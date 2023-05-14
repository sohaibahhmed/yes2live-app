package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class target_home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_home);
        bottomNavClick();
    }

    private void bottomNavClick() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {


            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navGoal:
                    Intent intent = new Intent(target_home.this, Goal.class);
                    startActivity(intent);
                    break;
                case R.id.navProfile:
                    Intent intent2 = new Intent(target_home.this, Profile.class);
                    startActivity(intent2);
                    break;
                case R.id.navTrack:
                    Intent intent3 = new Intent(target_home.this, Track.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
    }
}