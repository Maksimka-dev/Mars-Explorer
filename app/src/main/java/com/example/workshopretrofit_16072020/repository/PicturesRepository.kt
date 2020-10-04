package com.example.workshopretrofit_16072020.repository

import com.example.workshopretrofit_16072020.api.MarsPicture
import com.example.workshopretrofit_16072020.api.NasaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object PicturesRepository {

    private const val BASE_URL = "https://api.nasa.gov"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl((BASE_URL))
        .build()

    private val NasaService = retrofit.create(NasaApi::class.java)

    suspend fun getListOfPictures(date: String): List<MarsPicture> {
        return withContext(Dispatchers.IO) {
            NasaService.getListOfPictures(date).photos
                .map { result ->
                    MarsPicture(
                        result.id,
                        result.imageUrl,
                        result.date,
                        result.sol,
                        result.camera
                    )
                }
        }
    }

    fun getDefaultPicturesList(): List<MarsPicture> {
        return DataStorage.getPicturesList()
    }

}