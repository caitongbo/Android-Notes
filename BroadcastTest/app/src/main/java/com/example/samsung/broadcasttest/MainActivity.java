package com.example.samsung.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NextworkChangeReceiver nextworkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        nextworkChangeReceiver = new NextworkChangeReceiver();
        registerReceiver(nextworkChangeReceiver,intentFilter);
    }
    protected void onDestory(){
        super.onDestroy();
        unregisterReceiver(nextworkChangeReceiver);
    }
    class NextworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
                Toast.makeText(context,"network changes",Toast.LENGTH_SHORT).show();
        }
    }
}
