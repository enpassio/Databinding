package com.enpassio.databindingwithnewsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsRepository mRepo = NewsRepository.getInstance();
        mRepo.getArticles().observe(this, articles -> {
            if (articles != null) {
                for (Article article : articles) {
                    Log.d(TAG, "article: " + article.toString());
                }
            }
        });
    }
}
