package com.fyp.yes2live.Admin.Exercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.auth.login;
import com.fyp.yes2live.response.ExerciseBaseResponse;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;
import com.fyp.yes2live.response.SearchItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseFeature extends AppCompatActivity {

    private String exerciseName;
    private Button btnDelete, btnUpdate;
    private TextView txtViewExerciseName;
    private EditText editTextExerciseMet;
    private String servingFix;
    private long exerciseId =0;

    private APIInterface apiInterface;

    private void usePojo(String exerciseName) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ExerciseBaseResponse> call = apiInterface.getByExerciseName(exerciseName);
        call.enqueue(new Callback<ExerciseBaseResponse>() {
            @Override
            public void onResponse(Call<ExerciseBaseResponse> call, Response<ExerciseBaseResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("SUCCESS")) {

                    SearchExerciseResponse item = response.body().getPayload().get(0);
                    txtViewExerciseName.setText(exerciseName);
                    editTextExerciseMet.setText(String.valueOf(item.getMetValue()));
                    exerciseId = item.getExerciseTrackingId();

                } else {
                    Toast.makeText(ExerciseFeature.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ExerciseBaseResponse> call, Throwable t) {
                Toast.makeText(ExerciseFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        txtViewExerciseName = findViewById(R.id.txtViewExerciseName);
        editTextExerciseMet = findViewById(R.id.editTextExerciseMet);
        btnUpdate = findViewById(R.id.update_id);
        btnDelete = findViewById(R.id.delete_id);
    }

    private void btnDeleteClick() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GeneralResponse> call = apiInterface.deleteExercise(exerciseId);
        //call.enque is used to catch the response of api
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                GeneralResponse exerciseResponse = response.body();
                if (exerciseResponse.getStatus().equals("SUCCESS")) {
                    Intent intent1 = new Intent(ExerciseFeature.this, ExerciseList.class);
                    startActivity(intent1);
                    Toast.makeText(ExerciseFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ExerciseFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(ExerciseFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private TextWatcher servingWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (txtViewExerciseName.getText().toString().isEmpty()) {
                editTextExerciseMet.setText("0");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (txtViewExerciseName.getText().toString() != null || txtViewExerciseName.getText().toString() != "") {
                if (editTextExerciseMet.getText().toString().charAt(0)=='.') {
                    servingFix = "0"+editTextExerciseMet.getText().toString();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_feature);

        Intent intent = getIntent();
        exerciseName = intent.getStringExtra("exerciseName");
        initViews();
        usePojo(exerciseName);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 apiInterface = APIClient.getClient().create(APIInterface.class);
                 SearchExerciseResponse exercise = new SearchExerciseResponse(exerciseId,txtViewExerciseName.getText().toString(),Float.valueOf(editTextExerciseMet.getText().toString()));;
                 Call<GeneralResponse> call = apiInterface.addExercise(exercise);
                 //call.enque is used to catch the response of api
                 call.enqueue(new Callback<GeneralResponse>() {
                     @Override
                     public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                         GeneralResponse exerciseResponse = response.body();
                         if (exerciseResponse.getStatus().equals("SUCCESS")) {
                             Intent intent1 = new Intent(ExerciseFeature.this, ExerciseList.class);
                             startActivity(intent1);
                             Toast.makeText(ExerciseFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                         }else{
                             Toast.makeText(ExerciseFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
                     @Override
                     public void onFailure(Call<GeneralResponse> call, Throwable t) {
                         Toast.makeText(ExerciseFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                     }

                 });
             }
         });

        btnDelete.setOnClickListener(v -> {
                AlertDialog.Builder alertDialogClear = new AlertDialog.Builder(ExerciseFeature.this);
                alertDialogClear.setTitle("Delete Food Item")
                        .setMessage(txtViewExerciseName.getText().toString()+" will be deleted from your database, are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnDeleteClick();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                }).show();
                alertDialogClear.create();
            });

    }
}