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
import com.fyp.yes2live.response.UserBaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pregnant extends AppCompatActivity {
    Button button;
    private RadioGroup rgPregnant;
    private RadioButton yes, no;
    private APIInterface apiInterface;
    SharedPreferenceManager sharedPreferenceManager;

    private Boolean isPregnant;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant);
        button=(Button) findViewById(R.id.nextbtn);
        rgPregnant = findViewById(R.id.pregnant_Grp);
        yes = findViewById(R.id.yesbtn);
        no = findViewById(R.id.nobtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rgPregnant.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(pregnant.this, "Select any one", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    int selectedId = rgPregnant.getCheckedRadioButtonId();
                    if (selectedId == R.id.yesbtn) {
                        isPregnant = true;
                    } else {
                        isPregnant = false;
                    }
                    Intent intent = new Intent(pregnant.this, bloodrelative.class);
                    intent.putExtra("pregnant", isPregnant);
                    startActivity(intent);
                }
            }

        });
}}