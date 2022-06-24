package org.d3if2103.mitibleday.ui.about

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2103.mitibleday.model.TentangAplikasi
import org.d3if2103.mitibleday.network.ApiStatus
import org.d3if2103.mitibleday.network.ZakatApi


class AboutViewModel : ViewModel() {

    private val tentangAplikasi = MutableLiveData<TentangAplikasi>()
    private val status = MutableLiveData<ApiStatus>()


    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                tentangAplikasi.postValue(ZakatApi.service.getZakat())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("AboutViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getTentangAplikasiZakat(): LiveData<TentangAplikasi> = tentangAplikasi

    fun getStatus(): LiveData<ApiStatus> = status
}