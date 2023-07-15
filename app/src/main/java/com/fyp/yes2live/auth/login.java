package com.fyp.yes2live.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.ActivityLevel;
import com.fyp.yes2live.R;
import com.fyp.yes2live.SharedPreferenceManager;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.homepage;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.question1;
import com.fyp.yes2live.response.BaseResponse;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {
    // 1 image 1 textview 2 textinputedittext 1 button 1 textview
    Button login_btn;
    TextView signup_btn;
    APIInterface apiInterface;

    SharedPreferenceManager sharedPreferenceManager;
    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

     EditText email;
     EditText password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencing email and password
        email = (EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);

       signup_btn = (TextView) findViewById(R.id.signup);

       //button1
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
               Intent intent = new Intent(login.this, signup.class);
               startActivity(intent);
          }
       });

       login_btn = (Button) findViewById(R.id.loginBtn);
        login_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emailInput = email.getText().toString().trim();
               String passwordInput =password.getText().toString().trim();
               validateEmail();
               validatePassword();
               if(!validateEmail()||!validatePassword()){
                   Toast.makeText(login.this,"enter correct email and password" , Toast.LENGTH_SHORT).show();
               }
               else {
                   /**
                    login endpoint
                    **/
                   apiInterface = APIClient.getClient().create(APIInterface.class);//calling get client method of API client class fpr getting api urls etc (object)
                   User user = new User(emailInput,passwordInput);//initialize the user constructor see User class
                   Call<BaseResponse> call = apiInterface.login(user);//calling login method of api interface
                   //call.enque is used to catch the response of api
                   call.enqueue(new Callback<BaseResponse>() {//call.enque have two default methods one is onResponse(that hold the api success and failure  response both) and on failure(hold the response if api is not working)
                       @Override
                       public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                           BaseResponse loginResponse = response.body();
                           if (loginResponse.getStatus().equals("SUCCESS")) {
                               sharedPreferenceManager.saveUser(loginResponse.getPayload());
                               Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                               Intent intent1 = new Intent(login.this, homepage.class);
                               startActivity(intent1);
                           }else{
                               Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                       }

                       @Override
                       public void onFailure(Call<BaseResponse> call, Throwable t) {
                           Toast.makeText(login.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                       }

                   });
               }

           };
       });
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
    }
    private boolean validateEmail() {

        // Extract input from EditText
        String emailInput = email.getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPreferenceManager.isLoggedIn()) {
            if (sharedPreferenceManager.isQuestionnaire()) {
                if (sharedPreferenceManager.isActivityLevel()) {
                    Intent intent = new Intent(login.this, homepage.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(login.this, ActivityLevel.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }else{
                Intent intent = new Intent(login.this, question1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        }
    }

}