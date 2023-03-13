package com.fyp.yes2live.helper;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {
    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    public String[] validateEmail(String email) {
        String arry[] = new String[2];
        // Extract input from EditText
        String emailInput = email.trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
            arry[0] = "Field can not be empty";
            arry[1] = "0";
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            arry[0] = "Please enter a valid email address";
            arry[1] = "0";
        } else {
            arry[0] = null;
            arry[1] = "1";
        }

        return arry;
    }

    public String[] validatePassword(String password) {
        String arry[] = new String[2];
;        String passwordInput = password.trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            arry[0] = "Field can not be empty";
            arry[1] = "0";
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            arry[0] = "Password is too weak";
            arry[1] = "0";
        } else {
            arry[0] = null;
            arry[1] = "1";
        }

        return arry;
    }
}
