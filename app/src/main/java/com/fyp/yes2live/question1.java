package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class question1 extends AppCompatActivity {
    Button next;
    EditText age, height, weight;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    String gender;
    ImageButton TableConversion;
    APIInterface apiInterface;

    SharedPreferenceManager sharedPreferenceManager;
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

                Intent intent = new Intent(question1.this, homepage.class);
                startActivity(intent);
//                int selectedId = rgGender.getCheckedRadioButtonId();
//                RadioButton radioButton = (RadioButton) findViewById(selectedId);
//                if(selectedId==R.id.male){
//                    gender="male";
//                }
//                else {
//                    gender="female";
//                }
//
//                sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
//                long userId = sharedPreferenceManager.getUser().id;
//                apiInterface = APIClient.getClient().create(APIInterface.class);
//                User user = new User(userId,Integer.valueOf(age.getText().toString()),gender,Double.valueOf(weight.getText().toString()),Double.valueOf(height.getText().toString()));
//                Call<BaseResponse> call = apiInterface.update(user);
//                call.enqueue(new Callback<BaseResponse>() {
//                    @Override
//                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                        BaseResponse loginResponse = response.body();
//                        if (loginResponse.getStatus().equals("SUCCESS")) {
//                            Toast.makeText(question1.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(question1.this, homepage.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(question1.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<BaseResponse> call, Throwable t) {
//                        Toast.makeText(question1.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
//                    }
//
//                });
            }
        });

    }
}