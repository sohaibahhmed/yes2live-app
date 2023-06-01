package com.fyp.yes2live;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TargetWeightWarnings extends AppCompatActivity {

    TextView message;
    Button DoctorRecommended, AppRecommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_weight_warnings);
        getSupportActionBar().hide();
        message=findViewById(R.id.message);
        DoctorRecommended=findViewById(R.id.b1);
        AppRecommended=findViewById(R.id.button_2);
        String warning=getIntent().getStringExtra("message");
        message.setText(warning);
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        Long userId= sharedPreferenceManager.getUser().getId();


        AppRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Call<GetDoctorRecommended> call = RetrofitClient
//                        .getInstance()
//                        .getApi()
//                        .buttonOne(userId);
//
//                call.enqueue(new Callback<GetDoctorRecommended>() {
//                    @Override
//                    public void onResponse(Call<GetDoctorRecommended> call, Response<GetDoctorRecommended> response) {
//                        GetDoctorRecommended getDoctorRecommended = response.body();
//                        if (response.isSuccessful()) {
//
//                            if (getDoctorRecommended.getStatus().equals("SUCCESS")) {
//                                sharedPreferenceManager.saveRanges(getIntent().getIntExtra("targetweight",1));
//                                Toast.makeText(TargetWeightWarnings.this, getDoctorRecommended.getMessage(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(TargetWeightWarnings.this, CalorieCounter.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//                            }
//                            else {
//                                Toast.makeText(TargetWeightWarnings.this, getDoctorRecommended.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<GetDoctorRecommended> call, Throwable t) {
//                        Toast.makeText(TargetWeightWarnings.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });

        DoctorRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Call<GetDoctorRecommended> callApp = RetrofitClient
//                        .getInstance()
//                        .getApi()
//                        .buttonTwo(userId);
//
//                callApp.enqueue(new Callback<GetDoctorRecommended>() {
//                    @Override
//                    public void onResponse(Call<GetDoctorRecommended> call, Response<GetDoctorRecommended> response) {
//                        GetDoctorRecommended getDoctorRecommended = response.body();
//                        if (response.isSuccessful()) {
//
//                            if (getDoctorRecommended.getStatus().equals("SUCCESS")) {
//                                sharedPreferenceManager.saveRanges(getIntent().getIntExtra("targetweight",1));
//                                Toast.makeText(TargetWeightWarnings.this, getDoctorRecommended.getMessage(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(TargetWeightWarnings.this, CalorieCounter.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//                            }
//                            else {
//                                Toast.makeText(TargetWeightWarnings.this, getDoctorRecommended.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<GetDoctorRecommended> call, Throwable t) {
//                        Toast.makeText(TargetWeightWarnings.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

    }
}