package com.example.samsung.shoushi;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class ShiBieActivity extends AppCompatActivity
{
    GestureOverlayView gestureOverlayView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shibie);
        gestureOverlayView=findViewById(R.id.gestureOverlayViewShiBie);
        gestureOverlayView.setGestureColor(Color.GREEN);//设置手势颜色
        gestureOverlayView.setGestureStrokeWidth(30);//设置手势粗细
        final GestureLibrary gestureLibrary= GestureLibraries.fromFile("/mnt/sdcard/gestures");
        if(gestureLibrary.load())
        {
            Toast.makeText(ShiBieActivity.this, "检测到系统中有手势文件！", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(ShiBieActivity.this, "检测到系统中没有手势文件，请添加手势后再识别！", Toast.LENGTH_LONG).show();
        }
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener()
        {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
            {
                //识别所画手势
                ArrayList<Prediction> prediction=gestureLibrary.recognize(gesture);
                //遍历集合
                for(Prediction pre:prediction)
                {
                    if(pre.score>4.0)
                    {
                        Toast.makeText(ShiBieActivity.this, "手势与"+pre.name+"匹配", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ShiBieActivity.this, "无匹配手势，不能进入系统", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
