package com.example.workshopretrofit_16072020.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=Iejb4dZggR8e0MOpg2JZPsdB2Mzb9EpEcIzdEksn")
    suspend fun getListOfPictures(@Query("earth_date") earth_date: String): Mars
}