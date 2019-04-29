package com.exchange.rates.util

import io.reactivex.Scheduler

class AppSchedulers(private val _ioThread: Scheduler,
                    private val _mainThread: Scheduler) {

    val ioThread: Scheduler
        get() = _ioThread

    val mainThread: Scheduler
        get() = _mainThread
}