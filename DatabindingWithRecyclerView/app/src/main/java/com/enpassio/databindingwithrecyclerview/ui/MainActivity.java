package com.enpassio.databindingwithrecyclerview.ui;

import android.os.Bundle;

import com.enpassio.databindingwithrecyclerview.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*We could have call binding here like:
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        But we don't need to call anything from binding in our case.*/

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_holder, new ProductListFragment())
                    .commit();
        }
    }
}
