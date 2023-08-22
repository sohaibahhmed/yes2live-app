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
                    Intent intent = new Intent(married.this, pregnant.class);
                    intent.putExtra("married",isMarried);
                    startActivity(intent);
                }
                else {
                    isMarried=false;
                    Intent intent = new Intent(married.this, bloodrelative.class);
                    intent.putExtra("married",isMarried);
                    startActivity(intent);
                }
            }

        });

    }
}