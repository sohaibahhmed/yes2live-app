package com.fyp.yes2live;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavClick();
    }


    private void bottomNavClick() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {


            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navGoal:
                    Intent intent = new Intent(Profile.this, Goal.class);
                    startActivity(intent);
                    break;
                case R.id.navProfile:
                    Intent intent2 = new Intent(Profile.this, Profile.class);
                    startActivity(intent2);
                    break;
                case R.id.navTrack:
                    Intent intent3 = new Intent(Profile.this, Track.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
    }

}