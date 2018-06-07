package com.example.samsung.sqliteopenhelpertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       createDatabase();
       addData();
       delData();
       updateData();
       selectData();
    }
    public void createDatabase(){
        dbhelper = new MyDatabaseHelper(this,"BookStore.db",null    ,1);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.getWritableDatabase();
            }
        });
    }
    //添加操作
    public void addData(){
        Button addDate =(Button) findViewById(R.id.add_data);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                //第一条数据
                values.put("name","new Day");
                values.put("author","caitongbo");
                values.put("pages","800");
                values.put("price","66");
                db.insert("Book",null,values);
                //第二条数据
                values.put("name","second Day");
                values.put("author","蔡同波");
                values.put("pages","99");
                values.put("price","100");
                db.insert("Book",null,values);
            }
        });
    }
    //更新
    public void updateData(){
        Button button = (Button)findViewById(R.id.update_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("price","999999");
                db.update("Book",values,"name=?",new String[]{"caitongbo"});
            }
        });
    }
    //删除
    public void delData(){
        Button button = (Button)findViewById(R.id.del_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getReadableDatabase();
                db.delete("Book","price>?",new String[]{"500"});
            }
        });
    }
    //查询
    public void selectData() {
        Button button = (Button) findViewById(R.id.select_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "name is:" + name);
                        Log.d("MainActivity", "author is:" + author);
                        Log.d("MainActivity", "pages is :" + pages);
                        Log.d("MainActivity", "price is :" + price);
                    } while (cursor.moveToNext());
                }
            }
        });
    }
}
