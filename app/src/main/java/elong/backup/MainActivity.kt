package elong.backup

import android.annotation.SuppressLint
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import elong.backup.di_tucla_dependencyInjection.ImageLoad
import elong.backup.retrofitNetworkApi.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named
/*RETROFIT:
* Bước 1: Đưa thư viện retrofit vào project android (xem trong build.gradle)
* Bước 2: Tạo 1 folder để chứa tất cả các API truyền vào (retrofitNetworkApi)
*   1 API như zậy chứa 2 file
*       - file chứa tất cả API liên quan đến project (VD: GithubApi)
*       - file chứa tất cả những config cho API này (VD: GithubServices, xem zải thích trong file đó)
*
* DAGGER: đưa khái niệm về injection dependency vào
* Bước 1: Đưa thư viện Dagger vào project android (xem trong build.gradle)
* Bước 2: Zải thích tại file AppElong
        * DAGGER tutorial            |----> subComponent1 -> module1
        *         App -> AppComponent|----> subComponent2 -> module2 -> cung cấp (VD GithubService) -> inject GithubService này vào activity, fragment hay service
        *                            |----> module3
*   2.1: Tạo ra AppComponent->SubComponent->Modules(tất cả đc lưu vào package di)
*   2.2: Sau khi có đầy đủ thì đưa AppComponent vào App (AppElong)
*   (Để get image từ đường link -> sử dụng library Picasso (implementation trong build.gradle),
*   tạo 1 module ImageModule (load ImageLoad vào module đó)  và inject vào MainActivity)
*
* RxANDROID
* Bước 1: Đưa thư viện RxAndroid vào project android (xem trong build.gradle)
* */
class MainActivity
//    : AppCompatActivity() { /*cách 1: kế thừa từ AppCompatActivity*/
    : DaggerAppCompatActivity(){    /*cách 2: kế thừa từ DaggerAppCompatActivity*/
    @Inject     /*@Inject là anotation*/
    lateinit var githubApi: GithubApi
    /*Ngoài ra, trong mainActivity còn nhiều inject khác nữa, VD*/
    @Inject
    @Named("app-module")          /*singleton*/
    lateinit var imageLoad: ImageLoad   /*inject này đc demo là 1 instance, cho dù activity có bị destroy đi chăng nữa thì nó vẫn là 1 instance*/
    @Inject
    @Named("image-module")        /*activity scope*/
    lateinit var imageLoad2: ImageLoad  /*còn inject này khi mà activity bị destroy thì lần sau khi khởi tạo lại nó sẽ tạo ra 1 zá trị mới*/
    /* => làm sao để khai báo 1 lần ntnày thôi mà có thể đc truy xuất trong mainActivity => trong dagger lo hết */
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {

        /*cách 1: kế thừa từ AppCompatActivity => sử dụng AndroidInjection.inject(this) để injection */
//        AndroidInjection.inject(this)    /*bước tiếp theo là inject để hệ thống hiểu là @Inject githubApi đã đc dagger binding trong MainActivity này rồi*/
        /*cách 2: Bỏ AndroidInjection.inject(this) đi => lúc này code chỉ thuần vấn đề về @Inject lateinit var githubApi: GithubApi trên mà thôi*/

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        githubServices.genGithubService().rxGetProfile("JakeWharton") /*cách 1: .rxGetProfile("userName") hoặc .getProfile("userName"), cách làm này k theo dạng dependency*/
        /*cách 2: cách truyền thống*/
//        githubApi.getProfile("educlong")
//            ?.enqueue(object: Callback<Profile>{    /*đẩy callback vào để hứng data trả về từ api*/
//                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
//                    txt1.text= response.body()?.login
//                }
//                override fun onFailure(call: Call<Profile>, t: Throwable) {
//                    txt1.text=t.message
//                }
//            })
        /*cách 3: rxAndroid*/
        githubApi.rxGetProfile("educlong")
            ?.subscribeOn(Schedulers.io())/*rx hỗ trợ làm việc vs Thread và manyThreading. subscribeOn hỗ trợ chạy network trong Thread là IOThread. thường thì network thì dùng io()*/
            ?.observeOn(AndroidSchedulers.mainThread())/*UI thì k thể làm việc trên IOThread đc =>sau khi làm xong thì kếtquả sẽ đc handle trên 1 cái mainThread để we tươngtác vs UI*/
//            ?.toFlowable()      /*tiến hành làm theo realTime*/
            ?.subscribeBy (     /*subscribeBy nằm trong thư viện RxKotlin -> cần implementation vào trong build.gradle. subscribeBy liên quan đến phần callBack*/
                onSuccess = {
                                txt1.text= it.login
                                imageLoad.load(it.avatar_url,img1)
                            },
                onError ={it.printStackTrace()}
            )
    }
}