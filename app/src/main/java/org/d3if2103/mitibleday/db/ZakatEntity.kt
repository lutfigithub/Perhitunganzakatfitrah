package org.d3if2103.mitibleday.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zakat")
data class ZakatEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var hargaBeras: Double,
    var jiwa: Double
)
