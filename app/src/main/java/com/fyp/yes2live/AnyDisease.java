package com.fyp.yes2live;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AnyDisease extends AppCompatActivity {
//    declaration of variables
    CheckBox hypertension, cardiovascular, none;
    AlertDialog hyperdialog;
    AlertDialog cardiodialog;
    Boolean BP, Cholestrol,Others,None = false;
    EditText systolic,diastolic;
    EditText cardio_range;
    Integer sys,dias, choles = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_disease);

//        previousBtn= findViewById(R.id.PreviousButton);
        hypertension = findViewById(R.id.hypertension);
        cardiovascular = findViewById(R.id.cardiovascular);
        none = findViewById(R.id.noDisease);

//      hypertension popup start
        this.openHypertensionPopup();
//      hypertension popup end

//        previousBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                goto previous screen
//            }
//        });

        this.checkNonOption();

//      cardiovascular popup start
        this.setCardiovascularPopup();
//      cardiovascular popup end
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void clickNext(View view) {

        if(cardiovascular.isChecked()){
            Intent i1=new Intent(AnyDisease.this,Hba1c.class);
            i1.putExtra("married",getIntent().getBooleanExtra("married",false));
            i1.putExtra("pragnant",getIntent().getBooleanExtra("pregnant",false));
            i1.putExtra("diabetic_family",getIntent().getBooleanExtra("diabetic_family",false));
            i1.putExtra("cholesterol_ranges",Integer.valueOf(cardio_range.getText().toString()));
            i1.putExtra("systolic_ranges",sys);
            i1.putExtra("diastolic_ranges",dias);
            i1.putExtra("blood_pressure",BP);
            i1.putExtra("cholesterol",Cholestrol);
            i1.putExtra("other_diseases",Others);
            i1.putExtra("no_diseases",None);
            startActivity(i1);
        }

        if(hypertension.isChecked()){
            Intent i=new Intent(AnyDisease.this,Hba1c.class);
            i.putExtra("married",getIntent().getBooleanExtra("married",false));
            i.putExtra("pragnant",getIntent().getBooleanExtra("pregnant",false));
            i.putExtra("diabetic_family",getIntent().getBooleanExtra("diabetic_family",false));
            i.putExtra("cholesterol_ranges",Integer.valueOf(choles));
            i.putExtra("systolic_ranges",Integer.valueOf(systolic.getText().toString()));
            i.putExtra("diastolic_ranges",Integer.valueOf(diastolic.getText().toString()));
            i.putExtra("blood_pressure",BP);
            i.putExtra("cholesterol",Cholestrol);
            i.putExtra("other_diseases",Others);
            i.putExtra("no_diseases",None);
            startActivity(i);
        }

        if(hypertension.isChecked() && cardiovascular.isChecked() ){
            //Disease=1;
            //Others=true;
            Intent i=new Intent(AnyDisease.this,Hba1c.class);
            i.putExtra("married",getIntent().getBooleanExtra("married",false));
            i.putExtra("pragnant",getIntent().getBooleanExtra("pregnant",false));
            i.putExtra("diabetic_family",getIntent().getBooleanExtra("diabetic_family",false));
            i.putExtra("cholesterol_ranges",Integer.valueOf(cardio_range.getText().toString()));
            i.putExtra("systolic_ranges",Integer.valueOf(systolic.getText().toString()));
            i.putExtra("diastolic_ranges",Integer.valueOf(diastolic.getText().toString()));
            i.putExtra("blood_pressure",BP);
            i.putExtra("cholesterol",Cholestrol);
            i.putExtra("other_diseases",Others);
            i.putExtra("no_diseases",None);
            startActivity(i);
        }


        if(none.isChecked())
        {
            Intent i=new Intent(AnyDisease.this,Hba1c.class);
            i.putExtra("married",getIntent().getBooleanExtra("married",false));
            i.putExtra("pragnant",getIntent().getBooleanExtra("pregnant",false));
            i.putExtra("diabetic_family",getIntent().getBooleanExtra("diabetic_family",false));
            i.putExtra("cholesterol_ranges",choles);
            i.putExtra("systolic_ranges",sys);
            i.putExtra("diastolic_ranges",dias);
            i.putExtra("blood_pressure",BP);
            i.putExtra("cholesterol",Cholestrol);
            i.putExtra("other_diseases",Others);
            i.putExtra("no_diseases",None);
            startActivity(i);
        }

    }

    public void setCardiovascularPopup(){
        AlertDialog.Builder builderCardio = new AlertDialog.Builder(this);

        View viewCardio = getLayoutInflater().inflate(R.layout.cardiovascular_report, null);
        cardio_range=viewCardio.findViewById(R.id.cardioReport);
        builderCardio.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cardio_range.getText().toString().isEmpty()) {
                    Toast.makeText(AnyDisease.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                    hyperdialog.show();
                    hypertension.setChecked(false);
                }
                Log.d("Magic", "Cholestrol_Range: " + Double.valueOf(cardio_range.getText().toString()));
                cardiodialog.dismiss();
                none.setEnabled(false);
                Cholestrol=true;
                Log.d("Magic", "Cholestrol: " + Cholestrol);
            }
        });

        builderCardio.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cardiodialog.dismiss();
                cardiovascular.setChecked(false);
                none.setEnabled(true);
                Cholestrol=false;
                Log.d("Magic", "Cholestrol: " + Cholestrol);
            }
        });

        builderCardio.setView(viewCardio);

        //Create Dialog
        cardiodialog = builderCardio.create();
        cardiovascular.setOnClickListener(
            new View.OnClickListener() {
                @Override public void onClick(View view)
                {

                    if (cardiovascular.isChecked()) {
                        cardiodialog.show();
                    }
                    else {
                        none.setEnabled(true);
                        cardiovascular.setTextColor(
                                getResources().getColor(
                                        R.color.black));
                    }

                    cardiovascular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (cardiovascular.isChecked()) {
                                cardiodialog.show();
                            }
                            else {
                                none.setEnabled(true);
                                cardiovascular.setTextColor(
                                        getResources().getColor(
                                                R.color.black));
                            }
                        }
                    });

                }
            });
    }
    public void checkNonOption(){
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(none.isChecked()){
                    hypertension.setEnabled(false);
                    cardiovascular.setEnabled(false);
                }
                else {
                    hypertension.setEnabled(true);
                    cardiovascular.setEnabled(true);
                }
                none.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(none.isChecked()){
                            hypertension.setEnabled(false);
                            cardiovascular.setEnabled(false);
                        }
                        else {
                            hypertension.setEnabled(true);
                            cardiovascular.setEnabled(true);
                        }
                    }
                });
            }
        });
    }
    public void openHypertensionPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.hypertension_report, null);
        systolic=view.findViewById(R.id.systolic);
        diastolic=view.findViewById(R.id.diastolic);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (systolic.getText().toString().isEmpty() || diastolic.getText().toString().isEmpty()) {
                    Toast.makeText(AnyDisease.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                    hyperdialog.show();
                    hypertension.setChecked(false);
                }
                systolic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        systolic.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                if (systolic.getText().toString().isEmpty()) {
                                    systolic.setError("Enter systolic value");
                                    systolic.requestFocus();
                                    hyperdialog.show();
                                    return;
                                }
                                if(sys < 90 ){
                                    systolic.setError("Low value");
                                    systolic.requestFocus();
                                    hyperdialog.show();
                                    return;
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {}
                        });

                    }
                });

                hyperdialog.dismiss();
                none.setEnabled(false);
                BP = true;
                Log.d("Magic", "BP: " + BP);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hyperdialog.dismiss();
                hypertension.setChecked(false);
                none.setEnabled(true);
                BP=false;
            }
        });

        builder.setView(view);
        hyperdialog = builder.create();
        hypertension.setOnClickListener(
        new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                if (hypertension.isChecked()) {
                    hyperdialog.show();
                }
                else {
                    none.setEnabled(true);
                    hypertension.setTextColor(
                            getResources().getColor(
                                    R.color.black));
                }
                hypertension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (hypertension.isChecked()) {
                            hyperdialog.show();
                        }
                        else {
                            none.setEnabled(true);
                            hypertension.setTextColor(
                                    getResources().getColor(
                                            R.color.black));
                        }
                    }
                });
            }
        });
    }
}