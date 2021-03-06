package com.fleming.githubuserfinder.di.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@kotlin.annotation.MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)