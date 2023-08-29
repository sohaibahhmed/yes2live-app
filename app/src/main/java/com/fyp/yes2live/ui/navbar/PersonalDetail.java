package com.fyp.yes2live.ui.navbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.MainActivity;
import com.fyp.yes2live.R;
import com.fyp.yes2live.SharedPreferenceManager;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.homepage;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.UserBaseResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetail extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private APIInterface apiInterface;
    private SharedPreferenceManager sharedPreferenceManager;
    TextView textViewWeight, textViewName, textViewHeight, textViewAge, textViewGender, textViewActivityLevel, textTargetWeight, textViewGoal;
    ImageButton buttonWeight, buttonName, buttonHeight, buttonAge, buttonGender, buttonActivityLevel, buttonGoal, buttonTarget;
    ImageButton previousButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setFocusableInTouchMode(true);
        textViewWeight = (TextView) findViewById(R.id.textViewWeight);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewHeight = (TextView) findViewById(R.id.textViewHeight);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewGender = (TextView) findViewById(R.id.textViewGender);
        textViewActivityLevel = (TextView) findViewById(R.id.textViewActivityLevel);
        textViewGoal = (TextView) findViewById(R.id.textViewGoal);
        textTargetWeight = (TextView) findViewById(R.id.textTargetWeight);
        previousButton= findViewById(R.id.PreviousButton);

        buttonWeight = (ImageButton) findViewById(R.id.buttonWeight);
        buttonName = (ImageButton) findViewById(R.id.buttonName);
        buttonHeight = (ImageButton) findViewById(R.id.buttonHeight);
        buttonAge = (ImageButton) findViewById(R.id.buttonAge);
        buttonGender = (ImageButton) findViewById(R.id.buttonGender);
        buttonActivityLevel = (ImageButton) findViewById(R.id.buttonActivityLevel);
        buttonGoal = (ImageButton) findViewById(R.id.buttonGoal);
        buttonTarget = (ImageButton) findViewById(R.id.buttonTargetWeight);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PersonalDetail.this, homepage.class);
                startActivity(intent);
            }
        });

        //getUserData
        long user_id=sharedPreferenceManager.getUser().getId();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserBaseResponse> call = apiInterface.userProfile(user_id);
        call.enqueue(new Callback<UserBaseResponse>() {

            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {

                UserBaseResponse getUserBioResponse = response.body();
                if (response.isSuccessful()) {
                    if (getUserBioResponse.getStatus().equals("SUCCESS")) {
                        User user = getUserBioResponse.getPayload();
                        textViewGoal.setHint(String.valueOf(user.getPeerWeekGoal()));
                        textViewAge.setHint(String.valueOf(user.getAge()));
                        textViewHeight.setHint(String.valueOf(user.getHeight()));
                        textViewWeight.setHint(String.valueOf(user.getWeight()));
                        textTargetWeight.setHint(String.valueOf(user.getTargetWeight()));
                        textViewActivityLevel.setHint(String.valueOf(user.getActivityLevel()));
                        textViewGender.setHint(user.getGender());


                    } else {
                        Toast.makeText(PersonalDetail.this, getUserBioResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
//                    Toast.makeText(CalorieCounter.this, getPerDayLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                Toast.makeText(PersonalDetail.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void bottomNavClick() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navHome:
                    Intent intent2 = new Intent(PersonalDetail.this, homepage.class);
                    startActivity(intent2);
                    break;
                case R.id.navProfile:
                    Intent intent3 = new Intent(PersonalDetail.this, PersonalDetail.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
    }
}
