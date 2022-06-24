package org.d3if2103.mitibleday.network

import  com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2103.mitibleday.model.TentangAplikasi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "lutfigithub/Api/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ZakatApiService {
    @GET("Zakat.json")
    suspend fun getZakat(): TentangAplikasi
}

object ZakatApi {
    val service: ZakatApiService by lazy {
        retrofit.create(ZakatApiService::class.java)
    }
    fun getImageUrl():String{
        return BASE_URL + "54483.png"
    }
}

enum class ApiStatus {LOADING,SUCCESS,FAILED}