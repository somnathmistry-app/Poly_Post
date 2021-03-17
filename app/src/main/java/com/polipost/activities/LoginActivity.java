package com.polipost.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.polipost.R;
import com.polipost.Utilities.ApiClient;
import com.polipost.Utilities.ApiInterface;
import com.polipost.Utilities.MyPreferences;
import com.polipost.models.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText emailIdEt, passwordEt;
    private ProgressBar progressBar;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myPreferences = new MyPreferences(this);
        emailIdEt = findViewById(R.id.email_id);
        passwordEt = findViewById(R.id.pass_id);

        progressBar = findViewById(R.id.spin_kit);

        progressBar.setVisibility(View.GONE);

    }

    public void createAccount(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }

    public void getLoginBn(View view) {

        getValidation();
    }

    private void getValidation() {
        String emailStr = emailIdEt.getText().toString().trim();
        String passStr = passwordEt.getText().toString().trim();

        if (!emailStr.isEmpty() && !passStr.isEmpty()) {
            getUserLogin(emailStr, passStr);
        } else {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserLogin(String emailStr, String passStr) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.getLogin(emailStr, passStr);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("ok")) {

                        myPreferences.writeLoginStatus(true);
                        String id = String.valueOf(response.body().getData().getId());
                        String name = response.body().getData().getName();
                        String email = response.body().getData().getEmailId();

                        myPreferences.writeUserId(id);
                        myPreferences.writeUserName(name);
                        myPreferences.writeUserEmail(email);

                        Toast.makeText(LoginActivity.this, "Welcome to PoliPost", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Server error, please try again later", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}