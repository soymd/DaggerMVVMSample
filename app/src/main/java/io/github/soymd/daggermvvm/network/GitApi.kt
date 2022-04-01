package io.github.soymd.daggermvvm.network

import retrofit2.http.GET

interface GitApi {
    @GET("users/soymd")
    suspend fun get(): GitObject
}