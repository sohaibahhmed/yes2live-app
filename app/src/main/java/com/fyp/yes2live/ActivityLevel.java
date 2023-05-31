package com.fyp.yes2live;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLevel extends AppCompatActivity implements View.OnClickListener {
    private ImageButton previous;
    private CardView card1,card2,card3,card4,card5;
    private SharedPreferenceManager sharedPreferenceManager;
    private double activity_level;
    private Integer age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        getSupportActionBar().hide();
        previous = findViewById(R.id.PreviousButton);

        card1 = (CardView) findViewById(R.id.level1);
        card2 = (CardView) findViewById(R.id.level2);
        card3 = (CardView) findViewById(R.id.level3);
        card4 = (CardView) findViewById(R.id.level4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_level=1.2;
                openHomePage();
            }
        });


        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_level=1.375;
                openHomePage();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_level=1.55;
                openHomePage();
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_level=1.725;
                openHomePage();
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_level=1.9;
                openHomePage();
            }
        });
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());

    }

        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.PreviousButton:
                    openBMIPage();
                    break;

            }
        }

    public void openHomePage() {
        long userId= sharedPreferenceManager.getUser().getId();
    }


    public void openBMIPage() {
//        Intent intent1 = new Intent(this, BMIquestions.class);
//        startActivity(intent1);
    }
}