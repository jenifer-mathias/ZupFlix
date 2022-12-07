package br.com.zupflix.data.services

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create() //alguns json poedem dar erro na parse.
        return Retrofit.Builder()
            .baseUrl(br.com.zupflix.BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val service: Services = initRetrofit().create(Services::class.java)
}
