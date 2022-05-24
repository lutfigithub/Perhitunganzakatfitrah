package org.d3if2103.mitibleday.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZakatDao {
    @Insert
    fun insert(bmi: ZakatEntity)
    @Query("SELECT * FROM zakat ORDER BY id DESC LIMIT 1")
    fun getLastBmi(): LiveData<ZakatEntity?>
}