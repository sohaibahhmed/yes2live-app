package com.fyp.yes2live.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.homepage;
import com.fyp.yes2live.model.User;
import com.fyp.yes2live.response.BaseResponse;

import java.util.regex.Pattern;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {
    Button login_btn;
    Button signup_btn;
    APIInterface apiInterface;

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

     EditText email;
     EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencing email and password
        email = (EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);

       signup_btn = (Button) findViewById(R.id.signupBtn);

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
               String passwordInput = password.getText().toString().trim();
               validateEmail();
               validatePassword();
               if(!validateEmail()||!validatePassword()){
                   Toast.makeText(login.this,"enter email password" , Toast.LENGTH_SHORT).show();
               }
               else {
                   /**
                    login endpoint
                    **/
                   apiInterface = APIClient.getClient().create(APIInterface.class);
                   User user = new User(emailInput,passwordInput);
                   Call<BaseResponse> call = apiInterface.login(user);
                   call.enqueue(new Callback<BaseResponse>() {
                       @Override
                       public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                           BaseResponse loginResponse = response.body();
                           if (loginResponse.getStatus().equals("SUCCESS")) {
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
    public void confirmInput(View v) {
        if (!validateEmail() || !validatePassword()) {

            return;
        }

        // if the email and password matches, a toast message
        // with email and password is displayed
        String input = "Email: " + email.getText().toString();
        input += "\n";
        input += "Password: " + password.getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

    }


}