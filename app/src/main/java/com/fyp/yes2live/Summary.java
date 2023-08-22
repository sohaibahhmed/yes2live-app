package com.fyp.yes2live;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.GetHealthDetails;
import com.fyp.yes2live.response.UserAssessmentResponse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Summary extends AppCompatActivity {

    BarChart barChart;
    Button fastingtest, randomSugarTest;
    int patientId;
    // variable for our bar data set.
    BarDataSet barDataSet1, barDataSet2;
    int prefer;
    AlertDialog preferenceDialog;
    int position=0;

    // array list for storing entries.
    ArrayList barEntries;
    SharedPreferenceManager sharedPreferenceManager;
    private APIInterface apiInterface;
    long user_id;
    String date;
    TextView user_age,gender,user_height,userWeight,date_start,disease_category,status,improvementStatus;
    // creating a string array for displaying days.
    String[] record = new String[]{"Baseline", "3 Months", "6 Months", "9 Months"};
    int age;

    ImageButton previousButton;
    Button update;
    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //getSupportActionBar().hide();
        previousButton = findViewById(R.id.PreviousButton);
        update=findViewById(R.id.updateBtn);
        gender = findViewById(R.id.Usergender);
        user_height = findViewById(R.id.HeightNo);
        userWeight = findViewById(R.id.WeightNo);
        date_start = findViewById(R.id.dateStart);
        disease_category = findViewById(R.id.value);
        improvementStatus = findViewById(R.id.status);
        fastingtest = findViewById(R.id.fastingReport);
        randomSugarTest = findViewById(R.id.randomSugarreport);
        user_age = findViewById(R.id.age_no);
        View dietplan = findViewById(R.id.dietPlanCard);
        View Workoutplan = findViewById(R.id.workplan);
        date = getIntent().getStringExtra("date");
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        user_id = sharedPreferenceManager.getUser().getId();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Summary.this,dietaryinfo.class);
                startActivity(intent);
            }
        });

        String date= sharedPreferenceManager.getReportDate();
        Log.d("Magic", "Date: " + date);

        //GetUserName
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GetHealthDetails> call = apiInterface.getHealthDetails(user_id, date);

        call.enqueue(new Callback<GetHealthDetails>() {
            @Override
            public void onResponse(Call<GetHealthDetails> call, Response<GetHealthDetails> response) {
                Log.d("Magic2", "DETAILS: " + response.body());
                Log.d("Magic2", "DETAILS: " + response.errorBody());

                GetHealthDetails getHealthDetails = response.body();

                if (response.isSuccessful()) {
                    if (getHealthDetails.getStatus().equals("SUCCESS")) {
                        // Toast.makeText(UserSummary.this, getHealthDetails.getMessage(), Toast.LENGTH_SHORT).show();
                        patientId = getHealthDetails.getGetHealthDetailsPayload().getDiabetic_patient_id();
                        Log.d("Magic", "patient id:  " + patientId);
                        Log.d("Magic", "age:  " + getHealthDetails.getGetHealthDetailsPayload().getAge());

                        Log.d("Magic", "DETAILS: " + getHealthDetails.getGetHealthDetailsPayload());
                        user_age.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getAge()));
                        String user_gender = getHealthDetails.getGetHealthDetailsPayload().getGender();
                        if (user_gender.equalsIgnoreCase("female")) {
                            gender.setText("F");
                        } else {
                            gender.setText("M");
                        }
                        user_height.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getHeight()));
                        userWeight.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getWeight()));
                        date_start.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getHba1c_date()));
                        disease_category.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getDisease_category()));
                        improvementStatus.setText(String.valueOf(getHealthDetails.getGetHealthDetailsPayload().getImprovement_status()));
                    } else {
                        Toast.makeText(Summary.this, getHealthDetails.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(Summary.this, getHealthDetails.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetHealthDetails> call, Throwable t) {
                Log.d("Magic2", "onFailure: " + t.getMessage());
                Toast.makeText(Summary.this, "Check you Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });


        AlertDialog.Builder preferenceBuilder = new AlertDialog.Builder(this);

        final String[] listPreference = getResources().getStringArray(R.array.choice_preference);
        preferenceBuilder.setTitle("Preference");
        preferenceBuilder.setSingleChoiceItems(listPreference, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                position = i;
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String preference = listPreference[position];

                    if(listPreference[position].equals("Gym")){
                        prefer=0;
                    }
                    else {
                        prefer=1;
                    }
                    sharedPreferenceManager.saveUserPreference(prefer);
                    Log.d("magic", "onClick: "+prefer);
                    preferenceDialog.dismiss();
                }
            });
        preferenceDialog = preferenceBuilder.create();

        fastingtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Summary.this, FastingReport.class);
                i.putExtra("patient_id", patientId);
                startActivity(i);
            }
        });

        randomSugarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Summary.this, RandomTest.class);
                intent.putExtra("patient_id", patientId);
                startActivity(intent);
            }
        });

        dietplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferenceManager.isUserWarning()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Summary.this);
                    //Set body message of Dialog
                    builder.setMessage(sharedPreferenceManager.getUserWarning());
                    // Is dismiss when touching outside?
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.closeOptionsMenu();
                    dialog.setTitle("Warning");
                    dialog.show();
                } else {
                    Intent i = new Intent(Summary.this, Dietplan.class);
                    startActivity(i);
                }
            }
        });

        Workoutplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferenceManager.isUserWarning()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Summary.this);
                    //Set body message of Dialog
                    builder.setMessage(sharedPreferenceManager.getUserWarning());
                    // Is dismiss when touching outside?
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.closeOptionsMenu();
                    dialog.setTitle("Warning");
                    dialog.show();
                }
                else if(sharedPreferenceManager.isSelectPrefernce()){
                    Intent intent = new Intent(Summary.this, ExercisePlan.class);
                    startActivity(intent);
                }
                else {
                    preferenceDialog.show();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Summary.this, homepage.class);
                startActivity(i);
            }
        });

        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);

        barChart.setY(0);
        // creating a new bar data set.
        barDataSet1 = new BarDataSet(getBarEntriesOne(), "HbA1c");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.hba1cColor));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Weight (kg)");
        barDataSet2.setColor(getApplicationContext().getResources().getColor(R.color.weightColor));

        // below line is to add bar data set to our bar data.
        BarData data = new BarData(barDataSet1, barDataSet2);

        // after adding data to our bar data we
        // are setting that data to our bar chart.
        barChart.setData(data);
        barChart.setFitBars(false);
        // below line is to remove description
        // label of our bar chart.
        barChart.getDescription().setEnabled(false);

        // below line is to get x axis
        // of our bar chart.
        XAxis xAxis = barChart.getXAxis();

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.setValueFormatter(new IndexAxisValueFormatter(record));

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(true);

        // below line is to set position
        // to our x-axis to bottom.
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // below line is to set granularity
        // to our x axis labels.
        xAxis.setGranularity(1);

        // below line is to enable
        // granularity to our x axis.
        xAxis.setGranularityEnabled(true);

        // below line is to make our
        // bar chart as draggable.
        barChart.setDragEnabled(true);

        // below line is to make visible
        // range for our bar chart.
        barChart.setVisibleXRangeMaximum(3);

        // below line is to add bar
        // space to our chart.
        float barSpace = 0.0f;

        // below line is use to add group
        // spacing to our bar chart.
        float groupSpace = 0.5f;

        // we are setting width of
        // bar in below line.
        data.setBarWidth(0.25f);

        // below line is to set minimum
        // axis to our chart.
        barChart.getXAxis().setAxisMinimum(0);

        // below line is to
        // animate our chart.
        barChart.animate();

        // below line is to group bars
        // and add spacing to it.
        barChart.groupBars(0, groupSpace, barSpace);


        // below line is to invalidate
        // our bar chart.
        barChart.invalidate();
    }


    // array list for first set
    private ArrayList<BarEntry> getBarEntriesOne() {

        // creating a new array list
        barEntries = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries.add(new BarEntry(1f, 5));
        barEntries.add(new BarEntry(2f, 5));
        barEntries.add(new BarEntry(3f, 4));
        barEntries.add(new BarEntry(4f, 6));
        return barEntries;
    }


    // array list for second set.
    private ArrayList<BarEntry> getBarEntriesTwo() {

        // creating a new array list
        barEntries = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries.add(new BarEntry(1f, 4));
        barEntries.add(new BarEntry(2f, 3));
        barEntries.add(new BarEntry(3f, 3));
        barEntries.add(new BarEntry(4f, 2));
        return barEntries;
    }
}