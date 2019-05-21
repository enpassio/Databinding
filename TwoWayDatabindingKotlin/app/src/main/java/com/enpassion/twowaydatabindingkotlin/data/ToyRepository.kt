package com.enpassion.twowaydatabindingkotlin.data

import androidx.lifecycle.LiveData
import com.enpassion.twowaydatabindingkotlin.utils.AppExecutors


class ToyRepository private constructor(private val mDatabase: ToyDatabase, private val mExecutors: AppExecutors) {

    val toyList: LiveData<List<ToyEntry>>
        get() = mDatabase.toyDao().allToys

    fun getChosenToy(toyId: Int): LiveData<ToyEntry> {
        return mDatabase.toyDao().getChosenToy(toyId)
    }

    fun insertToy(toy: ToyEntry) {
        mExecutors.diskIO().execute{ mDatabase.toyDao().insertToy(toy) }
    }

    fun updateToy(toy: ToyEntry) {
        mExecutors.diskIO().execute{ mDatabase.toyDao().updateToyInfo(toy) }
    }

    fun deleteToy(toy: ToyEntry) {
        mExecutors.diskIO().execute{ mDatabase.toyDao().deleteToy(toy) }
    }

    companion object {

        private var sInstance: ToyRepository? = null

        fun getInstance(database: ToyDatabase, executors: AppExecutors): ToyRepository {
            return sInstance ?: synchronized(this) {
                sInstance
                    ?: ToyRepository(
                        database,
                        executors
                    ).also { sInstance = it }
            }
        }
    }

}
