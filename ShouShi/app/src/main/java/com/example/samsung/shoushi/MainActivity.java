package com.example.samsung.shoushi;

import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GestureOverlayView gestureOverlayView;//手势绘制控件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检测SD卡
        String stat = Environment.getExternalStorageState();
        if (stat.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(MainActivity.this,"SD卡ok!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"请检测SD卡是否安装！",Toast.LENGTH_SHORT).show();
        }
        gestureOverlayView=findViewById(R.id.gestureOverlayView);
        gestureOverlayView.setGestureColor(Color.RED);//设置手势颜色
        gestureOverlayView.setGestureStrokeWidth(30);//设置手势粗细
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener()
        {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture)
            {
                //手势画完了，加载一个布局save.xml，用来保存手势
                LayoutInflater lif=LayoutInflater.from(MainActivity.this);
                View savaDialog=lif.inflate(R.layout.save,null);
                //取sava中的2个控件
                final EditText etGName=savaDialog.findViewById(R.id.editTextGName);
                ImageView ivShow=savaDialog.findViewById(R.id.ivShowG);
                //手势画完之后，把手势转化为对应的位图，显示在ImageView控件中
                Bitmap bitmap=gesture.toBitmap(128,128,10,Color.BLUE);
                ivShow.setImageBitmap(bitmap);//在控件上显示位图
                //弹框
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("保存手势")
                        .setView(savaDialog)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                GestureLibrary gestureLibrary= GestureLibraries.fromFile("/mnt/sdcard/gestures");
                                gestureLibrary.addGesture(etGName.getText().toString().trim(),gesture);
                                gestureLibrary.save();
                                Toast.makeText(MainActivity.this,"手势保存成功！",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(MainActivity.this,ShiBieActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create().show();
            }
        });
    }
}
