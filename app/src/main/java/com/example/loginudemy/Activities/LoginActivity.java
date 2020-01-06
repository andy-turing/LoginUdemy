package com.example.loginudemy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.loginudemy.R;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private EditText edtEmail;
    private  EditText edtPassword;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("Prefernces", MODE_PRIVATE);
        bindUI();
        setCredentialsIfExists();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login(edtEmail.getText().toString(),edtPassword.getText().toString())){
                    saveOnPrefernces(edtEmail.getText().toString(),edtPassword.getText().toString());
                    goToMain();
                }
            }
        });

    }

    private void setCredentialsIfExists(){
        String email = getUserMailPrefs();
        String pass = getUserPassPrefs();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            edtEmail.setText(email);
            edtPassword.setText(pass);
        }
    }

    private String getUserMailPrefs(){
        return prefs.getString("email",""); //que devuelve en caso de no encontrarse
    }
    private String getUserPassPrefs(){
        return prefs.getString("password",""); //que devuelve en caso de no encontrarse
    }


    private void saveOnPrefernces(String email, String password){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            editor.commit();
            editor.apply();
        }
    }
    private void bindUI(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        switchRemember = findViewById(R.id.switchRemember);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length()>4;
    }

    private boolean login(String email, String password){
        if (!isValidEmail(email)){
            Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if (!isValidPassword(password)){
                Toast.makeText(this, "Password debe ser mayor a 4 de longitud", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                return true;
            }
        }
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
