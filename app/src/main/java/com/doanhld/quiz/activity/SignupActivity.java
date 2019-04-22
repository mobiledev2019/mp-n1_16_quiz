package com.doanhld.quiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doanhld.quiz.R;
import com.doanhld.quiz.model.Account;
import com.doanhld.quiz.model.ResObj;
import com.doanhld.quiz.remote.ApiUtils;
import com.doanhld.quiz.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    Button btnSingup, btnLoginRedirect;
    EditText edtUser, edtEmail, edtPassword, edtRePassword;
    UserService userService;
    Account account = new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSingup = findViewById(R.id.btn_signup);
        btnLoginRedirect = findViewById(R.id.link_login);

        edtUser = findViewById(R.id.input_name);
        edtEmail = findViewById(R.id.input_email);
        edtPassword = findViewById(R.id.input_password);
        edtRePassword = findViewById(R.id.input_reEnterPassword);
        userService = ApiUtils.getUserService();
        checkUser();

    }
    private void checkUser() {
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String userName = edtUser.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String reEnterPassword = edtRePassword.getText().toString();
                account.setUsername(userName);
                account.setEmail(email);
                account.setPassword(password);
                if (validateSignUp(userName, email, password, reEnterPassword)) {
                    singup(account);
                }

            }
        });
        btnLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void  singup(Account account) {
        Call<Account> call = userService.createAccount(account);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateSignUp(String userName, String email, String password, String reEnterPassword) {
        boolean valid = true;
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


    private void onLoginFailed() {
        btnSingup.setEnabled(true);
    }

}
