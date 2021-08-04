package elong.backup.di_tucla_dependencyInjection

import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
/*trong java là @interface thì trong kotlin là annotation*/
annotation class ActivityScope  /*Đây là 1 captionScope, captionScope đc đặt tại ActivityBuilder*/