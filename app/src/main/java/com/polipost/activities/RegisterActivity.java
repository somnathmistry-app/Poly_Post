package com.polipost.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.polipost.R;
import com.polipost.Utilities.ApiClient;
import com.polipost.Utilities.ApiInterface;
import com.polipost.models.ResponseClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEt, emailEt, passEt, passConfirmEt;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt = findViewById(R.id.name_id);
        emailEt = findViewById(R.id.email_id);
        passEt = findViewById(R.id.pass_id);
        passConfirmEt = findViewById(R.id.confirm_pass);
        progressBar = findViewById(R.id.spin_kit);

        progressBar.setVisibility(View.GONE);

    }

    private void getValidation() {
        String nameStr = nameEt.getText().toString().trim();
        String emailStr = emailEt.getText().toString().trim();
        String passStr = passEt.getText().toString().trim();
        String confPassStr = passConfirmEt.getText().toString().trim();

        if (!nameStr.equals("") && !emailStr.equals("") && !passStr.equals("") && !confPassStr.equals("")) {
            getLogin(nameStr, emailStr, passStr, confPassStr);
        } else {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }

    }

    private void getLogin(String nameStr, String emailStr, String passStr, String confPassStr) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseClass> call = apiInterface.getRegister(emailStr, passStr, nameStr);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    } else if (response.body().getResponse().equals("exist")) {
                        Toast.makeText(RegisterActivity.this, "User already exist, please try with another email", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Server Down, please try after sometime", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void loginAccount(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void getRegister(View view) {
        getValidation();
    }
}