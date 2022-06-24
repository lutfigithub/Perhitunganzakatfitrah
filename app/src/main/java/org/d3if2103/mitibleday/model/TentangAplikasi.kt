package org.d3if2103.mitibleday.model

import com.squareup.moshi.Json

data class TentangAplikasi(
    @Json(name = "tentang_aplikasi")
    val tentangAplikasi: String
)
