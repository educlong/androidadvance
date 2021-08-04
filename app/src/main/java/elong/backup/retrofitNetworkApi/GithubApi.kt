package elong.backup.retrofitNetworkApi

import elong.backup.models.Profile
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubApi {
    @GET("users/{userName}")    /*user truyền từ bên ngoài vào*/
    fun getProfile(@Path("userName") user: String?): Call<Profile>?     /*default của retrofit*/
    @GET("users/{userName}")    /*user truyền từ bên ngoài vào*/
    fun rxGetProfile(@Path("userName") user: String?): Single<Profile>? /*default của rxJava, Rx thì k trả về Call đc mà phải trả về 1 observable, và Single là 1 trong số đó*/
}   /*cả retrofit và rxandroid đều trả về 1 đối tượng là 1 profile -> tạo model là 1 profile để trả về đối tượng này*/