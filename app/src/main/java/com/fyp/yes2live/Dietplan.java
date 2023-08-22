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
import androidx.cardview.widget.CardView;

import com.fyp.yes2live.apiConfig.APIClient2;
import com.fyp.yes2live.apiConfig.DietplanApiInterface;
import com.fyp.yes2live.response.DietPlanResponse;
import com.fyp.yes2live.ui.navbar.PersonalDetail;
import com.fyp.yes2live.ui.navbar.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dietplan extends AppCompatActivity {

    ImageButton previous;
    TextView total_calorie, total_fat,total_carb,total_protein;
    SharedPreferenceManager sharedPreferenceManager;
    private DietplanApiInterface dietplanApiInterface;
    String diseaseCategory;
    int b_cal, l_cal, d_cal, s_cal;
    private BottomNavigationView bottomNavigationView;
    TextView breakDiet, lunchDiet, SnacksDiet, DinnerDiet;
    TextView plan1, plan2, plan3, plan4;
    CardView b_card, l_card, s_card, d_card;
    TextView foodText, foodCalories,EatenCalories, w_plan1, w_plan2;
    int bmi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietplan);
//        getSupportActionBar().hide();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        previous=findViewById(R.id.upBotton);
        w_plan1=findViewById(R.id.weekplan1);
        w_plan2=findViewById(R.id.weekplan2);
        b_card=findViewById(R.id.card1);
        l_card=findViewById(R.id.card2);
        s_card=findViewById(R.id.card3);
        d_card=findViewById(R.id.card4);
        foodText=findViewById(R.id.textFood);
        foodCalories=findViewById(R.id.foodCal);
        total_calorie=findViewById(R.id.d_calorie);
        //  total_d_eaten_cal=findViewById(R.id.eaten_calories);
        EatenCalories=findViewById(R.id.eatenCal);
        total_fat=findViewById(R.id.totalFat);
        breakDiet=findViewById(R.id.bf_diet);
        lunchDiet=findViewById(R.id.lunchDiet);
        SnacksDiet=findViewById(R.id.snackDiet);
        DinnerDiet=findViewById(R.id.dinnerDiet);
        total_carb=findViewById(R.id.totalCarb);
        total_protein=findViewById(R.id.totalProtein);
        //  breakfast_cal=findViewById(R.id.Breakfast_calorie);
        plan1=findViewById(R.id.plan1);
        plan2=findViewById(R.id.plan2);
        plan3=findViewById(R.id.plan3);
        plan4=findViewById(R.id.plan4);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(Dietplan.this, homepage.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_person:
                        Intent intent2 = new Intent(Dietplan.this, PersonalDetail.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_settings:
                        Intent intent3 = new Intent(Dietplan.this, Settings.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });


        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        b_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodText.setText("Breakfast");
                foodCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getB_calories()));
            }
        });

        l_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodText.setText("Lunch");
                foodCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getL_calories()));

            }
        });

        s_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodText.setText("Snacks");
                foodCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getS_calories()));
                //foodCalories.setText(snacks);

            }
        });

        d_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodText.setText("Dinner");
                foodCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getD_calories()));

            }
        });
        foodCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getB_calories()));

        total_fat.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getFat()));
        total_carb.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getCarbs()));
        total_protein.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getProteins()));
        EatenCalories.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getDisease_total_calories()));
        total_calorie.setText(String.valueOf(sharedPreferenceManager.getUserDiabeticAssessment().getDisease_total_calories()));

        //Get Values from shared preference
        diseaseCategory=sharedPreferenceManager.getUserDiabeticAssessment().getDisease_category();
        b_cal=sharedPreferenceManager.getUserDiabeticAssessment().getB_calories();
        l_cal=sharedPreferenceManager.getUserDiabeticAssessment().getL_calories();
        s_cal=sharedPreferenceManager.getUserDiabeticAssessment().getS_calories();
        d_cal=sharedPreferenceManager.getUserDiabeticAssessment().getD_calories();
        bmi= (int) sharedPreferenceManager.getUserDiabeticAssessment().getBmi();


        plan1.setTypeface(null, Typeface.BOLD);
        plan2.setTypeface(null,Typeface.NORMAL);
        plan3.setTypeface(null,Typeface.NORMAL);
        plan4.setTypeface(null,Typeface.NORMAL);

        if(sharedPreferenceManager.isPlan1()){
            // plan1.setTextColor();
            breakDiet.setText(sharedPreferenceManager.getPlan1().getBreakfastFoodPackage());
            lunchDiet.setText(sharedPreferenceManager.getPlan1().getLunchFoodPackage());
            SnacksDiet.setText(sharedPreferenceManager.getPlan1().getSnackFoodPackage());
            DinnerDiet.setText(sharedPreferenceManager.getPlan1().getDinnerFoodPackage());
        }
        else {
            dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
            Call<DietPlanResponse> call = dietplanApiInterface.user_dietPlan(diseaseCategory, b_cal, l_cal, s_cal, d_cal, bmi);

            call.enqueue(new Callback<DietPlanResponse>() {
                @Override
                public void onResponse(Call<DietPlanResponse> call, Response<DietPlanResponse> response) {

                    DietPlanResponse dietPlanResponse = response.body();

                    if (response.isSuccessful()) {

                        Log.d("magic", "dietplan" + response.errorBody());

                        if (dietPlanResponse.getStatus().equals("SUCCESS")) {
                            sharedPreferenceManager.saveplan1(dietPlanResponse.getDietPlanPayload());
                            Log.d("Magic", "dietplan" + dietPlanResponse.getDietPlanPayload().toString());
                            breakDiet.setText(dietPlanResponse.getDietPlanPayload().getBreakfastFoodPackage().toString());
                            lunchDiet.setText(dietPlanResponse.getDietPlanPayload().getLunchFoodPackage().toString());
                            SnacksDiet.setText(dietPlanResponse.getDietPlanPayload().getSnackFoodPackage().toString());
                            DinnerDiet.setText(dietPlanResponse.getDietPlanPayload().getDinnerFoodPackage().toString());
                        } else {
                            Toast.makeText(Dietplan.this, dietPlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    } else {}
                }

                @Override
                public void onFailure(Call<DietPlanResponse> call, Throwable t) {
                    Toast.makeText(Dietplan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        //Plan1
        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                w_plan1.setText("1st WEEK PLAN");
                w_plan2.setText("2nd WEEK PLAN");
                plan1.setTypeface(null, Typeface.BOLD);
                plan2.setTypeface(null,Typeface.NORMAL);
                plan3.setTypeface(null,Typeface.NORMAL);
                plan4.setTypeface(null,Typeface.NORMAL);
                // Toast.makeText(Dietplan.this, "plan1 call", Toast.LENGTH_SHORT).show();
                Log.d("magic", "onClick: "+ sharedPreferenceManager.isPlan1());
                Log.d("magic", "onClick: "+ sharedPreferenceManager.getPlan1().getBreakfastFoodPackage());

                if(sharedPreferenceManager.isPlan1()){
                    breakDiet.setText(sharedPreferenceManager.getPlan1().getBreakfastFoodPackage());
                    lunchDiet.setText(sharedPreferenceManager.getPlan1().getLunchFoodPackage());
                    SnacksDiet.setText(sharedPreferenceManager.getPlan1().getSnackFoodPackage());
                    DinnerDiet.setText(sharedPreferenceManager.getPlan1().getDinnerFoodPackage());


                }
                else {
                    dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                    Call<DietPlanResponse> call = dietplanApiInterface.user_dietPlan(diseaseCategory, b_cal, l_cal, s_cal, d_cal, bmi);

                    call.enqueue(new Callback<DietPlanResponse>() {
                        @Override
                        public void onResponse(Call<DietPlanResponse> call, Response<DietPlanResponse> response) {
                            DietPlanResponse dietPlanResponse = response.body();
                            if (response.isSuccessful()) {
                                Log.d("magic", "dietplan" + response.errorBody());
                                if (dietPlanResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveplan1(dietPlanResponse.getDietPlanPayload());
                                    Log.d("Magic", "dietplan" + dietPlanResponse.getDietPlanPayload().toString());
                                    breakDiet.setText(dietPlanResponse.getDietPlanPayload().getBreakfastFoodPackage().toString());
                                    lunchDiet.setText(dietPlanResponse.getDietPlanPayload().getLunchFoodPackage().toString());
                                    SnacksDiet.setText(dietPlanResponse.getDietPlanPayload().getSnackFoodPackage().toString());
                                    DinnerDiet.setText(dietPlanResponse.getDietPlanPayload().getDinnerFoodPackage().toString());
                                } else {
                                    Toast.makeText(Dietplan.this, dietPlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            } else {}
                        }

                        @Override
                        public void onFailure(Call<DietPlanResponse> call, Throwable t) {
                            Toast.makeText(Dietplan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                plan1.setTypeface(null, Typeface.NORMAL);
                plan2.setTypeface(null,Typeface.BOLD);
                plan3.setTypeface(null,Typeface.NORMAL);
                plan4.setTypeface(null,Typeface.NORMAL);

                w_plan1.setText("2nd WEEK PLAN");
                w_plan2.setText("3rd WEEK PLAN");
                Log.d("magic", "onClick: "+ sharedPreferenceManager.isPlan2());
                Log.d("magic", "onClick: "+ sharedPreferenceManager.getPlan2().getBreakfastFoodPackage());

                if(sharedPreferenceManager.isPlan2()){
                    breakDiet.setText(sharedPreferenceManager.getPlan2().getBreakfastFoodPackage());
                    lunchDiet.setText(sharedPreferenceManager.getPlan2().getLunchFoodPackage());
                    SnacksDiet.setText(sharedPreferenceManager.getPlan2().getSnackFoodPackage());
                    DinnerDiet.setText(sharedPreferenceManager.getPlan2().getDinnerFoodPackage());
                }
                else {
                    dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                    Call<DietPlanResponse> call = dietplanApiInterface.user_dietPlan(diseaseCategory, b_cal, l_cal, s_cal, d_cal, bmi);

                    call.enqueue(new Callback<DietPlanResponse>() {
                        @Override
                        public void onResponse(Call<DietPlanResponse> call, Response<DietPlanResponse> response) {
                            DietPlanResponse dietPlanResponse = response.body();
                            if (response.isSuccessful()) {
                                Log.d("magic", "dietplan" + response.errorBody());
                                if (dietPlanResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveplan2(dietPlanResponse.getDietPlanPayload());
                                    Log.d("Magic", "dietplan" + dietPlanResponse.getDietPlanPayload().toString());
                                    breakDiet.setText(dietPlanResponse.getDietPlanPayload().getBreakfastFoodPackage().toString());
                                    lunchDiet.setText(dietPlanResponse.getDietPlanPayload().getLunchFoodPackage().toString());
                                    SnacksDiet.setText(dietPlanResponse.getDietPlanPayload().getSnackFoodPackage().toString());
                                    DinnerDiet.setText(dietPlanResponse.getDietPlanPayload().getDinnerFoodPackage().toString());
                                } else {
                                    Toast.makeText(Dietplan.this, dietPlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            } else {}
                        }

                        @Override
                        public void onFailure(Call<DietPlanResponse> call, Throwable t) {
                            Toast.makeText(Dietplan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        plan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                plan1.setTypeface(null, Typeface.NORMAL);
                plan2.setTypeface(null,Typeface.NORMAL);
                plan3.setTypeface(null,Typeface.BOLD);
                plan4.setTypeface(null,Typeface.NORMAL);

                w_plan1.setText("3rd WEEK PLAN");
                w_plan2.setText("4th WEEK PLAN");
                //Toast.makeText(Dietplan.this, "plan3 call", Toast.LENGTH_SHORT).show();
                Log.d("magic", "onClick: "+ sharedPreferenceManager.isPlan3());
                Log.d("magic", "onClick: "+ sharedPreferenceManager.getPlan3().getBreakfastFoodPackage());

                if(sharedPreferenceManager.isPlan3()){
                    breakDiet.setText(sharedPreferenceManager.getPlan3().getBreakfastFoodPackage());
                    lunchDiet.setText(sharedPreferenceManager.getPlan3().getLunchFoodPackage());
                    SnacksDiet.setText(sharedPreferenceManager.getPlan3().getSnackFoodPackage());
                    DinnerDiet.setText(sharedPreferenceManager.getPlan3().getDinnerFoodPackage());
                }
                else {

                    dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                    Call<DietPlanResponse> call = dietplanApiInterface.user_dietPlan(diseaseCategory, b_cal, l_cal, s_cal, d_cal, bmi);

                    call.enqueue(new Callback<DietPlanResponse>() {
                        @Override
                        public void onResponse(Call<DietPlanResponse> call, Response<DietPlanResponse> response) {
                            DietPlanResponse dietPlanResponse = response.body();
                            if (response.isSuccessful()) {
                                Log.d("magic", "dietplan" + response.errorBody());
                                if (dietPlanResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveplan3(dietPlanResponse.getDietPlanPayload());
                                    Log.d("Magic", "dietplan" + dietPlanResponse.getDietPlanPayload().toString());
                                    breakDiet.setText(dietPlanResponse.getDietPlanPayload().getBreakfastFoodPackage().toString());
                                    lunchDiet.setText(dietPlanResponse.getDietPlanPayload().getLunchFoodPackage().toString());
                                    SnacksDiet.setText(dietPlanResponse.getDietPlanPayload().getSnackFoodPackage().toString());
                                    DinnerDiet.setText(dietPlanResponse.getDietPlanPayload().getDinnerFoodPackage().toString());
                                } else {
                                    Toast.makeText(Dietplan.this, dietPlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            } else {}
                        }
                        @Override
                        public void onFailure(Call<DietPlanResponse> call, Throwable t) {
                            Toast.makeText(Dietplan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                plan4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        plan1.setTypeface(null, Typeface.NORMAL);
                        plan2.setTypeface(null,Typeface.NORMAL);
                        plan3.setTypeface(null,Typeface.NORMAL);
                        plan4.setTypeface(null,Typeface.BOLD);

                        w_plan1.setText("4th WEEK PLAN");
                        w_plan2.setText("1st WEEK PLAN");
                        Log.d("magic", "onClick: "+ sharedPreferenceManager.isPlan4());
                        Log.d("magic", "onClick: "+ sharedPreferenceManager.getPlan4().getBreakfastFoodPackage());

                        if(sharedPreferenceManager.isPlan4()){
                            breakDiet.setText(sharedPreferenceManager.getPlan4().getBreakfastFoodPackage());
                            lunchDiet.setText(sharedPreferenceManager.getPlan4().getLunchFoodPackage());
                            SnacksDiet.setText(sharedPreferenceManager.getPlan4().getSnackFoodPackage());
                            DinnerDiet.setText(sharedPreferenceManager.getPlan4().getDinnerFoodPackage());
                        }
                        else {

                            dietplanApiInterface = APIClient2.getClient().create(DietplanApiInterface.class);
                            Call<DietPlanResponse> call = dietplanApiInterface.user_dietPlan(diseaseCategory, b_cal, l_cal, s_cal, d_cal, bmi);

                            call.enqueue(new Callback<DietPlanResponse>() {
                                @Override
                                public void onResponse(Call<DietPlanResponse> call, Response<DietPlanResponse> response) {
                                    DietPlanResponse dietPlanResponse = response.body();
                                    if (response.isSuccessful()) {
                                        Log.d("magic", "dietplan" + response.errorBody());
                                        if (dietPlanResponse.getStatus().equals("SUCCESS")) {
                                            sharedPreferenceManager.saveplan4(dietPlanResponse.getDietPlanPayload());
                                            Log.d("Magic", "dietplan" + dietPlanResponse.getDietPlanPayload().toString());
                                            breakDiet.setText(dietPlanResponse.getDietPlanPayload().getBreakfastFoodPackage().toString());
                                            lunchDiet.setText(dietPlanResponse.getDietPlanPayload().getLunchFoodPackage().toString());
                                            SnacksDiet.setText(dietPlanResponse.getDietPlanPayload().getSnackFoodPackage().toString());
                                            DinnerDiet.setText(dietPlanResponse.getDietPlanPayload().getDinnerFoodPackage().toString());
                                        } else {
                                            Toast.makeText(Dietplan.this, dietPlanResponse.getCode(), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {}
                                }

                                @Override
                                public void onFailure(Call<DietPlanResponse> call, Throwable t) {
                                    Toast.makeText(Dietplan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dietplan.this, homepage.class);
                startActivity(i);
            }
        });
    }
}