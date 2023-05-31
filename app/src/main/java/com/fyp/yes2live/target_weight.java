package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class target_weight extends AppCompatActivity {
    private Button button;
    private TextView range;
    private SharedPreferenceManager sharedPreferenceManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_weight);
        button = (Button) findViewById(R.id.NextButton);
        range=findViewById(R.id.weightRange);

        long user_id = sharedPreferenceManager.getUser().getId();

        //calling endpoint to get BMI range and set to range text field.

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(target_weight.this, Goal.class);
                startActivity(intent1);
            };
        });
    }
}