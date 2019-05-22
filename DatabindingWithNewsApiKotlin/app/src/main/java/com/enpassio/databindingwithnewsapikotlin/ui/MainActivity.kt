package com.enpassio.databindingwithnewsapikotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.enpassio.databindingwithnewsapikotlin.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.fragment_holder, ArticleListFragment())
            }
        }
    }
}
