package com.enpassion.twowaydatabindingkotlin.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, ToyListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        /*//If back is clicked when AddToyFragment is on the screen,
        check whether there are unsaved changes*/
       val currentFrag = supportFragmentManager.findFragmentById(R.id.main_container)
        if(currentFrag is AddToyFragment){
            currentFrag.onBackClicked()
        } else {
            super.onBackPressed()
        }
    }
}
