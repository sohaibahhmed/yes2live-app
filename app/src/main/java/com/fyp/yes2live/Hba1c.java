package com.fyp.yes2live;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.DiabeticPatient;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.UserAssessmentResponse;
import com.fyp.yes2live.response.UserBaseResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hba1c extends AppCompatActivity {
    Button add, cancel;
    EditText hba1cEdit;
    Double hba1cDouble;
    LocalDate date_n;
    TextView date;
    private APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;
    Boolean blood_pressure, cholesterol, diabetic_family, married, other_diseases, pregnant, no_diseases;
    Integer systolic_ranges, diastolic_ranges, cholesterol_ranges;
    ImageButton previousBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hba1c);
        add=(Button) findViewById(R.id.addReport);
        cancel = findViewById(R.id.cancelReport);
        hba1cEdit = (EditText) findViewById(R.id.hba1creport);
        previousBtn= findViewById(R.id.PreviousButton);
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        long user_id = sharedPreferenceManager.getUser().getId();
        date=findViewById(R.id.datetext);

        blood_pressure = getIntent().getBooleanExtra("blood_pressure", false);
        cholesterol = getIntent().getBooleanExtra("cholesterol", false);
        diabetic_family = getIntent().getBooleanExtra("diabetic_family", false);
        systolic_ranges = getIntent().getIntExtra("systolic_ranges", 1);
        diastolic_ranges = getIntent().getIntExtra("diastolic_ranges", 1);
        cholesterol_ranges = getIntent().getIntExtra("cholesterol_ranges", 1);
        married = getIntent().getBooleanExtra("married", false);
        other_diseases = getIntent().getBooleanExtra("other_diseases", false);
        pregnant = getIntent().getBooleanExtra("pregnant", false);
        no_diseases = getIntent().getBooleanExtra("no_diseases", false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_n = LocalDate.now();
            date.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Hba1c.this, AnyDisease.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hba1cDouble = Double.parseDouble(hba1cEdit.getText().toString());
                if (hba1cEdit.getText().toString().isEmpty()) {
                    hba1cEdit.setError("Enter Hba1c Range");
                    return;
                } else {
                    if(hba1cDouble <= 4 ){
                        Toast.makeText(Hba1c.this, "Your Sugar level is too low. You should consult your doctor", Toast.LENGTH_SHORT).show();
                    } else if(hba1cDouble>=14) {
                        Toast.makeText(Hba1c.this, "Your Sugar level is too high. You should consult your doctor", Toast.LENGTH_SHORT).show();
                    } else if(hba1cDouble< 14 && hba1cDouble >4){
                        String pattern = "yyyy-MM-dd"; // Example: "2023-07-25"
                        String dateString = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            dateString = date_n.format(DateTimeFormatter.ofPattern(pattern));
                        }
                        apiInterface = APIClient.getClient().create(APIInterface.class);
                        DiabeticPatient diabeticPatient = new DiabeticPatient(user_id, blood_pressure, cholesterol, diabetic_family, dateString, hba1cDouble, systolic_ranges, diastolic_ranges, cholesterol_ranges, married, other_diseases, pregnant, no_diseases);
                        Call<UserAssessmentResponse> call = apiInterface.user_assessment(diabeticPatient);
                        call.enqueue(new Callback<UserAssessmentResponse>() {
                            @Override
                            public void onResponse(Call<UserAssessmentResponse> call, Response<UserAssessmentResponse> response) {
                                UserAssessmentResponse hba1cResponse = response.body();
                                if (hba1cResponse.getStatus().equals("SUCCESS")) {
                                    sharedPreferenceManager.saveReportDate(String.valueOf(date_n));
                                    sharedPreferenceManager.saveUserDiabeticAssessment(hba1cResponse.getUserAssessmentPayload());
                                    Intent intent = new Intent(Hba1c.this, Summary.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else if(hba1cResponse.getStatus().equals("WARNING")){
                                    sharedPreferenceManager.saveUserWarning(hba1cResponse.getMessage());
                                    sharedPreferenceManager.saveReportDate(String.valueOf(date_n));
                                    Log.d("Magic", "onResponse: " + hba1cResponse.getUserAssessmentPayload());
                                    Intent intent = new Intent(Hba1c.this, UserWarning.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Hba1c.this, hba1cResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<UserAssessmentResponse> call, Throwable t) {
                                Toast.makeText(Hba1c.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(Hba1c.this, "empty field not allowed, must add HB1AC report", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // I've to ask question either we want to print message here or not
            }
        });
    }
}