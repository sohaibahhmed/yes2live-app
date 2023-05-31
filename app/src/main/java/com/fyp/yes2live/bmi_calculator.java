package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmi_calculator extends AppCompatActivity {
   // the bmi calculator screen have three textviews one image and one button
    android.widget.Button mrecalculatebmi;


    TextView mbmidisplay, mbmicategory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbmi;// string variable
    float floatbmi;// contain the value of bmi

    String height;
    String weight;
    float floatheight, floatweight;
    RelativeLayout mbackground;// relative layout
    RelativeLayout mtextcolor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        intent=getIntent();//get an intent
        mbmidisplay=findViewById(R.id.bmiresult);// textview of bmi(55)
        mbmicategory=findViewById(R.id.bmicategory);// text view (your bmi category)
        mgender=findViewById(R.id.genderdisplay);//textview (gender)
        mbackground=findViewById(R.id.contentlayout);// relative layout
        mimageview=findViewById(R.id.imageview);// image(tick one )
        mrecalculatebmi=findViewById(R.id.recalculatebmi);//button (recalculate bmi)
    // getextra is used to get data along intent from another screen

        height=intent.getStringExtra("Height");
        weight=intent.getStringExtra("Weight");
        floatheight =Float.parseFloat(height);// changing to float from string
        floatweight =Float.parseFloat(weight);

        floatheight = floatheight /100;//convert height from cm to m
        floatbmi = floatweight /(floatheight * floatheight);// fromula to calculate bmi
        mbmi=Float.toString(floatbmi);//cahnge value from float to string



        if(floatbmi <16){
            mbmicategory.setText("Severe Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));// layout become red
            mimageview.setImageResource(R.drawable.cross);// cross picture
        }
        else if(floatbmi <16.9 && floatbmi >16){
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(floatbmi <18.4 && floatbmi >17){
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(floatbmi <25 && floatbmi >18.4){
            mbmicategory.setText("Normal");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }
        else if(floatbmi <29.4 && floatbmi >25){
            mbmicategory.setText("Over Weight");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else{
            mbmicategory.setText("Obese Class 1");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

        //getSupportActionBar().hide();
        mrecalculatebmi=findViewById(R.id.recalculatebmi);
        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(bmi_calculator.this, bmiactivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}