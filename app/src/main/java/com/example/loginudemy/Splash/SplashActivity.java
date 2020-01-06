package com.example.loginudemy.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.loginudemy.Activities.LoginActivity;
import com.example.loginudemy.Activities.MainActivity;
import com.example.loginudemy.R;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, LoginActivity.class);
        if(!TextUtils.isEmpty(getUserMailPrefs()) && !TextUtils.isEmpty(getUserPassPrefs())){
            startActivity(intentMain);
        }else{
            startActivity(intent);
        }
        finish();

    }

    private String getUserMailPrefs(){
        return prefs.getString("email",""); //que devuelve en caso de no encontrarse
    }
    private String getUserPassPrefs(){
        return prefs.getString("password",""); //que devuelve en caso de no encontrarse
    }

}
