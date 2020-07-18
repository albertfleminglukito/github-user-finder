package com.fleming.githubuserfinder.base.scheduler

import io.reactivex.Scheduler


interface BaseSchedulerProvider {

	fun computation(): Scheduler

	fun io(): Scheduler

	fun ui(): Scheduler

}
