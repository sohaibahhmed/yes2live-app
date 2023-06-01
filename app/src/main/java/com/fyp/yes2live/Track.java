package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.yes2live.Notifications.BreakfastBroadcast;
import com.fyp.yes2live.Notifications.DailyBroadcast;
import com.fyp.yes2live.Notifications.DinnerBroadcast;
import com.fyp.yes2live.Notifications.EveningSnackBroadcast;
import com.fyp.yes2live.Notifications.ExerciseBroadcast;
import com.fyp.yes2live.Notifications.LunchBroadcast;
import com.fyp.yes2live.Notifications.MorningSnackBroadcast;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.GetExerciseListResponse;
import com.fyp.yes2live.response.GetLogDataResponse;
import com.fyp.yes2live.response.GetLogListResponse;
import com.fyp.yes2live.response.GetPerDayLogDataResponse;
import com.fyp.yes2live.response.UserBaseResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Track extends AppCompatActivity {

    private APIInterface apiInterface;
    private ProgressBar progressBar;
    private SharedPreferenceManager sharedPreferenceManager;
    private int calories,intake_calories,valueProgress;
    private Button dateHistory,breakfast, m_snack, dinner, exercise, lunch, e_snacks;
    private DatePickerDialog datePickerDialog;
    private RecyclerView recyclerView,l_recyclerView,ms_recyclerView,es_recyclerView,dinner_recyclerView,exercise_recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public static int Type;
    private BottomNavigationView bottomNavigationView;
    private TextView B_intake,L_intake,D_intake,ES_intake,MS_intake,Exercise_burned,
            calorie_intake,Protein_intake,Carbs_intake,Fats_intake,txt_name, txt_cal, txt_serve, txt_quan,
            per_day_protein, per_day_fat, per_day_carb, per_day_calorie,bf_cal, evening_snack_cal, lunch_cal, morning_snack_cal, dinner_cal;
    private LocalDate date;
    private ImageButton previousButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        progressBar=findViewById(R.id.circularProgressIndicator);
        dateHistory=findViewById(R.id.datePickerButton);
        dateHistory.setText(getTodayDate());
        previousButton=findViewById(R.id.PreviousButton);
        breakfast = findViewById(R.id.addBreakfast);
        lunch = findViewById(R.id.addLunch);
        e_snacks = findViewById(R.id.addEveningSnacks);
        m_snack = findViewById(R.id.addMorningSnacks);
        dinner = findViewById(R.id.addDinner);
        exercise = findViewById(R.id.addExercise);
        bf_cal = findViewById(R.id.b_cal);
        evening_snack_cal = findViewById(R.id.es_cal);
        lunch_cal = findViewById(R.id.l_cal);
        dinner_cal = findViewById(R.id.d_cal);
        morning_snack_cal = findViewById(R.id.ms_cal);

        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(intake_calories < calories){
                    progressBar.setProgress(intake_calories);
                    handler.postDelayed(this,200);
                }
                else {
                    handler.removeCallbacks(this);
                }
            }
        },200);

        //PerDayLog
        per_day_protein = findViewById(R.id.TotalProtein);
        per_day_carb = findViewById(R.id.totalCarb);
        per_day_fat = findViewById(R.id.TotalFat);
        per_day_calorie = findViewById(R.id.TotalCal);
        B_intake = findViewById(R.id.totalIntakeBCalories);
        D_intake = findViewById(R.id.totalIntakeDCalories);
        ES_intake = findViewById(R.id.totalIntakeESCalories);
        MS_intake = findViewById(R.id.totalIntakeMSCalories);
        L_intake = findViewById(R.id.totalIntakeLCalories);
        Exercise_burned = findViewById(R.id.totalIntakeECalories);
        Protein_intake = findViewById(R.id.proteinIntake);
        Carbs_intake = findViewById(R.id.carbIntake);
        Fats_intake = findViewById(R.id.FatIntake);
        calorie_intake = findViewById(R.id.calorieIntake);
        initDatePicker();
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        //Breakfast RecyclerView
        recyclerView = findViewById(R.id.FoodLog_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(5);

        //Lunch RecyclerView
        l_recyclerView = findViewById(R.id.LunchLog_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        l_recyclerView.setLayoutManager(layoutManager);
        l_recyclerView.setItemViewCacheSize(5);

        //Dinner RecyclerView
        dinner_recyclerView = findViewById(R.id.din_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        dinner_recyclerView.setLayoutManager(layoutManager);
        dinner_recyclerView.setItemViewCacheSize(5);

        //MorningSnacks RecyclerView
        ms_recyclerView = findViewById(R.id.msnacks_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        ms_recyclerView.setLayoutManager(layoutManager);
        ms_recyclerView.setItemViewCacheSize(5);

        //EveningSnacks RecyclerView
        es_recyclerView = findViewById(R.id.esnacks_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        es_recyclerView.setLayoutManager(layoutManager);
        es_recyclerView.setItemViewCacheSize(5);

        //Exercise RecyclerView
        exercise_recyclerView = findViewById(R.id.ex_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        exercise_recyclerView.setLayoutManager(layoutManager);
        exercise_recyclerView.setItemViewCacheSize(5);

//      checking perday calories
        valueProgress= Integer.parseInt(per_day_calorie.getText().toString());
        String cal= calorie_intake.getText().toString();
//
        if(calorie_intake.getText().toString().equals("0")){
            NotificationChannel();
        }
        if(Exercise_burned.getText().toString().equals(0)){
            e_NotificationChannel();
        }

        //Default  Notification
        b_NotificationChannel();
        l_NotificationChannel();
        d_NotificationChannel();
        MSNotification();
        EveningSnacksNotification();

//        //Previous Button
//        previousButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Track.this,homepage.class);
//                startActivity(i);
//            }
//        });

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        long user_id = sharedPreferenceManager.getUser().getId();

        String calorie = getIntent().getStringExtra("per_day_calorie");
        per_day_calorie.setText(calorie);

        //Log Card
        txt_name = findViewById(R.id.txt_food_name);
        txt_cal = findViewById(R.id.txt_calories);
        txt_quan = findViewById(R.id.txt_quantity);
        txt_serve = findViewById(R.id.txt_serving);


        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Track.this, SearchItemActivity.class);
                Type = 1;
                startActivity(i);

            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = 2;
                Intent i = new Intent(Track.this, SearchItemActivity.class);
                startActivity(i);
            }
        });

        m_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Track.this, SearchItemActivity.class);
                Type = 3;
                startActivity(i);
            }
        });

        e_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = 4;
                Intent i = new Intent(Track.this, SearchItemActivity.class);
                startActivity(i);
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = 5;
                Intent i = new Intent(Track.this, SearchItemActivity.class);
                startActivity(i);
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type = 6;
                Intent i = new Intent(Track.this, SearchExercise.class);
                startActivity(i);
            }
        });
        // notify when intake catlories are greater than total calories

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date=LocalDate.now();
        }
        dateHistory.setText(date.toString());

