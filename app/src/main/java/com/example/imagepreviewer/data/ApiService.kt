package com.example.imagepreviewer.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("test/images.txt")
    suspend fun downloadImageFile(): Response<ResponseBody>
}