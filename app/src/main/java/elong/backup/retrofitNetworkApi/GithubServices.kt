package elong.backup.retrofitNetworkApi

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GithubServices {  /*chứa các config cho api*/
    private var builder:OkHttpClient=OkHttpClient.Builder().readTimeout(5000,TimeUnit.MILLISECONDS)   /*set lại time ngắt k cho đọc data nữa, VD 5s=5000ms*/
        .writeTimeout(5000, TimeUnit.MILLISECONDS)     /*Time viết, cũng cho 5s*/
        .connectTimeout(10000,TimeUnit.MILLISECONDS)   /*kết nối đến server lâu quá k đc thì có thể set lại time đợi trong bao nhiêu lâu để ngắt lại kết nối này*/
        .retryOnConnectionFailure(true)     /*nếu connection có vấn đề zì thì retryOnConnectionFailure có khả năng khôi phục những kết nối đó*/
        .build()
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(builder)                                            /*kiểm soát time đã đc set ở trên*/
        .addConverterFactory(GsonConverterFactory.create())         /*converter này sử dụng gson trong retrofit (retrofit kết hợp vs gson). Tiếp theo add thêm 1 converter nữa*/
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  /*converter này làm việc vs rxjava*/
        .build()

    public fun genGithubService():GithubApi{
        return retrofit.create<GithubApi>(GithubApi::class.java)   /*tạo ra 1 githubservice để sử dụng GithubApi*/
    }
}