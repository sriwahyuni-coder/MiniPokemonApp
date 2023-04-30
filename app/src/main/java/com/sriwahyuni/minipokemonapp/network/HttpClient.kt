package com.sriwahyuni.minipokemonapp.network

import com.readystatesoftware.chuck.ChuckInterceptor
import com.sriwahyuni.minipokemonapp.BuildConfig
import com.sriwahyuni.minipokemonapp.MiniPokemonApp
import com.sriwahyuni.minipokemonapp.utils.Helpers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client : Retrofit? = null
    private var endpoint : Endpoint? = null

    companion object{
        private val mInstance: HttpClient = HttpClient()
        @Synchronized
        fun getInstance():HttpClient{
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi():Endpoint?{
        if (endpoint == null){
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient(){
        val builder  = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(MiniPokemonApp.getApp()))
        }

        val okHttpClient = builder.build()
        client = Helpers.getDefaultGson()?.let { GsonConverterFactory.create(it) }?.let {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(it)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        endpoint = null
    }

}