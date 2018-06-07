package com.example.samsung.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        Toast.makeText(ThirdActivity.this,"ThirdAc",Toast.LENGTH_SHORT).show();
    }
}
