package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.enpassio.databindingwithrecyclerviewkotlin.R

/**
 * Created by Greta Grigutė on 2018-12-23.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        /*We could have call binding here like:
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        But we don't need to call anything from binding in our case.*/

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_holder, ProductListFragment())
                    .commit()
        }
    }
}