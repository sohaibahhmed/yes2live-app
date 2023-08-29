package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.UserBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dietaryinfo extends AppCompatActivity implements View.OnClickListener {

    private TextView user_bmi;// for bmi result
    private Button nextbtn;
    private EditText age, height, weight;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private String gender;
    private long user_id;
    SharedPreferenceManager sharedPreferenceManager;
    private APIInterface apiInterface;

    ImageButton previousBtn;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diabetic_info);

        nextbtn = findViewById(R.id.NextButton);
        user_bmi = findViewById(R.id.bmi);
        age = findViewById(R.id.updateage);
        height = findViewById(R.id.updatecurrentHeight);
        weight = findViewById(R.id.updatecurrentWeight);
        rgGender = findViewById(R.id.gender_group);
        rbMale = findViewById(R.id.updatemale);
        rbFemale = findViewById(R.id.updatefemale);
        previousBtn= findViewById(R.id.PreviousButton);

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(dietaryinfo.this, homepage.class);
                startActivity(intent);
            }
        });

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        user_id = sharedPreferenceManager.getUser().getId();
        //getUserData
        this.fetchUser();//
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender.equalsIgnoreCase("male")){
                    Intent intent1 = new Intent(dietaryinfo.this, bloodrelative.class);
                    startActivity(intent1);
                }else{
                    Intent intent1 = new Intent(dietaryinfo.this, married.class);
                    startActivity(intent1);
                }
            };
        });
    }
//getbyid api
    public void fetchUser() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserBaseResponse> call = apiInterface.userProfile(user_id);
        call.enqueue(new Callback<UserBaseResponse>() {
            @Override
            public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {

                UserBaseResponse userProfile = response.body();
                if (userProfile.getStatus().equals("SUCCESS")) {
                    User user = userProfile.getPayload();
                    weight.setText(String.valueOf(user.getWeight()));
                    age.setText(String.valueOf(user.getAge()));
                    height.setText(String.valueOf(user.getHeight()));
                    // user_gender.setHint(String.valueOf(getUserBioResponse.getUserBioPayload().isGender()));
                    double int_height=user.getHeight()/100;// change height from cm to m
                    double int_bmi=user.getWeight()/(int_height*int_height);// bmi is calculated
                    user_bmi.setText(String.valueOf(int_bmi));
                    gender = user.getGender();
                    if (gender.equalsIgnoreCase("male")) {
                        rbMale.toggle();
                    } else {
                        rbFemale.toggle();
                    }
                }else{
                    Toast.makeText(dietaryinfo.this, userProfile.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                Toast.makeText(dietaryinfo.this,  t.toString()+" Not Found in Databses ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}