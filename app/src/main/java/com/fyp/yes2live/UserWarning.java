package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserWarning extends AppCompatActivity {

    TextView warning;
    SharedPreferenceManager sharedPreferenceManager;
    Button next;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_warning);
        getSupportActionBar().hide();
        warning=findViewById(R.id.u_warning);
        next=findViewById(R.id.NextP);
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        String message=sharedPreferenceManager.getUserWarning();
        warning.setText(message);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserWarning.this,Summary.class);
                startActivity(i);
            }
        });
    }
}