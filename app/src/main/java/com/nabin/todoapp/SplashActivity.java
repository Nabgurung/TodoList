package com.nabin.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences =getApplicationContext().getSharedPreferences("todo_pref",0);
                Boolean authentication=preferences.getBoolean("authentication",false);
                if(authentication){
                    Intent intent=new Intent(SplashActivity.this, ToDosListActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        },4000);
    }
}