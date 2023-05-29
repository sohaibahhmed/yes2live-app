package com.fyp.yes2live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.fyp.yes2live.ui.navbar.PersonalDetail;
import com.fyp.yes2live.ui.navbar.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private ImageSlider imageSlider;
    private RelativeLayout bmiModule;

    private RelativeLayout bfModule;

    private RelativeLayout weightlossModule;
    private RelativeLayout dietryModule;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        imageSlider = findViewById(R.id.imageSlider);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(homepage.this, homepage.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_person:
                        Intent intent2 = new Intent(homepage.this, PersonalDetail.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_settings:
                        Intent intent3 = new Intent(homepage.this, Settings.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });


        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.dietplanslider1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.dietplanslider1,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        bmiModule = (RelativeLayout) findViewById(R.id.bmi);

        bmiModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homepage.this, bmiactivity.class);
                startActivity(intent1);
            };
        });

        dietryModule = (RelativeLayout) findViewById(R.id.dietryModule);

        dietryModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homepage.this, dietaryinfo.class);
                startActivity(intent1);
            };
        });
        bfModule = (RelativeLayout) findViewById(R.id.bms);

        bfModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homepage.this, bfactivity.class);
                startActivity(intent1);
            };
        });
        weightlossModule = (RelativeLayout) findViewById(R.id.mealLogModule);

        weightlossModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homepage.this, target_weight.class);
                startActivity(intent1);
            };
        });
    }
}