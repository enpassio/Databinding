package com.enpassion.twowaydatabindingkotlin.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


@Database(entities = [ToyEntry::class], version = 1, exportSchema = false)
@TypeConverters(MapConverter::class)
abstract class ToyDatabase : RoomDatabase() {

    abstract fun toyDao(): ToyDao

    companion object {

        private const val DATABASE_NAME = "toy_inventory"
        @Volatile private var sInstance: ToyDatabase? = null

        fun getInstance(context: Context): ToyDatabase {
            return sInstance ?: synchronized(this) {
                sInstance ?: Room.databaseBuilder(context.applicationContext,
                    ToyDatabase::class.java, ToyDatabase.DATABASE_NAME)
                    .build()
                    .also { sInstance = it }
            }

        }
    }
}