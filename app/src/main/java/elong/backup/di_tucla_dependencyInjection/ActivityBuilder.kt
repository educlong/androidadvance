package elong.backup.di_tucla_dependencyInjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import elong.backup.MainActivity

/*define những nơi mà we inject đối tượng của AppModule cung cấp*/
@Module
abstract class ActivityBuilder {    /*đây là 1 subComponent, subComponent này chứa 1 module là ImageModule*/
    @ActivityScope  /*Bắt buộc scope này phải ở bên phía component, component có thì module mới có -> do đó đặt trước @ContributesAndroidInjector  */
    @ContributesAndroidInjector(modules = [ImageModule::class])     /*nhiệm vụ của ContributesAndroidInjector là gender 1 đoạn code sẵn*/
    abstract fun bindMainActivity():MainActivity    /*define nơi để inject, tên hàm k quan trọng, quan trọng là kiểu trả về, ở đây trả về MainActivity, sau đó đưa vào AppComponent, đây 9 là 1 subComponent*/
}