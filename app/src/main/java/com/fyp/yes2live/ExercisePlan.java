package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.apiConfig.APIClient2;
import com.fyp.yes2live.apiConfig.DietplanApiInterface;
import com.fyp.yes2live.response.ExercisePlanResponse;
import com.fyp.yes2live.ui.navbar.PersonalDetail;
import com.fyp.yes2live.ui.navbar.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExercisePlan extends AppCompatActivity {

    ImageButton previous;
    float al;
    int userprefer;
    int time;
    int age;
    TextView burnedcal;
    SharedPreferenceManager sharedPreferenceManager;
    private DietplanApiInterface dietplanApiInterface;
    private BottomNavigationView bottomNavigationView;
    TextView plan1, plan2;
    String diseaseCategory;
    TextView plan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);
        getSupportActionBar().hide();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        previous=findViewById(R.id.upBotton);
        plan=findViewById(R.id.explan);
        plan1=findViewById(R.id.ex_plan1);
        plan2=findViewById(R.id.ex_plan2);

        //SharedPreference
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        al=sharedPreferenceManager.getUserDiabeticAssessment().getActivity_level();
        userprefer=sharedPreferenceManager.getUserPreference();
        age=sharedPreferenceManager.getUserDiabeticAssessment().getAge();
        time=sharedPreferenceManager.getUserDiabeticAssessment().getTime_in_mins();
        diseaseCategory=sharedPreferenceManager.getUserDiabeticAssessment().getDisease_category();
       // burnedcal.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().get()));
        Log.d("magic", "activitylevel: "+ al);
        Log.d("magic", "userprefer: "+ userprefer);
        Log.d("magic", "age: "+ age);
        Log.d("magic", "time: "+ time);
        Log.d("magic", "diseaseCategory: "+ diseaseCategory);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ExercisePlan.this,Summary.class);
                startActivity(i);
            }
        });

        if(sharedPreferenceManager.isExercisePlan1()){
            plan.setText(sharedPreferenceManager.getExPlan1().getExerciseFoodPackage());
            plan1.setTypeface(null, Typeface.BOLD);
            plan2.setTypeface(null,Typeface.NORMAL);
        }
        else {
            plan1.setTypeface(null, Typeface.BOLD);
            plan2.setTypeface(null,Typeface.NORMAL);
            dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
            Call<ExercisePlanResponse> call = dietplanApiInterface.exercise_dietPlan(diseaseCategory,1,time,age,userprefer);

            call.enqueue(new Callback<ExercisePlanResponse>() {
                @Override
                public void onResponse(Call<ExercisePlanResponse> call, Response<ExercisePlanResponse> response) {
                    ExercisePlanResponse exercisePlanResponse = response.body();
                    if (response.isSuccessful()) {
                        Log.d("magic", "ExercisePlanResponse" + response.errorBody());
                        if (exercisePlanResponse.getStatus().equals("SUCCESS")) {
                            sharedPreferenceManager.saveExerciseplan1(exercisePlanResponse.getExercisePlanPayload());
                            Log.d("Magic", "exdietplan" + exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage());
                            plan.setText(exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage().toString());
                        } else {
                            Toast.makeText(ExercisePlan.this, exercisePlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    } else {}
                }

                @Override
                public void onFailure(Call<ExercisePlanResponse> call, Throwable t) {
                    Toast.makeText(ExercisePlan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferenceManager.isExercisePlan1()){
                    plan1.setTypeface(null, Typeface.BOLD);
                    plan2.setTypeface(null,Typeface.NORMAL);
                    Toast.makeText(ExercisePlan.this, "plan1", Toast.LENGTH_SHORT).show();
                    // plan1.setTextCol or();
                    plan.setText(sharedPreferenceManager.getExPlan1().getExerciseFoodPackage());
                }
                else {
                    plan1.setTypeface(null, Typeface.BOLD);
                    plan2.setTypeface(null,Typeface.NORMAL);


                    dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                    Call<ExercisePlanResponse> call = dietplanApiInterface.exercise_dietPlan(diseaseCategory,1,time,age,userprefer);

                    call.enqueue(new Callback<ExercisePlanResponse>() {
                        @Override
                        public void onResponse(Call<ExercisePlanResponse> call, Response<ExercisePlanResponse> response) {
                            ExercisePlanResponse exercisePlanResponse = response.body();
                            if (response.isSuccessful()) {
                                Log.d("magic", "ExercisePlanResponse" + response.errorBody());
                                if (exercisePlanResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveExerciseplan1(exercisePlanResponse.getExercisePlanPayload());
                                    Log.d("Magic", "exdietplan" + exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage());
                                    plan.setText(exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage().toString());
                                } else {
                                    Toast.makeText(ExercisePlan.this, exercisePlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            } else {}
                        }

                        @Override
                        public void onFailure(Call<ExercisePlanResponse> call, Throwable t) {
                            Toast.makeText(ExercisePlan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferenceManager.isExercisePlan2()){
                    // plan1.setTextColor();
                    plan2.setTypeface(null, Typeface.BOLD);
                    plan1.setTypeface(null,Typeface.NORMAL);

                    plan.setText(sharedPreferenceManager.getExPlan2().getExerciseFoodPackage());
                }
                else {
                    plan2.setTypeface(null, Typeface.BOLD);
                    plan1.setTypeface(null,Typeface.NORMAL);

                    dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                    Call<ExercisePlanResponse> call = dietplanApiInterface.exercise_dietPlan(diseaseCategory,1,time,age,userprefer);

                    call.enqueue(new Callback<ExercisePlanResponse>() {
                        @Override
                        public void onResponse(Call<ExercisePlanResponse> call, Response<ExercisePlanResponse> response) {

                            ExercisePlanResponse exercisePlanResponse = response.body();

                            if (response.isSuccessful()) {

                                Log.d("magic", "ExercisePlanResponse" + response.errorBody());

                                if (exercisePlanResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveExerciseplan2(exercisePlanResponse.getExercisePlanPayload());
                                    Log.d("Magic", "exdietplan" + exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage());
                                    plan.setText(exercisePlanResponse.getExercisePlanPayload().getExerciseFoodPackage().toString());
                                } else {
                                    Toast.makeText(ExercisePlan.this, exercisePlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            } else {}
                        }

                        @Override
                        public void onFailure(Call<ExercisePlanResponse> call, Throwable t) {
                            Toast.makeText(ExercisePlan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(ExercisePlan.this, homepage.class);
                        startActivity(intent);
                        break;

                    case R.id.Dietplan:
                        Intent intent4 = new Intent(ExercisePlan.this, Dietplan.class);
                        startActivity(intent4);
                        break;

                    case R.id.navigation_person:
                        Intent intent2 = new Intent(ExercisePlan.this, PersonalDetail.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_settings:
                        Intent intent3 = new Intent(ExercisePlan.this, Settings.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });


    }
}