package com.fyp.yes2live.Admin.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.ExerciseBaseResponse;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.UserListBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFeature extends AppCompatActivity {

    private String userName;
    private TextView txtViewUserName,txtViewEmail,txtViewGender,txtViewWeight,txtViewHeight;

    private APIInterface apiInterface;

    private void usePojo(String userName) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserListBaseResponse> call = apiInterface.getByUserName(userName);
        call.enqueue(new Callback<UserListBaseResponse>() {
            @Override
            public void onResponse(Call<UserListBaseResponse> call, Response<UserListBaseResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("SUCCESS")) {

                    User user = response.body().getPayload().get(0);
                    txtViewUserName.setText(user.getUsername());
                    txtViewEmail.setText(user.getEmail());
                    txtViewGender.setText(user.getGender());
                    txtViewWeight.setText(String.valueOf(user.getWeight()));
                    txtViewHeight.setText(String.valueOf(user.getHeight()));

                } else {
                    Toast.makeText(UsersFeature.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserListBaseResponse> call, Throwable t) {
                Toast.makeText(UsersFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        txtViewUserName = findViewById(R.id.txtUserName);
        txtViewEmail = findViewById(R.id.txtEmail);
        txtViewGender = findViewById(R.id.txtGender);
        txtViewWeight = findViewById(R.id.txtWeight);
        txtViewHeight = findViewById(R.id.txtHeight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feature);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        initViews();
        usePojo(userName);

    }
}