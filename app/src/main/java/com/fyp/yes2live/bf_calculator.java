package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bf_calculator extends AppCompatActivity {
// 3 text views one image one button one realtive layout
    android.widget.Button mrecalculatebf;


    TextView mbfdisplay, mbfcategory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbf;
    double BFP=0.0;//final ans is save in this

    String height;
    String weight;
    String waist;
    String neck;
    float floatheight, floatweight,floatWaist, floatNeck;
    RelativeLayout mbackground;
    RelativeLayout mtextcolor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf_calculator);

        intent=getIntent();
        mbfdisplay=findViewById(R.id.bfresult);
        mbfcategory=findViewById(R.id.bfcategory);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        mimageview=findViewById(R.id.imageview);
        mrecalculatebf=findViewById(R.id.recalculatebf);


        height=intent.getStringExtra("Height");
        weight=intent.getStringExtra("Weight");
        waist=intent.getStringExtra("waist");
        neck=intent.getStringExtra("neck");
        floatheight =Float.parseFloat(height);
        floatweight =Float.parseFloat(weight);
        floatWaist=Float.parseFloat(waist);
        floatNeck =Float.parseFloat(neck);

       if(intent.getStringExtra("gender").equalsIgnoreCase("male"))// if gender from previous screen is male
       {
//           log10(waist-neck)
           double logWN= Math.log10(floatWaist- floatNeck);// math. is built in class of java
//           log10(height)
           double logH=Math.log10(floatheight);
           BFP = 495/(((1.0324 - 0.19077)*logWN) + (0.15456)*(logH))- 450;
       }else{
           double logWN= Math.log10(floatWaist- floatNeck);
           double logH=Math.log10(floatheight);
           BFP = 495/(((1.0324 - 0.19077)*logWN) + (0.15456)*(logH))- 450;
       }



        if(BFP<10){
            mbfcategory.setText("Less than Essential Fat");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.cross);
        }
        else if(BFP>=10 && BFP<14){
            mbfcategory.setText("Essential");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(BFP>=14 && BFP<21){
            mbfcategory.setText("Athletes");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(BFP>=21 && BFP<25){
            mbfcategory.setText("Fitness");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }
        else if(BFP>=25 && BFP<32){
            mbfcategory.setText("Average");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else{
            mbfcategory.setText("obese");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbfdisplay.setText(mbf);

        //getSupportActionBar().hide();
        mrecalculatebf=findViewById(R.id.recalculatebf);
        mrecalculatebf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(bf_calculator.this, bmiactivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}