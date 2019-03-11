package com.enpassio.databindingwithnewsapikotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.enpassio.databindingwithnewsapikotlin.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_holder, ArticleListFragment())
                .commit()
        }
    }
}
