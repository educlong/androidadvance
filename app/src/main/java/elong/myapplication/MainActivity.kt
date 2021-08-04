package elong.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import elong.myapplication.di.ImageLoad
import elong.myapplication.models.jsonAdvDemo.Currency
import elong.myapplication.retrofit.JsonApi
import elong.myapplication.retrofit.JsonApiUtils
import elong.myapplication.models.jsonDemo.Profile
import elong.myapplication.models.jsonArrDemo.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity(){
    @Inject
    lateinit var githubApi: JsonApi
    @Inject
    @Named("app-module")
    lateinit var imageLoad: ImageLoad
    @Inject
    @Named("image-module")
    lateinit var imageLoad2: ImageLoad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*cách 2: cách truyền thống (bỏ hết @Inject đi)*/
        //retrofitDemo()


        /*cách 3: rxAndroid*/
        githubApi.rxGetProfile("JakeWharton")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy  (
                onSuccess = {
                                txt1.text= it.login
                                imageLoad.load(it.avatar_url,img1)
                            },
                onError ={it.printStackTrace()}
            )
    }

    private fun retrofitDemo() {
        var githubApi: JsonApi = JsonApiUtils("https://api.github.com/users/").getDataJsonApi()
        githubApi.getProfile("educlong")
            ?.enqueue(object: Callback<Profile>{    /*đẩy callback vào để hứng data trả về từ api*/
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    txt1.text= response.body()?.login
                }
                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    txt1.text=t.message
                }
            })


        var currencyApi:JsonApi=JsonApiUtils("http://apilayer.net/").getDataJsonApi()
        currencyApi.getCurrency("843d4d34ae72b3882e3db642c51e28e6", "VND","USD",1).enqueue(object : Callback<Currency>{
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                var currency: Currency? =response.body()
                if (currency!=null && currency.success){
                    txtSuccess.text= currency.success.toString()+"-"
                    txtTerms.text=currency.terms
                    txtPrivacy.text=currency.privacy
                    txtTimesStamp.text=currency.timestamp.toString()+"-"
                    txtSource.text=currency.source+"-"
                    txtQuotes.text=currency.quotes.USDVND.toString()
                }
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error ${t.message}",Toast.LENGTH_SHORT).show()
            }
        })


        var jsonApi: JsonApi = JsonApiUtils("https://www.w3schools.com/js/").getDataJsonApi()
        var sourceListPerson:MutableList<Person> = mutableListOf()
        var adapterListPerson:ArrayAdapter<Person>?=null
        jsonApi.getPersonList()?.enqueue(object : Callback<MutableList<Person>>{
            override fun onResponse(call: Call<MutableList<Person>>, response: Response<MutableList<Person>>) {
                var personList: MutableList<Person>? = response.body()
                if (personList != null){
                    for (person: Person in personList)
                        sourceListPerson.add(person)
                    adapterListPerson = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1,sourceListPerson)
                    lvWebServiceJSONListInforPerson.adapter=adapterListPerson
                }
            }
            override fun onFailure(call: Call<MutableList<Person>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error ${t.message}",Toast.LENGTH_SHORT).show()
            }   /*nếu code bị bug: CLEARTEXT -> add thêm android:usesCleartextTrafic="true" trong manifest*/
        })
    }
}