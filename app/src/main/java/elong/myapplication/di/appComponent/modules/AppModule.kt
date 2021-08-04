package elong.myapplication.di.appComponent.modules
/*package di chứa tất cả những dependency injection liên quan đến dagger*/

import dagger.Module
import dagger.Provides
import elong.myapplication.di.ImageLoad
import elong.myapplication.retrofit.JsonApiUtils
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    fun providesGithubApi() = JsonApiUtils("https://api.github.com/users/").getDataJsonApi()

    @Provides
    @Singleton
    @Named("app-module")
    fun providesImageLoader()= ImageLoad()
}