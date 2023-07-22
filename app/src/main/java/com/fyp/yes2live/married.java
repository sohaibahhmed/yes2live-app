package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.UserBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class married extends AppCompatActivity {
    Button button;
    private RadioGroup rgMarried;
    private RadioButton yes, no;
    private APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;

    private Boolean isMarried;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_married);
        button=(Button) findViewById(R.id.nextbtn);
        rgMarried = findViewById(R.id.married_Grp);
        yes = findViewById(R.id.yesbtn);
        no = findViewById(R.id.nobtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rgMarried.getCheckedRadioButtonId();
                if(selectedId==R.id.yesbtn){
                    isMarried=true;
                }
                else {
                    isMarried=false;
                }
                sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
                long userId = sharedPreferenceManager.getUser().id;
                apiInterface = APIClient.getClient().create(APIInterface.class);
                User user = new User();
                user.setId(userId);
                user.setMarried(isMarried);
                Call<UserBaseResponse> call = apiInterface.married(user);
                call.enqueue(new Callback<UserBaseResponse>() {
                    @Override
                    public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {
                        UserBaseResponse marriedResponse = response.body();
                        if (marriedResponse.getStatus().equals("SUCCESS")) {
                            Toast.makeText(married.this, marriedResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(married.this, pregnant.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(married.this, marriedResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                        Toast.makeText(married.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });

    }
}