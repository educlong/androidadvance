package elong.backup.di_tucla_dependencyInjection
/*package di chứa tất cả những dependency injection liên quan đến dagger*/

import dagger.Module
import dagger.Provides
import elong.backup.retrofitNetworkApi.GithubServices
import javax.inject.Named
import javax.inject.Singleton


@Module /*module này là của dagger => module này sẽ inject sử dụng cho activity, fragment hay service (nói tại ElongApp)*/
class AppModule {
    @Provides   /*để module trả về cho we những dependency thì sẽ có nhiều cách, trong đó @Provides là 1 cách thôi*/
    fun providesGithubApi()=GithubServices().genGithubService() /*thay vì trong mainActivity, */
    /*muốn sử dụng GithubApi thì phải call hàm này trong mainActivity => we sẽ call ở đây*/
    /*trong mainActivity chỉ cần chỉ ra inject vào thôi (@Inject lateinit var githubApi: GithubApi trong mainActivity) */

    @Provides
    @Singleton /*singleton khi sử dụng cho AppModule thì chỉ sử dụng khi nào biết chắc là thành phần này khởi tạo duy I' 1 lần trong app này thôi(VD: Api chỉ khởi tạo đúng 1 lần)*/
    @Named("app-module")    /*zả sử có 2 module cùng cung cấp 1 đối tượng là ImageLoad -> làm sao để dagger biết là ImageLoad nào đc cung cấp từ module nào => sử dụng Named*/
    fun providesImageLoader()= ImageLoad()
}