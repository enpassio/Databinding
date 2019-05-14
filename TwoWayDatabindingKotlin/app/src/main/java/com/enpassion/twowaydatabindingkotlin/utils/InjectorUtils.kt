package com.enpassion.twowaydatabindingkotlin.utils

import android.content.Context
import com.enpassion.twowaydatabindingkotlin.data.ToyDatabase
import com.enpassion.twowaydatabindingkotlin.data.ToyRepository


fun provideRepository(context: Context): ToyRepository {
    val executors = AppExecutors.getInstance()
    val db = ToyDatabase.getInstance(context)
    return ToyRepository.getInstance(db, executors)
}