package com.fyp.yes2live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.fyp.yes2live.auth.login;
import com.fyp.yes2live.ui.navbar.PersonalDetail;
import com.fyp.yes2live.ui.navbar.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class homepage extends AppCompatActivity {
    private DrawerLayout drawerLayout;//line 20(drawerlayout)
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;//auto three lines on top (side nav bar view)
    private BottomNavigationView bottomNavigationView;
    private ImageSlider imageSlider;
    private RelativeLayout bmiModule;

    private RelativeLayout bfModule;

    private RelativeLayout weightlossModule;
    private RelativeLayout dietryModule;
    private String userName;
    private TextView txtUserName,txtUser,txtEmail;

    SharedPreferenceManager sharedPreferenceManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        userName = sharedPreferenceManager.getUser().getUsername();
        drawerLayout = findViewById(R.id.drawer);
        txtUserName = findViewById(R.id.username);

        navigationView = findViewById(R.id.navigation_view);
        View hView = navigationView.getHeaderView(0);
        txtUser =  hView.findViewById(R.id.textUserName);
        txtEmail = hView.findViewById(R.id.textemail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        imageSlider = findViewById(R.id.imageSlider);
        txtUserName.setText(txtUserName.getText()+userName);
        txtUser.setText(userName);
        txtEmail.setText(sharedPreferenceManager.getUser().getEmail());
// top toolbar set (three icons) or sidebar
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.logout:
                        sharedPreferenceManager.logout();
                        Intent i = new Intent(homepage.this, login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    case R.id.nav_dietry:
                        if (sharedPreferenceManager.isDataSaved() || sharedPreferenceManager.isUserWarning()) {
                            Intent intent = new Intent(homepage.this, Summary.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Intent intent1 = new Intent(homepage.this, dietaryinfo.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent1);
                        }

                    case R.id.nav_weight:

                        if(sharedPreferenceManager.isSavedTargetWeight()){
                            if(sharedPreferenceManager.getGoalStartDate() != null){
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    LocalDate today = LocalDate.now();
                                    LocalDate goalStartDate = LocalDate.parse(sharedPreferenceManager.getGoalStartDate());
                                    if(ChronoUnit.DAYS.between(goalStartDate, today) > 7){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(homepage.this);
                                        //Set body message of Dialog
                                        builder.setMessage("You complete your goal time, please update check your weight and update the profile")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Intent intent1 = new Intent(homepage.this, question1.class);
                                                        startActivity(intent1);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog dialog = builder.create();
                                        //dialog.closeOptionsMenu();
                                        dialog.setTitle("ALert!!");
                                        dialog.show();
                                    }else{
                                        Intent intent1 = new Intent(homepage.this, Track.class);
                                        startActivity(intent1);
                                    }
                                }
                            }
                        }else{
                            Intent targetIntent = new Intent(homepage.this, target_weight.class);
                            targetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(targetIntent);
                        }

                    case R.id.Setting:
                        Intent sett = new Intent(homepage.this, Settings.class);
                        sett.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(sett);
                }
                return true;
            }
        });
// set the home setting and profile on bottom
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {// bottom nevigation method
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

// code to add pictures (time is mention ) on image slider
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.dietplanslider1, ScaleTypes.CENTER_CROP));// images should be in center
        slideModels.add(new SlideModel(R.drawable.dietplanslider1,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);

// click on features where to go
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
                if (sharedPreferenceManager.isDataSaved() || sharedPreferenceManager.isUserWarning()) {
                    Intent intent = new Intent(homepage.this, Summary.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Intent intent1 = new Intent(homepage.this, dietaryinfo.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent1);
                }
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
                if(sharedPreferenceManager.isSavedTargetWeight()){
                    if(sharedPreferenceManager.getGoalStartDate() != null){

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            LocalDate today = LocalDate.now();
                            LocalDate goalStartDate = LocalDate.parse(sharedPreferenceManager.getGoalStartDate());
                            if(ChronoUnit.DAYS.between(goalStartDate, today) > 7){
                                AlertDialog.Builder builder = new AlertDialog.Builder(homepage.this);
                                //Set body message of Dialog
                                builder.setMessage("You complete your goal time, please update check your weight and update the profile")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent1 = new Intent(homepage.this, question1.class);
                                                startActivity(intent1);
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog dialog = builder.create();
                                //dialog.closeOptionsMenu();
                                dialog.setTitle("ALert!!");
                                dialog.show();
                            }else{
                                Intent intent1 = new Intent(homepage.this, Track.class);
                                startActivity(intent1);
                            }
                        }
                    }
                }else{
                    Intent intent = new Intent(homepage.this, target_weight.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            };
        });

    }
}