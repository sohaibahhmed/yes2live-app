package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {
Button button1;
Button bmi_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        button1=(Button) findViewById(R.id.dibetic);
        bmi_button=(Button)findViewById(R.id.bmi);
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(homepage.this, married.class);
               startActivity(intent);
           }
       });
        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, bmi_calculator.class);
                startActivity(intent);
            }
        });
    }
}