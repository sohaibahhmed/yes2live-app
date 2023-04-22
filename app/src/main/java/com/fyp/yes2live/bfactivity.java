package com.fyp.yes2live;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class bfactivity extends AppCompatActivity {
    android.widget.Button mcalculatebmi;

    TextView mcurrentheight;
    TextView mcurrentage, mcurrentweight,mcurrentneck,mcurrentwaist;
    ImageView mincrementage, mincrementweight, mdecrementage, mdecrementweight,mincrementneck,mincrementwaist,mdecrementwaist,mdecrementneck;
    SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;

    int intweight=55;
    int intage=25;
    int intneck=50;
    int intwaist=96;
    int currentprogress;
    String mintprogress="170";
    String typeofuser="0";
    String weight2="55";
    String age2="25";
    String neck2="50";
    String waist2="96";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfactivity);
        // getSupportActionBar().hide();
        mcalculatebmi=findViewById(R.id.calculatebmi);
        mcurrentage=findViewById(R.id.currentage);
        mcurrentweight=findViewById(R.id.currentweight);
        mcurrentheight=findViewById(R.id.currentheight);
        mseekbarforheight=findViewById(R.id.seekbarheight);
        mincrementage=findViewById(R.id.plussage);
        mincrementweight=findViewById(R.id.plussweight);
        mdecrementage=findViewById(R.id.minusage);
        mdecrementweight=findViewById(R.id.minusweight);
        mmale=findViewById(R.id.male_2);
        mfemale=findViewById(R.id.female_2);
        mcurrentneck=findViewById(R.id.currentneck);
        mcurrentwaist=findViewById(R.id.currentwaist);
        mincrementwaist=findViewById(R.id.plusswaist);
        mincrementneck=findViewById(R.id.plusneck);
        mdecrementwaist=findViewById(R.id.minuswaist);
        mdecrementneck=findViewById(R.id.minusneck);


        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.feature_bg));
                typeofuser="Male";
            }
        });
        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.feature_bg));
                typeofuser="Female";
            }
        });


        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentprogress=i;
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage+1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });



        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });



        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });


        mincrementneck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intneck=intneck+1;
                neck2=String.valueOf(intneck);
                mcurrentneck.setText(neck2);
            }
        });

        mdecrementneck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intneck=intneck-1;
                neck2=String.valueOf(intneck);
                mcurrentneck.setText(neck2);
            }
        });
        mincrementwaist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intwaist=intwaist+1;
                waist2=String.valueOf(intwaist);
                mcurrentwaist.setText(waist2);
            }
        });

        mdecrementwaist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intwaist=intwaist-1;
                waist2=String.valueOf(intwaist);
                mcurrentwaist.setText(waist2);
            }
        });




        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typeofuser.equals("0")){
                    Toast.makeText(getApplicationContext(), "Select Your Gender First", Toast.LENGTH_SHORT).show();
                }
                else if (mintprogress.equals("0")){
                    Toast.makeText(getApplicationContext(), "Select Your Height First", Toast.LENGTH_SHORT).show();
                }
                else if (intage == 0 || intage<0){
                    Toast.makeText(getApplicationContext(), "Please Input Correct Age", Toast.LENGTH_SHORT).show();
                }
                else if (intweight == 0 || intweight<0){
                    Toast.makeText(getApplicationContext(), "Please Input Correct Weight", Toast.LENGTH_SHORT).show();
                }
                else if (intneck == 0 || intneck<0){
                    Toast.makeText(getApplicationContext(), "Please Input Correct neck ", Toast.LENGTH_SHORT).show();
                }
                else if (intwaist == 0 || intwaist<0){
                    Toast.makeText(getApplicationContext(), "Please Input Correct Waist", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(bfactivity.this, bf_calculator.class);

                    intent.putExtra("gender", typeofuser);
                    intent.putExtra("Height", mintprogress);
                    intent.putExtra("Weight", weight2);
                    intent.putExtra("Age", age2);
                    intent.putExtra("waist", waist2);
                    intent.putExtra("neck", neck2);


                    startActivity(intent);
                }



            }
        });




    }

}