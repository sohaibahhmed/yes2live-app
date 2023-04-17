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

    android.widget.Button mrecalculatebf;


    TextView mbfdisplay, mbfcategory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbf;
    float intbf;

    String height;
    String weight;
    String waist;
    String neck;
    float intheight,intweight,intWaist,intNeck;
    RelativeLayout mbackground;
    RelativeLayout mtextcolor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf_calculator);

//        getSupportActionBar().setElevation(0);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
//        getSupportActionBar().setTitle("Result");
//        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);


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
        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);
        intWaist=Float.parseFloat(waist);
        intNeck=Float.parseFloat(neck);


        double BFP=0.0;
       if(intent.getStringExtra("gender").equalsIgnoreCase("male"))
       {
//           log10(waist-neck)
           double logWN= Math.log10(intWaist-intNeck);
//           log10(height)
           double logH=Math.log10(intheight);
           BFP = 495/(((1.0324 - 0.19077)*logWN) + (0.15456)*(logH))- 450;
       }else{
           //BFP = 495/(1.29579 - 0.35004×log10(waist+hip-neck) + 0.22100×log10(height))- 450
       }




        if(BFP<16){
            mbfcategory.setText("Severe Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.cross);
        }
        else if(intbf<16.9 && intbf>16){
            mbfcategory.setText("Moderate Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbf<18.4 && intbf>17){
            mbfcategory.setText("Mild Thinness");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbf<25 && intbf>18.4){
            mbfcategory.setText("Normal");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }
        else if(intbf<29.4 && intbf>25){
            mbfcategory.setText("Over Weight");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else{
            mbfcategory.setText("Obese Class 1");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbfdisplay.setText(mbf);

        //getSupportActionBar().hide();
        mrecalculatebf=findViewById(R.id.recalculatebmi);
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