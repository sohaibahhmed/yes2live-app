package com.fyp.yes2live;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.UserBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hba1c extends AppCompatActivity {
    Button button;
    EditText hba1cEdit;
    private APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hba1c);
        button=(Button) findViewById(R.id.addReport);
        hba1cEdit = (EditText) findViewById(R.id.hba1creport);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double hba1cDouble = Double.parseDouble(hba1cEdit.getText().toString());
                sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
                long userId = sharedPreferenceManager.getUser().id;
                apiInterface = APIClient.getClient().create(APIInterface.class);
                User user = new User();
                user.setId(userId);
                user.setHba1cReading(hba1cDouble);
                Call<UserBaseResponse> call = apiInterface.hba1c(user);
                call.enqueue(new Callback<UserBaseResponse>() {
                    @Override
                    public void onResponse(Call<UserBaseResponse> call, Response<UserBaseResponse> response) {
                        UserBaseResponse hba1cResponse = response.body();
                        if (hba1cResponse.getStatus().equals("SUCCESS")) {
                            Toast.makeText(Hba1c.this, hba1cResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Hba1c.this, Summary.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Hba1c.this, hba1cResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserBaseResponse> call, Throwable t) {
                        Toast.makeText(Hba1c.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }
}