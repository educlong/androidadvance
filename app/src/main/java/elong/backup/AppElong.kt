package elong.backup

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import elong.backup.di_tucla_dependencyInjection.DaggerAppComponent

class AppElong : DaggerApplication() {  /* dấu () tức là kế thừa luôn constructor của Application => mở file manifest lên để application cho class này*/
    /*DAGGER tutorial            |----> subComponent1 -> module1
    *         App -> AppComponent|----> subComponent2 -> module2 -> cung cấp (VD GithubService) -> inject GithubService này vào activity, fragment hay service
    *                            |----> module3
    * khi app này chạy -> nó sẽ tạo ra 1 appComponent (là nơi chứa link tất cả các dependency trong project). trong appComponent này sẽ có nhiều subComponent
    * -> và mỗi subComponent sẽ đi vs nó là 1 module (module này là nơi cung cấp cho we những dependency để we sử dụng trong activity, fragment hay service của we)
    * VD trong trường hợp này subComponent tạo ra 1 module, module này cung cấp 1 GithubService -> we sẽ inject GithubService vào activity của we
    * Ngoài ra appComponent k nhất thiết phải có subComponent mà nó có thể trực tiếp tạo ra module luôn
    * Bên cạnh đó, có thể có Grab đc khởi tạo trong App, Grab này sẽ biết đc những module sẽ đc inject vào đâu.
    *
    * VD trong MainActivity, we muốn sử dụng GithubService -> cần khai báo @Inject trước onCreate*/


    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  /*khi kế thừa DaggerApplication thì phải overide 1 applicationInjector*/
                                                    /*applicationInjector trả về 1 AndroidInjector, AndroidInjector này chính là là appComponent của we*/
            /*sau khi gender, rebuild tất cả trong package di, models và retrofit, ko báo lỗi thì android sẽ gender ra cho we DaggerAppComponent*/
            = DaggerAppComponent.builder()  /*đây là biểu thức nên k cần dấu {}, gán = luôn. Dòng code này của kotlin hoảng loạn thật*/
            .application( this)        /*application trong AppComponent*/
            .build()                        /*build trong AppComponent, gọi build sẽ trả về 1 AppComponent*/
}