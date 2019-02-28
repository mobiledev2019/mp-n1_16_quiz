package com.doanhld.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button btnLogin, btnSingupRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.input_email);
        editPassword = findViewById(R.id.input_password);

        btnLogin = findViewById(R.id.btn_login);
        btnSingupRedirect = findViewById(R.id.link_signup);
        checkLogin();
        validate();
    }

    private void checkLogin() {


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (editEmail.getText().length() !=0 && editPassword.getText().length() !=0){
//                    if (editEmail.getText().toString().equals("abc@gmail.com") && editPassword.getText().toString().equals("12345")){
//                        Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                        startActivity(intent);
//                    }else {
//                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
//                    }
//                }
//                else {
//                    Toast.makeText(LoginActivity.this,"Nhap du thong tin",Toast.LENGTH_LONG).show();
//                }
                login();
            }
        });
        btnSingupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SingupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void onLoginFailed() {

        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("enter a valid email address");
            valid = false;
        } else {
            editEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editPassword.setError(null);
        }

        return valid;
    }

}
