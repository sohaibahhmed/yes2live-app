package com.fyp.yes2live.Admin.MealFood;

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

import com.fyp.yes2live.Admin.Exercise.ExerciseFeature;
import com.fyp.yes2live.Admin.Exercise.ExerciseList;
import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;
import com.fyp.yes2live.response.SearchItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFeature extends AppCompatActivity {

    private String foodName;
    private Button btnDelete, btnUpdate;
    private double protein, carbs, fats, kcals, valueRound, finalProtein, finalCarbs, finalFats, finalKcals;
    private TextView txtViewFoodName;
    private EditText editTextFoodServing,txtViewProtein,txtViewCarbs,txtViewFats,txtViewKcals,txtSevingType;
    private int mealId, category;
    private String servingFix;
    private double serving;
    private long foodId;

    private APIInterface apiInterface;

    private void usePojo(String foodName) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SearchItemBaseResponse> call = apiInterface.getItemName(foodName);
        call.enqueue(new Callback<SearchItemBaseResponse>() {
            @Override
            public void onResponse(Call<SearchItemBaseResponse> call, Response<SearchItemBaseResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("SUCCESS")) {

                    SearchItemResponse item = response.body().getPayload().get(0);
                    txtViewFoodName.setText(foodName);
                    txtViewCarbs.setText(""+item.getCarbs());
                    txtViewProtein.setText(""+item.getProtein());
                    txtViewFats.setText(""+item.getFat());
                    txtViewKcals.setText(""+item.getCalories());
                    txtSevingType.setText(""+item.getServing_type());
                    editTextFoodServing.setText(String.valueOf(item.getQuantity()));
                    foodId = item.getLog_food_items_id();
                } else {
                    Toast.makeText(FoodFeature.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchItemBaseResponse> call, Throwable t) {
                Toast.makeText(FoodFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

//        protein = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("protein"));
//        fats = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("fats"));
//        carbs = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("carbs"));
//        kcals = foodFeatureCursor.getDouble(foodFeatureCursor.getColumnIndex("kcals"));
    }

    private void initViews() {
        txtViewFoodName = findViewById(R.id.txtViewFoodName);
        txtViewProtein = findViewById(R.id.txtViewProtein);
        txtViewCarbs = findViewById(R.id.txtViewCarbs);
        txtViewFats = findViewById(R.id.txtViewFats);
        txtViewKcals = findViewById(R.id.txtViewKcals);
        txtSevingType = findViewById(R.id.txtServType);
        editTextFoodServing = findViewById(R.id.editTextFoodServing);
        btnUpdate = findViewById(R.id.update_id);
        btnDelete = findViewById(R.id.delete_id);
    }

    private void calculateMacros(TextView txtView, double initMacro) {

        if (editTextFoodServing.getText().toString().trim().length()>0) {


            if (editTextFoodServing.getText().toString().trim().charAt(0)=='.') {
                serving = Double.parseDouble(servingFix);
            }
            else {
                serving = Double.parseDouble(editTextFoodServing.getText().toString().trim());
            }

            double value = serving*initMacro;
            valueRound = Math.floor(value*10)/10;
            txtView.setText(String.valueOf(valueRound));

            if (initMacro==protein) {
                finalProtein=valueRound;
            }
            else if (initMacro==carbs) {
                finalCarbs=valueRound;
            }
            else if (initMacro==fats) {
                finalFats=valueRound;
            }
            else if (initMacro==kcals) {
                finalKcals=valueRound;
            }
        }
    }

    private void btnAddClick(String tableName) {

//        if (editTextFoodServing.getText().toString().equals("1")) {
//            db.open();
//            db.insert(tableName,
//                      "_id, mealId, food, kcals, protein, carbs, fats",
//                      "NULL, "+mealId+", '"+foodName+"', "+kcals+", "+protein+","+carbs+", "+fats+"");
//        }
//        else {
//            db.open();
//            db.insert(tableName,
//                      "_id, mealId, food, kcals, protein, carbs, fats",
//                      "NULL, "+mealId+", '"+foodName+"', "+finalKcals+", "+finalProtein+", "+finalCarbs+", "+finalFats+"");
//        }
//        db.close();
//        Toast.makeText(this, foodName+" added", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(FoodFeature.this, MainActivity.class);
//        intent.putExtra("code", 2);
//        intent.putExtra("tableName", tableName);
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finishAffinity();
    }

    private void btnDeleteClick() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GeneralResponse> call = apiInterface.deleteMealFood(foodId);
        //call.enque is used to catch the response of api
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                GeneralResponse exerciseResponse = response.body();
                if (exerciseResponse.getStatus().equals("SUCCESS")) {
                    Intent intent1 = new Intent(FoodFeature.this, FoodList.class);
                    startActivity(intent1);
                    Toast.makeText(FoodFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FoodFeature.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(FoodFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private TextWatcher servingWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (editTextFoodServing.getText().toString().isEmpty()) {
                txtViewKcals.setText("0");
                txtViewProtein.setText("0");
                txtViewFats.setText("0");
                txtViewCarbs.setText("0");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (editTextFoodServing.getText().toString().trim().length()>0) {
                if (editTextFoodServing.getText().toString().charAt(0)=='.') {
                    servingFix = "0"+editTextFoodServing.getText().toString();
                }
            }

            calculateMacros(txtViewCarbs, carbs);
            calculateMacros(txtViewFats, fats);
            calculateMacros(txtViewProtein, protein);
            calculateMacros(txtViewKcals, kcals);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_feature);

        Intent intent = getIntent();
        foodName = intent.getStringExtra("foodName");
        initViews();

        usePojo(foodName);//function in which the whole record based on item name is obtained and is set on screen

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = APIClient.getClient().create(APIInterface.class);

                String foodName = txtViewFoodName.getText().toString().trim();
                double carbs = Double.parseDouble(txtViewCarbs.getText().toString().trim());
                double fats = Double.parseDouble(txtViewFats.getText().toString().trim());
                double protein = Double.parseDouble(txtViewProtein.getText().toString().trim());
                String size = editTextFoodServing.getText().toString().trim();
                double kcals = 4 * protein + 4 * carbs + 9 * fats;
                String servingType2 = txtSevingType.getText().toString().trim();

                SearchItemResponse item = new SearchItemResponse(foodId,foodName,kcals,protein,fats,carbs,Integer.valueOf(size),servingType2);
                Call<GeneralResponse> call = apiInterface.addMealFood(item);
                //call.enque is used to catch the response of api
                call.enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        GeneralResponse itemResponse = response.body();
                        if (itemResponse.getStatus().equals("SUCCESS")) {
                            Intent intent1 = new Intent(FoodFeature.this, FoodList.class);
                            startActivity(intent1);
                            Toast.makeText(FoodFeature.this, itemResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(FoodFeature.this, itemResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(FoodFeature.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogClear = new AlertDialog.Builder(FoodFeature.this);
            alertDialogClear.setTitle("Delete Food Item")
                    .setMessage(foodName+" will be deleted from your database, are you sure?")
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