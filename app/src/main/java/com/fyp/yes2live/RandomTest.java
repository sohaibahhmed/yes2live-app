package com.fyp.yes2live;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.RandomSugarTestResponse;
import com.fyp.yes2live.response.UserAssessmentResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomTest extends AppCompatActivity {

    Button add;
    LocalDate date_n;
    SharedPreferenceManager sharedPreferenceManager;
    private APIInterface apiInterface;
    int patientId,testRange;
    long user_id;
    EditText rangeRandom;
    TextView date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_test);
        getSupportActionBar().hide();
        rangeRandom=findViewById(R.id.rangeRandomReport);
        add=findViewById(R.id.addRandomReport);
        date=findViewById(R.id.datetext);


        date_n= LocalDate.now();
        date.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        Log.d("Magic", "Date: " + date_n);
        patientId=getIntent().getIntExtra("patient_id",1);

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        user_id=sharedPreferenceManager.getUser().getId();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rangeRandom.getText().toString().isEmpty()){
                    rangeRandom.setError("Enter Your report readings");
                    return;
                }
                else {
                    Log.d("Magic", "patientId: " + patientId);
                    Log.d("Magic", "test range: " + rangeRandom.getText().toString());
                    testRange = Integer.valueOf(rangeRandom.getText().toString());

                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<RandomSugarTestResponse> call = apiInterface.random_sugar_test_report(user_id, patientId, date_n, testRange);

                    call.enqueue(new Callback<RandomSugarTestResponse>() {
                        @Override
                        public void onResponse(Call<RandomSugarTestResponse> call, Response<RandomSugarTestResponse> response) {

                            Log.d("Magic", "DETAILS: " + response.errorBody());
                            RandomSugarTestResponse randomSugarTestResponse = response.body();
                            if (response.isSuccessful()) {
                                if (randomSugarTestResponse.getStatus().equals("SUCCESS")) {
                                    Toast.makeText(RandomTest.this, randomSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RandomTest.this, Summary.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RandomTest.this, randomSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RandomTest.this, randomSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RandomSugarTestResponse> call, Throwable t) {
                            Toast.makeText(RandomTest.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}