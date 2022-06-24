package org.d3if2103.mitibleday.ui.hitung

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2103.mitibleday.MainActivity
import org.d3if2103.mitibleday.db.ZakatDao
import org.d3if2103.mitibleday.db.ZakatEntity
import org.d3if2103.mitibleday.model.harga
import org.d3if2103.mitibleday.model.hitungZakat
import org.d3if2103.mitibleday.network.UpdateWorker
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db: ZakatDao) : ViewModel() {

    private val hasilZakat = MutableLiveData<harga?>()


    fun hitungZakat(hargaBeras:Double,jiwa:Double) {

        val dataZakat = ZakatEntity(
            hargaBeras = hargaBeras.toFloat(),
            jiwa = jiwa.toFloat()
        )
        hasilZakat.value = dataZakat.hitungZakat()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataZakat)

            }
        }
    }

    fun getHasil(): LiveData<harga?> = hasilZakat

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}