package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class question1 extends AppCompatActivity {
    // gender age weight height ( behalf of id)
    private Button next;
    private EditText age, height, weight;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private String gender;
    private APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        next = (Button) findViewById(R.id.Next_btn);
        age = findViewById(R.id.age_edit_txt);
        height = findViewById(R.id.currentHeight);
        weight = findViewById(R.id.weight_edit_txt);
        rgGender = findViewById(R.id.gender_Grp);
        rbMale = findViewById(R.id.male);
        rbFemale = findViewById(R.id.female);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                declare variable
                int ageInt = Integer.valueOf(age.getText().toString());
                double weightDouble = Double.valueOf(weight.getText().toString());
                double heightDouble = Double.valueOf(height.getText().toString());

//                add empty field validation
                if (height.getText().toString().isEmpty() || weight.getText().toString().isEmpty() || age.getText().toString().isEmpty()) {
                    Toast.makeText(question1.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }

                if (ageInt < 15) {
                    age.setError("You need to be older than 15 to registered");
                    return;
                }

                if (ageInt > 80) {
                    age.setError("You need to be younger than 80 to registered");
                    return;
                }

                if (weightDouble < 20) {
                    weight.setError("Enter numeric weight greater than 20 kg");
                    weight.requestFocus();
                    return;
                }

                if (weightDouble > 300) {
                    weight.setError("Enter weight less than 300 kg");
                    return;
                }

                if (heightDouble < 101) {
                    height.setError("Enter height greater than 100 cm");
                    height.requestFocus();
                    return;
                }

                if (heightDouble > 243) {
                    height.setError("Enter height less than 243 cm");
                    height.requestFocus();
                    return;
                }


                int selectedId = rgGender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==R.id.male){
                    gender="male";
                }
                else {
                    gender="female";
                }

                sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
                long userId = sharedPreferenceManager.getUser().id;
                apiInterface = APIClient.getClient().create(APIInterface.class);
                User user = new User(userId,ageInt,gender,weightDouble,heightDouble);
                Call<BaseResponse> call = apiInterface.update(user);
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        BaseResponse loginResponse = response.body();
                        if (loginResponse.getStatus().equals("SUCCESS")) {
                            sharedPreferenceManager.saveUser(loginResponse.getPayload());
                            sharedPreferenceManager.saveQuestion();
                            Toast.makeText(question1.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(question1.this, ActivityLevel.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(question1.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(question1.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }
}