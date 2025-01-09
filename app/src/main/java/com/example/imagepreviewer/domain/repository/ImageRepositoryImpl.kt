package com.example.imagepreviewer.domain.repository

import com.example.imagepreviewer.data.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {

    override suspend fun downloadImageFile(): Response<ResponseBody> {
        return RetrofitClient().getClient().downloadImageFile()
    }
}