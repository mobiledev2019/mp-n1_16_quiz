package com.doanhld.quiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.doanhld.quiz.R;
import com.doanhld.quiz.model.ResObj;
import com.doanhld.quiz.remote.ApiUtils;
import com.doanhld.quiz.remote.UserService;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText inputUserName, inputPassword;
    private Button btnLogin, btnSingupRedirect;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputUserName = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_passwordsing);

        btnLogin = findViewById(R.id.btn_login);
        btnSingupRedirect = findViewById(R.id.link_signup);
        userService = ApiUtils.getUserService();
        checkLogin();
//        validate();
    }

    private void checkLogin() {


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = inputUserName.getText().toString();
                String password = inputPassword.getText().toString();

                if (validateLogin(username, password)) {
                    doLogin(username, password);
                }

            }
        });
        btnSingupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(String username, String password) {
        Call<ResObj> call = userService.login(username, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.isSuccessful()) {
                    ResObj resObj =  response.body();
                    if (resObj.getMessage().equals("true")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
