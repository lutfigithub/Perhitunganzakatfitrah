package org.d3if2103.mitibleday.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2103.mitibleday.db.ZakatDao
import org.d3if2103.mitibleday.db.ZakatDb
import org.d3if2103.mitibleday.db.ZakatEntity
import org.d3if2103.mitibleday.model.harga

class HitungViewModel(private val db: ZakatDao) : ViewModel() {

    private val hasilZakat = MutableLiveData<harga?>()

    val data = db.getLastBmi()

    fun hitungZakat(hargaBeras:Double,jiwa:Double) {
        val hasil = hargaBeras*2.5*jiwa
        hasilZakat.value = harga(hasil.toFloat())

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataZakat = ZakatEntity(
                    hargaBeras = hargaBeras,
                    jiwa = jiwa
                )
                db.insert(dataZakat)
            }
        }
    }
    fun getHasil(): LiveData<harga?> = hasilZakat
}