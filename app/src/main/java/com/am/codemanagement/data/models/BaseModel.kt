package com.securelink.myanmarbeenchanted.data.models


import android.content.Context
import com.am.codemanagement.network.NetworkApi
import com.am.codemanagement.persistence.MovieDatabase
import com.am.codemanagement.utils.BASEURL
import com.am.codemanagement.utils.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected val mTheMovieApi: NetworkApi

    var mMovieDatabase: MovieDatabase? = null
    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) //connect Url
            .readTimeout(15, TimeUnit.SECONDS) //read response
            .writeTimeout(15, TimeUnit.SECONDS) // send request
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(NetworkApi::class.java)
    }

    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

}