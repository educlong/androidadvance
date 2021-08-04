package elong.myapplication.retrofit

import elong.myapplication.models.jsonAdvDemo.Currency
import elong.myapplication.models.jsonArrDemo.Person
import elong.myapplication.models.jsonDemo.Profile
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JsonApi {
    /*JsonDemo - GithubDemo*/
    @GET("{userName}")
    fun getProfile(@Path("userName") user: String?): Call<Profile>?
    @GET("{userName}")
    fun rxGetProfile(@Path("userName") user: String?): Single<Profile>?


    /*JsonArray Demo*/
    @GET("customers_mysql.php")
    fun getPersonList(): Call<MutableList<Person>>?


    /*JsonAdv Demo*/
    //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    @GET("api/live")
    fun getCurrency(@Query("access_key") access_key:String,  /*2 thành phần: key (@Query("access_key")) và value (access_key:String) --> xem link trên*/
                    @Query("currencies") currencies:String,
                    @Query("source") source:String,
                    @Query("format") format:Int):Call<Currency>
}