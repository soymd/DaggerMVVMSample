package io.github.soymd.daggermvvm.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class GitRepository @Inject constructor(
    retrofitBuilder: Retrofit.Builder,
    baseUrl: String
) {
    private val retrofitForCoroutine: Retrofit
    private val gitApi: GitApi

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        retrofitForCoroutine = retrofitBuilder.baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        gitApi = retrofitForCoroutine.create(GitApi::class.java)
    }

    suspend fun get(): GitObject {
        return gitApi.get()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GitModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }
}