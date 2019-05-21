package com.enpassion.twowaydatabindingkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ToyEntry::class], version = 1, exportSchema = false)
@TypeConverters(MapConverter::class, ProcurementTypeConverter::class, GenderTypeConverter::class)
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