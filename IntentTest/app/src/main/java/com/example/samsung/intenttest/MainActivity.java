package com.example.samsung.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //显示Intent
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

       /* 隐示Intent
        需要在AndroidManifest.xml中添加如下
                  <intent-filter>
                <action android:name="START_THID"/>  //随意指定，须与activity中相同
                <category android:name="android.intent.category.DEFAULT"/>   //必须 否则崩溃
                <category android:name="THID"/>    //随意指定，须与activity中相同
            </intent-filter>
            */
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("START_THID");
                intent.addCategory("THID");
                startActivity(intent);
            }
        });
    }
}
