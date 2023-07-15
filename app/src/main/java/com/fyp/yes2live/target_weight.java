package com.fyp.yes2live;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.UserBaseResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class target_weight extends AppCompatActivity {
    private Button nextBtn;
    private TextView range,reachGoal;
    private SharedPreferenceManager sharedPreferenceManager;
    private APIInterface apiInterface;
    private SeekBar seekBar;
    private double start_value;
    private ImageButton upBtn;

    private EditText targetWeight;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        setContentView(R.layout.activity_target_weight);
        nextBtn = (Button) findViewById(R.id.NextButton);
        range=findViewById(R.id.weightRange);
        seekBar = findViewById(R.id.seekbarforheight);
        upBtn = findViewById(R.id.upBotton);
        targetWeight=findViewById(R.id.t_Weight);
        reachGoal = findViewById(R.id.goal);

        long user_id = sharedPreferenceManager.getUser().getId();
        callBMIRange(user_id);
        //calling endpoint to get BMI range and set to range text field.

        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (i == 0) {
                        start_value = 0.25;
                        reachGoal.setText(Double.valueOf(start_value).toString());
                    } else if (i == 1) {
                        start_value = 0.5;
                        reachGoal.setText(Double.valueOf(start_value).toString());

                    } else if (i == 2) {
                        start_value = 0.75;
                        reachGoal.setText(Double.valueOf(start_value).toString());

                    } else if (i == 3) {
                        start_value = 1.0;
                        reachGoal.setText(Double.valueOf(start_value).toString());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(target_weight.this, homepage.class);
                startActivity(i);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (targetWeight.getText().toString().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(target_weight.this);
                    //Set body message of Dialog
                    builder.setMessage("Target weight can't be empty");
                    // Is dismiss when touching outside?
                    builder.setCancelable(true);//to cancel the dilogue box(option of cross)
                    AlertDialog dialog = builder.create();
                    dialog.closeOptionsMenu();
                    dialog.setTitle("Alert");
                    dialog.show();
                }else{// we send id weight and reach goal
                    long userId = sharedPreferenceManager.getUser().getId();
                    double weight = Double.valueOf(targetWeight.getText().toString());
                    double reachGoal = start_value;
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    User user = new User(userId,weight,reachGoal);
                    Call<UserBaseResponse> call = apiInterface.calculateCaloriesBasedOnWeightLoss(user);
                    call.enqueue(new Callback<UserBaseResponse>() {
                        @Override
                        public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {
                            UserBaseResponse baseResponse = response.body();
                            if (baseResponse.getStatus().equals("SUCCESS")) {
                                sharedPreferenceManager.saveRanges();
                                Intent intent = new Intent(target_weight.this, Track.class);
                                startActivity(intent);
                                finish();
                            }
//                else if(baseResponse.getStatus().equals("WARNING")){
//                    Intent intent = new Intent(target_weight.this, TargetWeightWarnings.class);
//                    intent.putExtra("message", baseResponse.getMessage());
//                    intent.putExtra("targetweight", targetWeight);
//                    startActivity(intent);
//                    finish();
//                }
                            else if (baseResponse.getStatus().equals("ERROR")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(target_weight.this);
                                //Set body message of Dialog
                                builder.setMessage(baseResponse.getMessage());
                                builder.setCancelable(true);
                                AlertDialog dialog = builder.create();
                                dialog.closeOptionsMenu();
                                dialog.setTitle("Alert");
                                dialog.show();
                            }
                            else{
                                Toast.makeText(target_weight.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                            Toast.makeText(target_weight.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }

                    });
                }

            }
        });
    }

    public void callBMIRange(long userID){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserBaseResponse> call = apiInterface.Bmi_range(userID);
        call.enqueue(new Callback<UserBaseResponse>() {
            @Override
            public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {
                UserBaseResponse baseResponse = response.body();
                if (baseResponse.getStatus().equals("SUCCESS")) {
                    range.setText(String.valueOf(baseResponse.getMessage()));
                }else{
                    Toast.makeText(target_weight.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                Toast.makeText(target_weight.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

}