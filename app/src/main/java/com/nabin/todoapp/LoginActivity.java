package com.nabin.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText textUser,textpass;
    Button btnLogin,btnCancel;
    AlertDialog.Builder mAlertdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUser = findViewById(R.id.login_username);
        textpass=findViewById(R.id.login_password);

        btnLogin=findViewById(R.id.login_ok);
        btnCancel=findViewById(R.id.login_cancel);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String userName = textUser.getText().toString();
                String password =textpass.getText().toString();

                if (userName.equals("")){
                    textUser.setError(getString(R.string.text_username_validation));
                    textUser.requestFocus();
                }
                if (password.equals("")){
                    textpass.setError(getString(R.string.text_password_validation));
                    textpass.requestFocus();
                }
                else
                {
                    if (userName.equals("nabin")&&password.equals("nabin")){
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref ",0);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putBoolean("authentication",true);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, ToDosListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        textUser.setError(getString(R.string.text_login_invalid));
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mAlertdialog = new AlertDialog.Builder(LoginActivity.this);
                mAlertdialog.setMessage(getString(R.string.text_alert)).setCancelable(false).setTitle(getString(R.string.app_name)).setIcon(R.mipmap.ic_launcher);
                mAlertdialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                mAlertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mAlertdialog.show();
            }
        });
    }
}