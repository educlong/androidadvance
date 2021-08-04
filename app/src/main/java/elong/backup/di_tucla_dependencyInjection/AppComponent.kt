package elong.backup.di_tucla_dependencyInjection

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import elong.backup.AppElong
import javax.inject.Singleton

/**Phần bổ sung: Scope trong daggger => để quản lý lifetime, VD define cái biến này chỉ đc sống trong activity, nếu như activity bị hủy thì tất cả những zì liên quan đến
 * activity đó mà we làm việc vs dagger này sẽ bị hủy hết. VD trong trường hợp này có 1 scope là @Singleton*/
@Singleton  /*@Singleton sẽ tạo ra đối tượng AppComponent này và sẽ tồn tại vĩnh viễn trong app này luôn, trừ khi app này bị hủy nó mới bị hủy*/
/*tuy nhiên we có thể define ra nhiều scope khác nữa, và trong mỗi component đó we sẽ define những module của we*/


@Component(modules = [                  /*đánh dấu interface này là 1 component, trong component này có thể đưa tất cả những subComponent hoặc modules we cần đưa vào*/
    AndroidInjectionModule::class,      /*module đầu tiên bắt buộc phải có đó là AndroidInjectionModule để cung cấp 1 đối tượng những nơi có thể injectable đc (activity, fragment,..)*/
    AppModule::class,                   /*tiếp theo là những module của we*/
    ActivityBuilder::class
])
interface /*bắt buộc phải dùng interface*/ AppComponent
    : AndroidInjector
    /*thành phần bên trong này có thể là những phần của android, VD như activity, appication,...*/<AppElong>{
    /*AndroidInjector chứa những thành phần để support những đối tượng như activity, service, fragment.
    * Đối vs dagger thì nó có riêng 1 thư viện để hỗ trợ cho phần này -> muốn hỗ trợ thêm thì add thêm trong build gradle */

     @Component.Builder/*bind instance vào trong appComponent này bằng @Component.Builder, .Builder để sử dụng để tạo ra đối tượng appComponent ở trong MainActivity*/
     interface Builder {  /*khi xài @Component.Builder thì dưới nó phải là 1 interface*/
        @BindsInstance
        fun application(app:AppElong): Builder
        fun build():AppComponent        /*trả về đối tượng appComponent của we*/
    }
}/*dagger compile sẽ đọc hết thông tin của interface này -> sau đó nó tự động gender ra AppComponent cho mình
* Sau đó đánh dấu interface này là 1 component bằng @Component (line 16)*/