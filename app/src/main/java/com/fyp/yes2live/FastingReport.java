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
import com.fyp.yes2live.response.FastingSugarTestResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FastingReport extends AppCompatActivity {

    Button add;
    LocalDate date_n;
    SharedPreferenceManager sharedPreferenceManager;
    private APIInterface apiInterface;
    int patientId,testRange;
    long user_id;
    EditText range;
    TextView date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasting_report);
        getSupportActionBar().hide();
        range=findViewById(R.id.rangefastingReport);
        add=findViewById(R.id.addFastReport);
        date=findViewById(R.id.datetext);


        date_n= LocalDate.now();
        date.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        // date.setText(date_n.toString());
        Log.d("Magic", "Date: " + date_n);
        patientId=getIntent().getIntExtra("patient_id",1);

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        user_id=sharedPreferenceManager.getUser().getId();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (range.getText().toString().isEmpty()) {
                    range.setError("Enter Your report readings ");
                    return;

                } else {
                    Log.d("Magic", "patientId: " + patientId);
                    Log.d("Magic", "test range: " + range.getText().toString());
                    testRange = Integer.valueOf(range.getText().toString());

                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<FastingSugarTestResponse> call = apiInterface.fasting_sugar_test_report(user_id, patientId, date_n, testRange);

                    call.enqueue(new Callback<FastingSugarTestResponse>() {
                        @Override
                        public void onResponse(Call<FastingSugarTestResponse> call, Response<FastingSugarTestResponse> response) {

                            Log.d("Magic", "DETAILS: " + response.errorBody());
                            FastingSugarTestResponse fastingSugarTestResponse = response.body();
                            if (response.isSuccessful()) {
                                if (fastingSugarTestResponse.getStatus().equals("SUCCESS")) {
                                    Toast.makeText(FastingReport.this, fastingSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FastingReport.this, Summary.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(FastingReport.this, fastingSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(FastingReport.this, fastingSugarTestResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<FastingSugarTestResponse> call, Throwable t) {
                            Toast.makeText(FastingReport.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}