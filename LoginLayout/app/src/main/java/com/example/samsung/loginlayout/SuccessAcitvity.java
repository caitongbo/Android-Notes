package com.example.samsung.loginlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SuccessAcitvity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_acitvity);

        Toast.makeText(SuccessAcitvity.this,"登录成功！",Toast.LENGTH_SHORT).show();
        btn = (Button) findViewById(R.id.exit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuccessAcitvity.this.finish();
            }
        });
    }
}
