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


import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.auth.login;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.UserBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLevel extends AppCompatActivity implements View.OnClickListener {
    private ImageButton previous;
    private CardView card1,card2,card3,card4,card5;
    private SharedPreferenceManager sharedPreferenceManager;
    private double activity_level;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        previous = findViewById(R.id.PreviousButton);

        card1 = (CardView) findViewById(R.id.level1);
        card2 = (CardView) findViewById(R.id.level2);
        card3 = (CardView) findViewById(R.id.level3);
        card4 = (CardView) findViewById(R.id.level4);
        card5 = (CardView) findViewById(R.id.level5);

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

        apiInterface = APIClient.getClient().create(APIInterface.class);//calling get client method of API client class fpr getting api urls etc (object)
        User user = new User(userId,activity_level);//initialize the user constructor see User class
        Call<UserBaseResponse> call = apiInterface.calculateCalories(user);//calling login method of api interface
        //call.enque is used to catch the response of api
        call.enqueue(new Callback<UserBaseResponse>() {//call.enque have two default methods one is onResponse(that hold the api success and failure  response both) and on failure(hold the response if api is not working)
            @Override
            public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {
                UserBaseResponse userResponse = response.body();
                if (userResponse.getStatus().equals("SUCCESS")) {
                    sharedPreferenceManager.saveActivityLevel();
                    Intent intent1 = new Intent(ActivityLevel.this, homepage.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(ActivityLevel.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                Toast.makeText(ActivityLevel.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void openBMIPage() {
        Intent intent1 = new Intent(this, question1.class);
        startActivity(intent1);
    }
}