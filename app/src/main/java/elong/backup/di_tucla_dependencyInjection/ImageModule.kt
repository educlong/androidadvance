package elong.backup.di_tucla_dependencyInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ImageModule { /*zả sử có 2 module (ImageModule và AppModule) cùng cung cấp 1 đối tượng là ImageLoad */
    @Provides       /*-> làm sao để dagger biết là ImageLoad nào đc cung cấp từ module nào => sử dụng Named*/
    @Named("image-module")
    fun providesImageLoader()= ImageLoad()
}