//        //GetLogData
        this.populateCaloriesInffo(user_id);

        if(!dateHistory.isSelected()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sharedPreferenceManager.saveDate(String.valueOf(LocalDate.now()));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                date=LocalDate.now();
            }
        }

        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                date= LocalDate.parse(dateHistory.getText().toString());
            }
        }

//        //getPerDayLogData
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<GetPerDayLogDataResponse> call = apiInterface.getPerDayLogData(user_id, String.valueOf(date));
//        call.enqueue(new Callback<GetPerDayLogDataResponse>() {
//
//            @Override
//            public void onResponse(Call<GetPerDayLogDataResponse> call, Response<GetPerDayLogDataResponse> response) {
//
//                GetPerDayLogDataResponse getPerDayLogDataResponse = response.body();
//                intake_calories=getPerDayLogDataResponse.getPayload().totalEatenCalories;
//                if (response.isSuccessful()) {
//                    if (getPerDayLogDataResponse.getStatus().equals("SUCCESS")) {
//
//                        B_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_BCalories));
//                        D_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_DCalories));
//                        ES_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_ESCalories));
//                        MS_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_MSCalories));
//                        L_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_LCalories));
//                        Exercise_burned.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalBurnedCalories));
//                        Protein_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenProtein));
//                        Carbs_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenCarbs));
//                        Fats_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenFat));
//                        calorie_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().getTotalEatenCalories()));
//                        Log.d("magic", "per_day_calorie " + per_day_calorie.getText().toString());
//                        Log.d("magic", "calorie_intake " + calorie_intake.getText().toString());
//                        if (calories < intake_calories) {
//                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Track.this);
//                            //Set body message of Dialog
//                            builder.setMessage("You consumed more calories than you required. Having more calories than you need can cause you to lose focus on your weight loss Track.");
//                            // Is dismiss when touching outside?
//                            builder.setCancelable(true);
//                            androidx.appcompat.app.AlertDialog dialog = builder.create();
//                            dialog.closeOptionsMenu();
//                            dialog.setTitle("Alert");
//                            dialog.show();
//                        } else {
//                            Log.d("magic", "per_day_calorie 1 " + calories);
//                            Log.d("magic", "calorie_intake 1 " + intake_calories);
//                            // Toast.makeText(CalorieCounter.this, "not ", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//
//
//                }
//
//
//                else {
//                    Log.d("Magic", "error8: " + getPerDayLogDataResponse.getMessage());
//
//                    //Toast.makeText(CalorieCounter.this, getPerDayLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetPerDayLogDataResponse> call, Throwable t) {
//                Log.d("Magic", "error9: " + t.getMessage());
//                //  Toast.makeText(CalorieCounter.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        //GetLunchListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetLogListResponse>> call1 = apiInterface.getLunchList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call1.enqueue(new Callback<List<GetLogListResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetLogListResponse> getLunchListResponses = response.body();
//                    LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
//                    l_recyclerView.setAdapter(logAdapter);
//                    logAdapter.notifyDataSetChanged();
//                } else {
//                    //  Toast.makeText(CalorieCounter.this, "Response is not successful", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
//                Log.d("Magic", "error7: " + t.getMessage());
//                //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //GetBreakfastListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetLogListResponse>> call2 = apiInterface.getBreakfastList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call2.enqueue(new Callback<List<GetLogListResponse>>() {
//
//            @Override
//            public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetLogListResponse> getLunchListResponses = response.body();
//                    LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
//                    recyclerView.setAdapter(logAdapter);
//                    logAdapter.notifyDataSetChanged();
//                } else {
//                    //  Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
//                Log.d("Magic", "error6: " + t.getMessage());
//                // Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //GetMMorningSnacksListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetLogListResponse>> call4 = apiInterface.getMorningSnacksList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call4.enqueue(new Callback<List<GetLogListResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetLogListResponse> getLunchListResponses = response.body();
//                    LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
//                    ms_recyclerView.setAdapter(logAdapter);
//                    logAdapter.notifyDataSetChanged();
//                } else {
//                    //    Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
//                Log.d("Magic", "error5: " + t.getMessage());
//                // Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //GetEveningListListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetLogListResponse>> call5 = apiInterface.getEveningSnacksList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call5.enqueue(new Callback<List<GetLogListResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetLogListResponse> getLunchListResponses = response.body();
//                    LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
//                    es_recyclerView.setAdapter(logAdapter);
//                    logAdapter.notifyDataSetChanged();
//                } else {
//                    // Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
//                Log.d("Magic", "error4: " + t.getMessage());
//                //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //GetDinnerListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetLogListResponse>> call6 = apiInterface.getDinnerList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call6.enqueue(new Callback<List<GetLogListResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetLogListResponse> getLunchListResponses = response.body();
//                    LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
//                    dinner_recyclerView.setAdapter(logAdapter);
//                    logAdapter.notifyDataSetChanged();
//                } else {
//                    // Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
//                Log.d("Magic", "error3: " + t.getMessage());
//                //   Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //GetExerciseListApi
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<List<GetExerciseListResponse>> call7 = apiInterface.getExerciseList(sharedPreferenceManager.getUser().getId(), String.valueOf(date));
//        call7.enqueue(new Callback<List<GetExerciseListResponse>>() {
//
//            @Override
//            public void onResponse(Call<List<GetExerciseListResponse>> call, Response<List<GetExerciseListResponse>> response) {
//                if (response.isSuccessful()) {
//                    List<GetExerciseListResponse> getExerciseListResponses = response.body();
//                    ExerciseLogAdapter exerciseLogAdapter = new ExerciseLogAdapter(getExerciseListResponses, Track.this);
//                    exercise_recyclerView.setAdapter(exerciseLogAdapter);
//                    exerciseLogAdapter.notifyDataSetChanged();
//                } else {
//                    //  Toast.makeText(CalorieCounter.this, "Exercise Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GetExerciseListResponse>> call, Throwable t) {
//                Toast.makeText(Track.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("Magic", "error2: " + t.getMessage());
//
//            }
//        });
//
//
//        int int_id=getIntent().getIntExtra("int_id",1);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 19);
//        calendar.set(Calendar.MINUTE, 15);
//        calendar.set(Calendar.SECOND, 00);
//
//        Calendar b_calendar = Calendar.getInstance();
//        b_calendar.set(Calendar.HOUR_OF_DAY, 23);
//        b_calendar.set(Calendar.MINUTE, 00);
//        b_calendar.set(Calendar.SECOND, 00);
//
//        Calendar l_calendar = Calendar.getInstance();
//        l_calendar.set(Calendar.HOUR_OF_DAY, 1);
//        l_calendar.set(Calendar.MINUTE, 30);
//        l_calendar.set(Calendar.SECOND, 30);
//
//        Calendar e_calendar = Calendar.getInstance();
//        e_calendar.set(Calendar.HOUR_OF_DAY, 7);
//        e_calendar.set(Calendar.MINUTE, 30);
//        e_calendar.set(Calendar.SECOND, 00);
//
//        Calendar d_calendar = Calendar.getInstance();
//        d_calendar.set(Calendar.HOUR_OF_DAY, 9);
//        d_calendar.set(Calendar.MINUTE, 30);
//        d_calendar.set(Calendar.SECOND, 00);
//
//        Calendar ms_calendar = Calendar.getInstance();
//        ms_calendar.set(Calendar.HOUR_OF_DAY, 11);
//        ms_calendar.set(Calendar.MINUTE, 30);
//        ms_calendar.set(Calendar.SECOND, 00);
//
//        Calendar es_calendar = Calendar.getInstance();
//        es_calendar.set(Calendar.HOUR_OF_DAY, 5);
//        es_calendar.set(Calendar.MINUTE, 00);
//        es_calendar.set(Calendar.SECOND, 00);
//
//        if (Calendar.getInstance().after(calendar)) {
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if (Calendar.getInstance().after(b_calendar)) {
//            b_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if (Calendar.getInstance().after(e_calendar)) {
//            e_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if(Calendar.getInstance().after(l_calendar)){
//            l_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if(Calendar.getInstance().after(d_calendar)){
//            d_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if(Calendar.getInstance().after(ms_calendar)){
//            ms_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        if(Calendar.getInstance().after(es_calendar)){
//            es_calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//
//        Intent intent = new Intent(Track.this, DailyBroadcast.class);
//        Intent intent1 = new Intent(Track.this, BreakfastBroadcast.class);
//        Intent intent2 = new Intent(Track.this, LunchBroadcast.class);
//        Intent intent3 = new Intent(Track.this, ExerciseBroadcast.class);
//        Intent intent4 = new Intent(Track.this, DinnerBroadcast.class);
//        Intent intent5 = new Intent(Track.this, MorningSnackBroadcast.class);
//        Intent intent6 = new Intent(Track.this, EveningSnackBroadcast.class);
        bottomNavClick();
    }

    private String getTodayDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(year,month,day);
    }

    private String makeDateString(int year, int month, int day) {
        return year+"-"+month+"-"+day;
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                String date = makeDateString(year,month,day);
                dateHistory.setText(date);

                Log.d("Magic", "onDateSet: "+date);
                Log.d("Magic", "date History: ",sharedPreferenceManager.saveDate(date));

                long user_id=sharedPreferenceManager.getUser().getId();

                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<GetLogDataResponse> call = apiInterface.getLogData(user_id);
                call.enqueue(new Callback<GetLogDataResponse>() {
                    @Override
                    public void onResponse(Call<GetLogDataResponse> call, Response<GetLogDataResponse> response) {

                        GetLogDataResponse getLogDataResponse = response.body();
                        if (response.isSuccessful()) {
                            if (getLogDataResponse.getStatus().equals("SUCCESS")) {
                                per_day_carb.setText(String.valueOf(getLogDataResponse.getPayload().t_carbs));
                                per_day_calorie.setText(String.valueOf(getLogDataResponse.getPayload().weightloss_calories));
                                per_day_carb.setText(String.valueOf(getLogDataResponse.getPayload().t_carbs));
                                lunch_cal.setText(String.valueOf(getLogDataResponse.getPayload().t_lCalories));
                                dinner_cal.setText(String.valueOf(getLogDataResponse.getPayload().t_dCalories));
                                bf_cal.setText(String.valueOf(getLogDataResponse.getPayload().t_bCalories));
                                morning_snack_cal.setText(String.valueOf(getLogDataResponse.getPayload().t_msCalories));
                                evening_snack_cal.setText(String.valueOf(getLogDataResponse.getPayload().t_esCalories));
                                per_day_protein.setText(String.valueOf(getLogDataResponse.getPayload().t_proteins));
                                per_day_fat.setText(String.valueOf(getLogDataResponse.getPayload().t_fat));

                            } else {
                                // Toast.makeText(CalorieCounter.this, getLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //  Toast.makeText(CalorieCounter.this, getLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLogDataResponse> call, Throwable t) {
                        //  Toast.makeText(CalorieCounter.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });

                //getPerDayLogData
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<GetPerDayLogDataResponse> call2 = apiInterface.getPerDayLogData(user_id, (date));
                call2.enqueue(new Callback<GetPerDayLogDataResponse>() {
                    @Override
                    public void onResponse(Call<GetPerDayLogDataResponse> call, Response<GetPerDayLogDataResponse> response) {

                        GetPerDayLogDataResponse getPerDayLogDataResponse = response.body();
                        if (response.isSuccessful()) {
                            if (getPerDayLogDataResponse.getStatus().equals("SUCCESS")) {
                                B_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_BCalories));
                                D_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_DCalories));
                                ES_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_ESCalories));
                                MS_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_MSCalories));
                                L_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEaten_LCalories));
                                Exercise_burned.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalBurnedCalories));
                                Protein_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenProtein));
                                Carbs_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenCarbs));
                                Fats_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().totalEatenFat));
                                calorie_intake.setText(String.valueOf(getPerDayLogDataResponse.getPayload().getTotalEatenCalories()));

                            } else {
                                //  Toast.makeText(CalorieCounter.this, getPerDayLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
//                    Toast.makeText(CalorieCounter.this, getPerDayLogDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<GetPerDayLogDataResponse> call, Throwable t) {
                        // Toast.makeText(CalorieCounter.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });

                //GetLunchListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetLogListResponse>> call3 = apiInterface.getLunchList(sharedPreferenceManager.getUser().getId(), date);
                call3.enqueue(new Callback<List<GetLogListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
                        if (response.isSuccessful()) {
                            List<GetLogListResponse> getLunchListResponses = response.body();
                            LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
                            l_recyclerView.setAdapter(logAdapter);
                            logAdapter.notifyDataSetChanged();
                        } else {
                            //  Toast.makeText(CalorieCounter.this, "Response is not successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
                        //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                //GetBreakfastListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetLogListResponse>> call4 = apiInterface.getBreakfastList(sharedPreferenceManager.getUser().getId(), date);
                call4.enqueue(new Callback<List<GetLogListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
                        if (response.isSuccessful()) {
                            List<GetLogListResponse> getLunchListResponses = response.body();
                            LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
                            recyclerView.setAdapter(logAdapter);
                            logAdapter.notifyDataSetChanged();
                        } else {
                            //  Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
                        //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                //GetMMorningSnacksListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetLogListResponse>> call5 = apiInterface.getMorningSnacksList(sharedPreferenceManager.getUser().getId(), date);
                call5.enqueue(new Callback<List<GetLogListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
                        if (response.isSuccessful()) {
                            List<GetLogListResponse> getLunchListResponses = response.body();
                            LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
                            ms_recyclerView.setAdapter(logAdapter);
                            logAdapter.notifyDataSetChanged();
                        } else {
                            //    Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
                        //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                //GetEveningListListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetLogListResponse>> call6 = apiInterface.getEveningSnacksList(sharedPreferenceManager.getUser().getId(), date);
                call6.enqueue(new Callback<List<GetLogListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
                        if (response.isSuccessful()) {
                            List<GetLogListResponse> getLunchListResponses = response.body();
                            LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
                            es_recyclerView.setAdapter(logAdapter);
                            logAdapter.notifyDataSetChanged();
                        } else {
                            // Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
                        // Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                //GetDinnerListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetLogListResponse>> call7 = apiInterface.getDinnerList(sharedPreferenceManager.getUser().getId(), date);
                call7.enqueue(new Callback<List<GetLogListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetLogListResponse>> call, Response<List<GetLogListResponse>> response) {
                        if (response.isSuccessful()) {
                            List<GetLogListResponse> getLunchListResponses = response.body();
                            LogAdapter logAdapter = new LogAdapter(getLunchListResponses, Track.this);
                            dinner_recyclerView.setAdapter(logAdapter);
                            logAdapter.notifyDataSetChanged();
                        } else {
                            // Toast.makeText(CalorieCounter.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetLogListResponse>> call, Throwable t) {
                        //  Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });


                //GetExerciseListApi
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<GetExerciseListResponse>> call8 = apiInterface.getExerciseList(sharedPreferenceManager.getUser().getId(), date);
                call8.enqueue(new Callback<List<GetExerciseListResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetExerciseListResponse>> call, Response<List<GetExerciseListResponse>> response) {
                        if (response.isSuccessful()) {
                            Log.d("Magic", "openHomePage: " + user_id);                            List<GetExerciseListResponse> getExerciseListResponses = response.body();
                            ExerciseLogAdapter exerciseLogAdapter = new ExerciseLogAdapter(getExerciseListResponses, Track.this);
                            exercise_recyclerView.setAdapter(exerciseLogAdapter);
                            exerciseLogAdapter.notifyDataSetChanged();
                        } else {
                            //    Toast.makeText(CalorieCounter.this, "Exercise Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetExerciseListResponse>> call, Throwable t) {
                        // Toast.makeText(CalorieCounter.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }

        };

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog =new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    //if calories is equal to 0
    private void NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Yes2Live";
            String description = "Yes2Live CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Daily_Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    //Breakfast Notification
    private void b_NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String description = "FITVISOR`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("B_Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    //Exercise Notification
    private void e_NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String description = "FITVISOR`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("E_Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    //Lunch Notification
    private void l_NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String description = "FITVISOR`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("L_Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    //Dinner Notification
    private void d_NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String description = "FITVISOR`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("D_Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    //MorningSnacks Notification
    private void MSNotification() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String Description = "FITVISOR CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("MS_Notification", name, importance);
            channel.setDescription(Description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //EveningSnacks Notification
    private void EveningSnacksNotification() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            CharSequence name = "FITVISOR";
            String Description = "FITVISOR CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ES_Notification", name, importance);
            channel.setDescription(Description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void bottomNavClick() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {


            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navGoal:
                    Intent intent = new Intent(Track.this, Goal.class);
                    startActivity(intent);
                    break;
                case R.id.navProfile:
                    Intent intent2 = new Intent(Track.this, Profile.class);
                    startActivity(intent2);
                    break;
                case R.id.navTrack:
                    Intent intent3 = new Intent(Track.this, Track.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
    }

    public void populateCaloriesInffo(long user_id) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<UserBaseResponse> call = apiInterface.userProfile(user_id);
        call.enqueue(new Callback<UserBaseResponse>() {
            @Override
            public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {

                UserBaseResponse userProfile = response.body();
                if (userProfile.getStatus().equals("SUCCESS")) {
                    calories=(int) userProfile.getPayload().getWeightLossCalories();
                    per_day_carb.setText(String.valueOf(userProfile.getPayload().getT_carbs()));
                    per_day_calorie.setText(String.valueOf(userProfile.getPayload().getWeightLossCalories()));
                    lunch_cal.setText(String.valueOf(userProfile.getPayload().getL_calories()));
                    dinner_cal.setText(String.valueOf(userProfile.getPayload().getD_calories()));
                    bf_cal.setText(String.valueOf(userProfile.getPayload().getB_calories()));
                    morning_snack_cal.setText(String.valueOf(userProfile.getPayload().getMs_calories()));
                    evening_snack_cal.setText(String.valueOf(userProfile.getPayload().getEs_calories()));
                    per_day_protein.setText(String.valueOf(userProfile.getPayload().getT_proteins()));
                    per_day_fat.setText(String.valueOf(userProfile.getPayload().getT_fat()));
                }else{
                    Toast.makeText(Track.this, userProfile.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                Toast.makeText(Track.this,  t.toString()+" Not Found in Databses ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
