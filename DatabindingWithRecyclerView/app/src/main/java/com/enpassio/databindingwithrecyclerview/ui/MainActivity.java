package com.enpassio.databindingwithrecyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.enpassio.databindingwithrecyclerview.R;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*We are not obliged to implement databinding in all the layouts of the project.
        activity_main.xml contains only a frame layout, so we skipped that one. But if we had
        implemented databinding for activity layout as well, we could inflate it like:
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);*/

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_holder, new ProductListFragment())
                    .commit();
        }
    }
}
