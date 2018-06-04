package com.example.samsung.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"0","1","2","3","4","5","6","7","8","9","4","5","6","7","8","9","4","5","6","7","8","9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
    }
}
