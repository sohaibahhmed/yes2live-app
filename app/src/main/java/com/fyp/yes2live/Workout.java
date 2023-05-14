package com.fyp.yes2live;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.GetBurnedCaloriesResponse;
import com.fyp.yes2live.response.GetExerciseLogResponse;
import com.fyp.yes2live.response.UpdateExerciseResponse;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Workout extends AppCompatActivity {
    ImageButton upBtn;
    Button nextBtn;
    TextView cal;
    SharedPreferenceManager sharedPreferenceManager;
    EditText exercise_time;
    Integer  time;

    long userId;
    Double calories;
    String Exercise_name, name;
    Integer exercise_done;
    Integer exercise_tracking_id;
    String BurnedCal;
    String date;
    String dateHistory;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getSupportActionBar().hide();
        upBtn = findViewById(R.id.upBotton);
        nextBtn = findViewById(R.id.Next);
        TextView exercise = findViewById(R.id.workout);
        cal = findViewById(R.id.calorie);
        exercise_time = findViewById(R.id.exertime);

        Exercise_name = getIntent().getStringExtra("exerciseName");
        name = getIntent().getStringExtra("exercise_name");
        exercise_tracking_id = getIntent().getIntExtra("ExerciseTrackingId", 1);
        exercise_done = getIntent().getIntExtra("exercise_done_id", 1);
        exercise.setText(Exercise_name);
        BurnedCal = getIntent().getStringExtra("burnes_calories");

        if (name == getIntent().getStringExtra("exercise_name")) {
            cal.setText(getIntent().getStringExtra("burnes_calories"));
            exercise.setText(getIntent().getStringExtra("exerciseName"));
            exercise_time.setText(getIntent().getStringExtra("time"));
        } else {
            exercise.setText(name);

        }
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        userId = sharedPreferenceManager.getUser().getId();


        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Intent i = new Intent(Workout.this, Track.class);
                startActivity(i);
            }
        });

        exercise_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (TextUtils.isEmpty(s.toString())) {
                    Double a1 = 0.0;
                    cal.setText(a1.toString());
                    return;

                } else {

                    time = Integer.valueOf(s.toString());

                    //GetBurnedCalories
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<GetBurnedCaloriesResponse> call3 = apiInterface.burnedKcal(userId, exercise_tracking_id, time);
                    call3.enqueue(new Callback<GetBurnedCaloriesResponse>() {
                        @Override
                        public void onResponse(Call<GetBurnedCaloriesResponse> call, Response<GetBurnedCaloriesResponse> response) {
                            GetBurnedCaloriesResponse getBurnedCaloriesResponse = response.body();
                            cal.setText(getBurnedCaloriesResponse.getPayload().getCalories());
                            calories = Double.valueOf(getBurnedCaloriesResponse.getPayload().getCalories());
                            Intent i=new Intent();
                            i.putExtra("exercise_id",getBurnedCaloriesResponse.getPayload().getExercise_id());
                        }

                        @Override
                        public void onFailure(Call<GetBurnedCaloriesResponse> call, Throwable t) {
                            //Toast.makeText(Workout.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                dateHistory= sharedPreferenceManager.getDate();
                Log.d("Magic", "date History: "+dateHistory);

                if(dateHistory.equals(LocalDate.now())) {
                    date= String.valueOf(LocalDate.now());
                }
                else{
                    date= (dateHistory);
                }
                //Saved Exercise into the Database
                Log.d("Magic", "Add Exercise into Database: -----------------");
                Log.d("Magic", "Date: " + date);
                Log.d("Magic", "userId: " + userId);
                Log.d("Magic", "exerciseTrackingId: " + exercise_tracking_id);
                Log.d("Magic", "time: " + time);
                Log.d("Magic", "BurnesCalories: " + calories);

                if (exercise_time.getText().toString().isEmpty()) {
                    exercise_time.setError("Empty Fields not allowed");
                    return;
                }
                Log.d("magic", "onClick: "+time.toString());
                if(time==0){
                    exercise_time.setError("Workout time should be greater than 0");
                    return;
                }

                    if (exercise.getText().toString().equals(Exercise_name)) {
                        //Api->saveMe(Food)
                        apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<GetExerciseLogResponse> call = apiInterface.exerciseLog(userId, date, exercise_tracking_id, calories, time);
                        call.enqueue(new Callback<GetExerciseLogResponse>() {
                            @Override
                            public void onResponse(Call<GetExerciseLogResponse> call, Response<GetExerciseLogResponse> response) {
                                GetExerciseLogResponse getExerciseLogResponse = response.body();
                                if (response.isSuccessful()) {

                                    if (getExerciseLogResponse.getStatus().equals("Success")) {
                                        Toast.makeText(Workout.this, getExerciseLogResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Workout.this, Track.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else if (getExerciseLogResponse.getStatus().equals("Fail!")) {
                                        Toast.makeText(Workout.this, getExerciseLogResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                       // Toast.makeText(Workout.this, getExerciseLogResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                   // Toast.makeText(Workout.this, getExerciseLogResponse.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<GetExerciseLogResponse> call, Throwable t) {
                                Toast.makeText(Workout.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }

                    if (exercise_done == getIntent().getIntExtra("exercise_done_id", 1)) {

                        // exercise.setText(Exercise_name);
                        // cal.setText(String.valueOf(calories));
                        //Api->saveMe(Food)
                        apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<UpdateExerciseResponse> call2 = apiInterface.updateExerciseDone(userId, date, exercise_done, time);
                        call2.enqueue(new Callback<UpdateExerciseResponse>() {
                            @Override
                            public void onResponse(Call<UpdateExerciseResponse> call, Response<UpdateExerciseResponse> response) {
                                UpdateExerciseResponse updateExerciseResponse = response.body();
                                if (response.isSuccessful()) {

                                    if (updateExerciseResponse.getStatus().equals("Success")) {
                                        Toast.makeText(Workout.this, updateExerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Workout.this, Track.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else if (updateExerciseResponse.getStatus().equals("Fail!")) {
                                        Toast.makeText(Workout.this, updateExerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                       // Toast.makeText(Workout.this, updateExerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                  //  Toast.makeText(Workout.this, updateExerciseResponse.getStatus().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UpdateExerciseResponse> call, Throwable t) {
                                Toast.makeText(Workout.this,"Check your internet connection", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        //   Toast.makeText(Workout.this, "not happen", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}