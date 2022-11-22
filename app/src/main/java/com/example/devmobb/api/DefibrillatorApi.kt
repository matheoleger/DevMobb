package com.example.devmobb.api

import Defibrillator
import retrofit2.Response
import retrofit2.http.GET

interface DefibrillatorApi {
    @GET("api/defibrillators")
    suspend fun getDefibrillators(): Response<List<Defibrillator>>
}