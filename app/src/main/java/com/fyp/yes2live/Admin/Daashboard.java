package com.fyp.yes2live.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.Admin.Exercise.ExerciseList;
import com.fyp.yes2live.Admin.MealFood.FoodList;
import com.fyp.yes2live.Admin.User.UsersList;
import com.fyp.yes2live.R;
import com.fyp.yes2live.auth.login;
import com.fyp.yes2live.bmiactivity;
import com.fyp.yes2live.homepage;

public class Daashboard extends AppCompatActivity {

    ImageButton previousBtn;
    LinearLayout mealFood,exercise,users;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        previousBtn= findViewById(R.id.PreviousButton);
        mealFood= findViewById(R.id.mealFood_id);
        exercise= findViewById(R.id.exercise_id);
        users= findViewById(R.id.users_id);


        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Daashboard.this, login.class);
                startActivity(intent);
            }
        });

        mealFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Daashboard.this, FoodList.class);
                startActivity(intent1);
            };
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Daashboard.this, ExerciseList.class);
                startActivity(intent1);
            };
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Daashboard.this, UsersList.class);
                startActivity(intent1);
            };
        });
    }
}
