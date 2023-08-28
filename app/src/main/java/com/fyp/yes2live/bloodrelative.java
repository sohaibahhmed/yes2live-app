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

public class bloodrelative extends AppCompatActivity {
    Button button;
    private RadioGroup rgBloodRelatives;
    private Boolean isdiabetic;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodrelative);
        button=(Button) findViewById(R.id.nextbtn);
        rgBloodRelatives = findViewById(R.id.gender_Grp);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rgBloodRelatives.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(bloodrelative.this, "Select any one", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                    int selectedId = rgBloodRelatives.getCheckedRadioButtonId();
                    if(selectedId==R.id.yesbtn){
                        isdiabetic=true;
                    }
                    else {
                        isdiabetic=false;
                    }
                    Intent intent = new Intent(bloodrelative.this, AnyDisease.class);
                    intent.putExtra("diabetic_family", isdiabetic);
                    startActivity(intent);
                }

            }});
    }
}