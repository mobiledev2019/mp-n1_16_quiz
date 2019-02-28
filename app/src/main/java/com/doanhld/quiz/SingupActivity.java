package com.doanhld.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingupActivity extends AppCompatActivity {
    Button btnSingup,btnLoginRedirect;
    EditText edtUser,edtEmail,edtPassword,edtRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        btnSingup = findViewById(R.id.btn_signup);
        btnLoginRedirect  = findViewById(R.id.link_login);
        edtUser = findViewById(R.id.input_name);
        edtEmail = findViewById(R.id.input_email);
        edtPassword = findViewById(R.id.input_password);
        edtRePassword = findViewById(R.id.input_reEnterPassword);
        checkUser();
    }



    private void checkUser() {
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
            }
        });
        btnLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void singup() {
        if (!validate()) {
            onLoginFailed();
            return;
        } else {
            Intent intent = new Intent(SingupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void onLoginFailed() {
        btnSingup.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String userName = edtUser.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String reEnterPassword = edtRePassword.getText().toString();

        if (userName.isEmpty() || userName.length() < 3) {
            edtUser.setError("at least 3 characters");
            valid = false;
        } else {
            edtUser.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtRePassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            edtRePassword.setError("Password Do not match");
            valid = false;
        } else {
            edtRePassword.setError(null);
        }

        return valid;
    }
}
