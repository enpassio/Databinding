package com.enpassion.twowaydatabindingkotlin.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors private constructor(private val diskIO: Executor) {

    fun diskIO(): Executor {
        return diskIO
    }

    companion object {

        // For Singleton instantiation
        private val LOCK = Any()

        private var sInstance: AppExecutors? = null

        fun getInstance(): AppExecutors {
            return sInstance ?: synchronized(LOCK) {
                sInstance ?: AppExecutors(Executors.newSingleThreadExecutor()).also { sInstance = it }
            }
        }
    }
}