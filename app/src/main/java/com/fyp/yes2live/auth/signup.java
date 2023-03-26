package com.fyp.yes2live.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class signup extends AppCompatActivity {

    Button signup_button;
    APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    private Pattern mobilepattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    EditText email;
    EditText password;
    EditText name;
    EditText repass;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());

        // Referencing email and password
        email = (EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.pass);
        name =(EditText) findViewById(R.id.name);
        repass =(EditText) findViewById(R.id.repass);
        phone =(EditText) findViewById(R.id.phone);
        signup_button = (Button) findViewById(R.id.signup);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                validateEmail();
                validatePassword();
                validateName();
                isNumeric();
                if(!validateEmail()||!validatePassword()||!validateName()||!isNumeric()){
                    Toast.makeText(signup.this,"fill all fields  " , Toast.LENGTH_SHORT).show();
                }
                else {

                    /**
                     login endpoint
                     **/
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    User user = new User(name.getText().toString(),emailInput,passwordInput);
                    Call<BaseResponse> call = apiInterface.signUp(user);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            BaseResponse loginResponse = response.body();
                            if (loginResponse.getStatus().equals("SUCCESS")) {
                                sharedPreferenceManager.saveUser(loginResponse.getPayload());
                                Toast.makeText(signup.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(signup.this, question1.class);
                                startActivity(intent1);
                            }else{
                                Toast.makeText(signup.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            Toast.makeText(signup.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
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
        String repasswordInput = repass.getText().toString().trim();
        boolean status=false;
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
           return false;
        }
       if (repasswordInput.isEmpty()) {
            repass.setError("Field can not be empty");
           return false;
        }
        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
           return false;
        }
        else if(!passwordInput.equals(repasswordInput)){
            repass.setError("repassword is not matched");
           return false;
        }
        else {
            password.setError(null);
            return true;
        }

    }

    public boolean validateName(){

        String nameinput = name.getText().toString().trim();
        // if name field is empty
        // it will display error message "Field can not be empty"
        if (nameinput.isEmpty()) {
            name.setError("Field can not be empty");
            return false;
        }else {
            return true;
        }
    }

    public boolean isNumeric() {
        String mobile = phone.getText().toString().trim();
        if (mobile.isEmpty()) {
            phone.setError("Field can not be empty");
            return false;
        }else if (!mobilepattern.matcher(mobile).matches()) {
            phone.setError("invalid input");
            return false;
        }
        else{
            return true;
        }
    }

}