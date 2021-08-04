package elong.myapplication.di.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import elong.myapplication.di.DaggerAppComponent

class AppElong : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder()
            .application( this)
            .build()
}