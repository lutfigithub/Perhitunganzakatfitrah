package org.d3if2103.mitibleday.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2103.mitibleday.db.ZakatDao
import org.d3if2103.mitibleday.db.ZakatEntity

class HistoriViewModel(private val db: ZakatDao) : ViewModel() {
    val data = db.getLastBmi()

    fun hapusDataSatuPerSatu(historiEntity: ZakatEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.delete(historiEntity)
        }
    }

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}