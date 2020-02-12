package com.example.ispit_2017_09_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ispit_2017_09_06.fragments.PosiljkaAdd1Fragment;
import com.example.ispit_2017_09_06.helper.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.otvoriFragmentKaoReplace(this,R.id.mjestoFragment, PosiljkaAdd1Fragment.newInstance());
    }
}
