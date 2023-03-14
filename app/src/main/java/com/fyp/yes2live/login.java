package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyp.yes2live.api.RetrofitClient;
import com.fyp.yes2live.response.LoginResponse;
import com.fyp.yes2live.response.LoginUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    Button login_btn;
    Button signup_btn;

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
               AndroidNetworking.initialize(getApplicationContext());

               LoginUser login = new LoginUser();
               login.setEmail(emailInput);
               login.setPassword(passwordInput);
               // Actually making the GET request
               AndroidNetworking.get("http://153.156.163.115:8070/api/login")
                       .addQueryParameter("email", emailInput)
                       .addQueryParameter("password", passwordInput)
                       .build()
                       .getAsJSONObject(new JSONObjectRequestListener() {
                           @Override
                           public void onResponse(JSONObject response) {
                               // Text will show success if Response is success


                               try {
                                   if (response.getString("status").equals("SUCCESS")) {
                                        Intent intent = new Intent(login.this, homepage.class);
                                       Intent intent1 = new Intent(login.this, homepage.class);
                                       startActivity(intent1);
                                    } else {
                                        Toast.makeText(login.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                               } catch (JSONException e) {
                                   throw new RuntimeException(e);
                               }
                           }

                           @Override
                           public void onError(ANError anError) {
                               System.out.println("testt");
                           }
                       });

               //}
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


    private void userLogin(String email, String password) {

        AndroidNetworking.get("http://153.156.163.115:8070/api/login/api/login")
                .addQueryParameter("email", email)
                .addQueryParameter("password", password)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("response.get(0)");
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
//        Call<LoginResponse> getData = RetrofitClient
//                .getInstance()
//                .getApi()
//                .login(email, password);
//        getData.enqueue(new Callback<LoginResponse>() {
//
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                LoginResponse loginResponse = response.body();
//                if (response.isSuccessful()) {
//                    if (loginResponse.getStatus().equals("SUCCESS")) {
//                        sharedPreferenceManager.saveUser(loginResponse.getPayload());
//                        Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(login.this, homepage.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } else {
//                    Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(login.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }


}