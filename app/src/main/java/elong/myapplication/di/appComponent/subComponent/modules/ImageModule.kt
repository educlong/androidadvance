package elong.myapplication.di.appComponent.subComponent.modules

import dagger.Module
import dagger.Provides
import elong.myapplication.di.ImageLoad
import javax.inject.Named

@Module
class ImageModule {
    @Provides
    @Named("image-module")
    fun providesImageLoader()= ImageLoad()
}