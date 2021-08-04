package elong.myapplication.di.appComponent.subComponent

import dagger.Module
import dagger.android.ContributesAndroidInjector
import elong.myapplication.MainActivity
import elong.myapplication.di.ActivityScope
import elong.myapplication.di.appComponent.subComponent.modules.ImageModule

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ImageModule::class])
    abstract fun bindMainActivity():MainActivity
}