package com.example.imagepreviewer.domain.repository

import okhttp3.ResponseBody
import retrofit2.Response

interface ImageRepository {
    suspend fun downloadImageFile(): Response<ResponseBody>
}