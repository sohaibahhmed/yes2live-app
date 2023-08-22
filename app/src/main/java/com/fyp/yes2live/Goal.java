package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fyp.yes2live.apiConfig.APIInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Goal extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        bottomNavClick();
    }

    private void bottomNavClick() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {


            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navTrack:
                    Intent intent = new Intent(Goal.this, Track.class);
                    startActivity(intent);
                    break;
                case R.id.navHome:
                    Intent intent2 = new Intent(Goal.this, homepage.class);
                    startActivity(intent2);
                    break;
            }
            return true;
        });
    }
}