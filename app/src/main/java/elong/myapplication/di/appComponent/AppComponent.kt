package elong.myapplication.di.appComponent

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import elong.myapplication.di.appComponent.subComponent.ActivityBuilder
import elong.myapplication.di.appComponent.modules.AppModule
import elong.myapplication.di.app.AppElong
import javax.inject.Singleton

/**Phần bổ sung: Scope trong daggger => để quản lý lifetime, VD define cái biến này chỉ đc sống trong activity, nếu như activity bị hủy thì tất cả những zì liên quan đến
 * activity đó mà we làm việc vs dagger này sẽ bị hủy hết. VD trong trường hợp này có 1 scope là @Singleton*/
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,  /*modules của android*/
    ActivityBuilder::class,         /*đưa subComponent  vào appComponents*/
    AppModule::class                /*đưa module        vào appComponent*/
])
interface AppComponent : AndroidInjector <AppElong>{
     @Component.Builder
     interface Builder {
        @BindsInstance
        fun application(app: AppElong): Builder
        fun build(): AppComponent
    }
}