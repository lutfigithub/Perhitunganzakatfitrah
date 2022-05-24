package org.d3if2103.mitibleday.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2103.mitibleday.model.harga

class MainViewModel : ViewModel() {

    private val hasilZakat = MutableLiveData<harga?>()
    fun hitungZakat(hargaBeras:Double,jiwa:Double) {
        val hasil = hargaBeras*2.5*jiwa
        hasilZakat.value = harga(hasil.toFloat())
    }
    fun getHasil(): LiveData<harga?> = hasilZakat
}