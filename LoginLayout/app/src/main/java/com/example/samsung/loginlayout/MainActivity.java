package com.example.samsung.loginlayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox remember;
    private CheckBox autoLogin;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        remember = (CheckBox)findViewById(R.id.remenber);
        autoLogin = (CheckBox)findViewById(R.id.auto_login);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);

        read();
    }
    public void login(View view) {
        save();
        switch (checkInfo(username, password)) {
            case 1:
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                finish();
                break;
            case 0:
                Toast.makeText(MainActivity.this, "未通过验证！", Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }
    public void read(){
        boolean b1 = sharedPreferences.getBoolean("remember",false);
        boolean b2 = sharedPreferences.getBoolean("autoLogin",false);

        remember.setChecked(b1);
        autoLogin.setChecked(b2);

        if(b1 == true){
            username.setText(sharedPreferences.getString("username",""));
            password.setText(sharedPreferences.getString("password",""));
        }
        if (b2 == true){
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
            finish();
        }
    }
    public void save(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("remember",remember.isChecked());
        if (remember.isChecked()){
            String name= username.getText().toString();
            String pass = password.getText().toString();
            if ("admin".equals(name)&&"admin".equals(pass)){
            editor.putString("username",name);
            editor.putString("password",pass);
            }
            else
                Toast.makeText(MainActivity.this,"登陆信息有误，请重新输入",Toast.LENGTH_LONG).show();
        }
        editor.putBoolean("autoLogin",autoLogin.isChecked());
        editor.commit();
    }
    public int checkInfo(EditText username,EditText password){
        if ("admin".equals(username.getText().toString())&& "admin".equals(password.getText().toString()))
            return 1;
        return 0;
    }
}
