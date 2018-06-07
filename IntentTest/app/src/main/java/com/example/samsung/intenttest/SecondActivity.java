package com.example.samsung.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Toast.makeText(SecondActivity.this,"secondAc",Toast.LENGTH_SHORT).show();
    }
}
