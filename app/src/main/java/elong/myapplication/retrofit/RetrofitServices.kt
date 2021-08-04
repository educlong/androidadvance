package elong.myapplication.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitServices(baseUrl: String) {

    private var retrofits:Retrofit?=null
    private var builder: OkHttpClient = OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS)
        .writeTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(10000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .build()
    public var Retrofits:Retrofit?
        get() {return retrofits}
        set(value) {retrofits=value}

    init {  /*tương đương vs constructor trong java*/
        retrofits = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}