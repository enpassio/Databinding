package com.enpassio.twowaydatabinding.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, new ToyListFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (currentFrag instanceof AddToyFragment) {
            ((AddToyFragment)currentFrag).onBackClicked();
        } else {
            super.onBackPressed();
        }
    }

}

