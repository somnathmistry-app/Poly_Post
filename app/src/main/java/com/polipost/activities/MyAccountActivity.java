package com.polipost.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.polipost.R;
import com.polipost.Utilities.MyPreferences;

public class MyAccountActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MyPreferences myPreferences;
    private TextView nameTx, emailTx, partyTx, positionTx, stateTx, districTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        setUi();
        setVAlue();

    }

    private void setUi() {
        toolbar = findViewById(R.id.toolbar);
        myPreferences = new MyPreferences(this);
        nameTx = findViewById(R.id.name);
        emailTx = findViewById(R.id.email);
        partyTx = findViewById(R.id.nameid);
        positionTx = findViewById(R.id.positionid);
        stateTx = findViewById(R.id.stateid);
        districTx = findViewById(R.id.districid);

    }
    private void setVAlue() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameTx.setText(myPreferences.readUserName());
        emailTx.setText(myPreferences.readUserEmail());
    }

}