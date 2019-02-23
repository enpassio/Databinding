package com.enpassion.twowaydatabindingkotlin.utils

import android.content.Context
import com.enpassion.twowaydatabindingkotlin.ToyRepository
import com.enpassion.twowaydatabindingkotlin.data.ToyDatabase


fun provideRepository(context: Context): ToyRepository {
    val executors = AppExecutors.getInstance()
    val db = ToyDatabase.getInstance(context)
    return ToyRepository.getInstance(db, executors)
}