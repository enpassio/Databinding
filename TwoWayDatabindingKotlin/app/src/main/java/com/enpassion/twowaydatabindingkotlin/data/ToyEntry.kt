package com.enpassion.twowaydatabindingkotlin.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.enpassion.twowaydatabindingkotlin.BR

@Entity(tableName = "toys")
data class ToyEntry(
    private var _toyName: String? = null,
    private var _categories: Map<String, Boolean>,
    private var _gender: Int = 0,
    private var _state: Int = 0,
    @PrimaryKey(autoGenerate = true) val toyId: Int = 0
) : BaseObservable() {

    var toyName : String?
    @Bindable get() = _toyName
        set(value) {
        _toyName = value
        notifyPropertyChanged(BR.toyName)
        Log.d(TAG, "toy name set")
    }

    var categories : Map<String, Boolean>
    @Bindable get() = _categories
        set(value) {
            _categories = value
            notifyPropertyChanged(BR.categories)
            Log.d(TAG, "categories set")
        }

    var gender : Int
    @Bindable get() = _gender
        set(value) {
            _gender = value
            notifyPropertyChanged(BR.gender)
            Log.d(TAG, "gender set")
        }

    var state : Int
    @Bindable get() = _state
        set(value) {
            _state = value
            notifyPropertyChanged(BR.state)
            Log.d(TAG, "state set")
        }

    companion object {
        private val TAG = "ToyEntry"
    }
}