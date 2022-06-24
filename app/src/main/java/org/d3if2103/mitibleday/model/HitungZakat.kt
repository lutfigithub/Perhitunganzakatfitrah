package org.d3if2103.mitibleday.model

import org.d3if2103.mitibleday.db.ZakatEntity

fun ZakatEntity.hitungZakat(): harga {
    val hasil =  hargaBeras * 2.5 * jiwa
    return harga(hasil.toFloat())
}