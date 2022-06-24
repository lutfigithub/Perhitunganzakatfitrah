package org.d3if2103.mitibleday.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZakatDao {
    @Insert
    fun insert(bmi: ZakatEntity)
    @Query("SELECT * FROM zakat ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<ZakatEntity?>>

    @Delete
    fun delete(zakatEntity: ZakatEntity)

    @Query("DELETE FROM zakat")
    fun clearData()
}