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

public class bmiactivity extends AppCompatActivity {

    android.widget.Button mcalculatebmi;

    TextView mcurrentheight; //text view of height
    TextView mcurrentage, mcurrentweight;
    ImageView mincrementage, mincrementweight, mdecrementage, mdecrementweight;
    SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;

    int intweight=55;// we add or minus one from this on increment and decrement
    int intage=25;
    int currentprogress;//used for seekbar(current value or heght of seekbar)
    String mintprogress="170";//string type variable used for height
    String typeofuser="0";//whether the user is male or female
    String weight2="55";//this variable is used to show on textview
    String age2="25";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
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


        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));//when click on male its colour changes (focusmf)
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.feature_bg));
                typeofuser="Male";
            }
        });
        // same in female case heighlight the female
        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.feature_bg));
                typeofuser="Female";
            }
        });


        mseekbarforheight.setMax(300);//max value of height on seekbar
        mseekbarforheight.setProgress(170);//initial vale of seekbar

        //seekbar working
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentprogress=i;//height from seekbar u decide
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }
         //default method of setonbarSeekBarchangelistener must include
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





    //calculate bmi (formula)

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
                else{
                    Intent intent=new Intent(bmiactivity.this, bmi_calculator.class);
        // putextra is used to send data to next screen with intent
                    intent.putExtra("gender", typeofuser);
                    intent.putExtra("Height", mintprogress);
                    intent.putExtra("Weight", weight2);
                    intent.putExtra("Age", age2);


                    startActivity(intent);
                }



            }
        });




    }
}