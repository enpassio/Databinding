package com.enpassio.twowaydatabinding.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.enpassio.twowaydatabinding.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, new ToyListFragment())
                    .commit();
        }
    }
}
