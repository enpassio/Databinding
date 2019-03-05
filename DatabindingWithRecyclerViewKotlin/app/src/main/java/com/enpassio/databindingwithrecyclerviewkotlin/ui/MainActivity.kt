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
        /*If we wanted to implement data binding for this layout as well,
        we could have call binding here like:
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        But we are not obliged to implement data binding in all of the layouts. This layout
        contains only a frame layout and nothing else, so we can skip this one. */

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_holder, ProductListFragment())
                    .commit()
        }
    }
}