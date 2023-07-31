package com.fyp.yes2live;

import static java.lang.Double.valueOf;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.AddFoodResponse;
import com.fyp.yes2live.response.UpdateFoodIntakeResponse;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodDescription extends AppCompatActivity {
    Double d6,d1;

    APIInterface apiInterface;
    TextView foodname,servings;
    TextView proteins;
    TextView carbs;
    TextView fats;
    TextView calories;
    EditText quantity;
    Button AddFood;
    SharedPreferenceManager sharedPreferenceManager;
    String type;
    private Bundle extras;
    Double c,f,cs,quant,p;
    private int id;
    String date;
    String dateHistory;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_description);
        //getSupportActionBar().hide();

        AddFood=findViewById(R.id.NextButton);
        servings = findViewById(R.id.servings);
        calories = findViewById(R.id.calorie);
        foodname = findViewById(R.id.foodname);
        proteins = findViewById(R.id.protein_cal);
        carbs = findViewById(R.id.carbs_cal);
        fats = findViewById(R.id.fats_cal);
        quantity = findViewById(R.id.quantityno);

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        //appDatabase= Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"infodb").allowMainThreadQueries().build();


        String name=getIntent().getStringExtra("foodName");
        String cal=getIntent().getStringExtra("calories");
        String serve=getIntent().getStringExtra("servings");
        String carb=getIntent().getStringExtra("carbs");
        String fat=getIntent().getStringExtra("fats");
        String protein=getIntent().getStringExtra("proteins");
        String quan=getIntent().getStringExtra("quantity");

        foodname.setText(name);
        servings.setText(getIntent().getStringExtra("servings"));
        calories.setText(getIntent().getStringExtra("calories"));
        carbs.setText(getIntent().getStringExtra("carbs"));
        proteins.setText(getIntent().getStringExtra("proteins"));
        fats.setText(getIntent().getStringExtra("fats"));
        quantity.setText(getIntent().getStringExtra("quantity"));

        //if user doesn't change default quantity
        Log.d("Magic", "Quantity1: " + quantity.getText().toString());
        Log.d("Magic", "Quantity2: " + getIntent().getStringExtra("quantity"));

        if(quantity.getText().toString().equals(getIntent().getStringExtra("quantity")))
        {
            c= valueOf(cal);
            p= valueOf(protein);
            quant= valueOf(quan.toString());
            f= valueOf(fat);
            cs= valueOf(carb);
        }

        dateHistory= sharedPreferenceManager.getDate();
        Log.d("Magic", "date History: "+dateHistory);

         if(dateHistory.equals(LocalDate.now())) {
           date= String.valueOf(LocalDate.now());
       }
         else{
        date= (dateHistory);
         }


        AddFood.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Long userId = sharedPreferenceManager.getUser().getId();
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //String currentDateandTime = sdf.format(new Date());
                int food_log_id = getIntent().getIntExtra("user_log_id", 1);

                String getcalorie = getIntent().getStringExtra("calories");
                long intake_id = getIntent().getLongExtra("intakeId", 1);

                //Check which type is selected by user
                if (Track.Type == 1) {
                    type = "breakfast";
                } else if (Track.Type == 2) {
                    type = "lunch";
                } else if (Track.Type == 3) {
                    type = "morning snacks";
                } else if (Track.Type == 4) {
                    type = "evening snacks";
                } else if (Track.Type == 5) {
                    type = "dinner";
                } else if (Track.Type == 6) {
                    type = "exercise";
                }

                //Display Save food in Calorie Counter Activity
                Intent intent1 = new Intent();
                intent1.putExtra("calorie", c);
                intent1.putExtra("carb", cs);
                intent1.putExtra("protein", p);
                intent1.putExtra("serving", serve);
                intent1.putExtra("foodname", name);
                intent1.putExtra("quantity", quant);
                intent1.putExtra("date", date);
                intent1.putExtra("type", type);

                //Saved food nutrients value into the Database
                Log.d("Magic", "Add Food nutrients into Database: -----------------");
                Log.d("Magic", "Date: " + date);
                Log.d("Magic", "user_id: " + userId);
                Log.d("Magic", "Fats: " + f);
                Log.d("Magic", "Proteins: " + p);
                Log.d("Magic", "Carbs: " + cs);
                Log.d("Magic", "Calories: " + c);
                Log.d("Magic", "Quantity: " + quant);
                Log.d("Magic", "log_food_item_id: " + food_log_id);
                Log.d("Magic", "FoodName: " + name);
                Log.d("Magic", "Type: " + type);
                Log.d("Magic", "intakeId: " + intake_id);
                Log.d("Magic", "dateHistory: " + date);

                String  userLogId = String.valueOf(getIntent().getIntExtra("user_log_id", 1));
                Log.d("Magic", "user_log_id: " + userLogId);


                if (quantity.getText().toString().isEmpty()) {
                    Toast.makeText(FoodDescription.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }
                int intake=getIntent().getIntExtra("intake",1);
                Log.d("magic", "onClick: "+intake);
                if(intake==2){

                    //Api->saveMe(Food)
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<AddFoodResponse> call = apiInterface.saveMeal(userId, Date.valueOf(date), food_log_id, type,quant);;
                    call.enqueue(new Callback<AddFoodResponse>() {
                        @Override
                        public void onResponse(Call<AddFoodResponse> call, Response<AddFoodResponse> response) {
                            AddFoodResponse addFoodResponse = response.body();
                            if (response.isSuccessful()) {

                                if (addFoodResponse.getStatus().equals("Success")) {
                                  //  Toast.makeText(FoodDescription.this, addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FoodDescription.this, Track.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                if (addFoodResponse.getStatus().equals("Fail!")) {
                                    // Toast.makeText(FoodDescription.this, addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    //  Toast.makeText(FoodDescription.this, addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddFoodResponse> call, Throwable t) {
                           // Toast.makeText(FoodDescription.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                int update=getIntent().getIntExtra("update",1);
                Log.d("magic", "update: "+update);

                if(update==3){

                    foodname.setText(name);
                    //Api->saveMe(Food)
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<UpdateFoodIntakeResponse> call = apiInterface.updateFoodIntake(userId, date, intake_id, quant);
                    call.enqueue(new Callback<UpdateFoodIntakeResponse>() {
                        @Override
                        public void onResponse(Call<UpdateFoodIntakeResponse> call, Response<UpdateFoodIntakeResponse> response) {
                            UpdateFoodIntakeResponse updateFoodIntakeResponse = response.body();
                            if (response.isSuccessful()) {

                                if (updateFoodIntakeResponse.getStatus().equals("Success")) {
                                  //  Toast.makeText(FoodDescription.this, updateFoodIntakeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FoodDescription.this, Track.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(FoodDescription.this, updateFoodIntakeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                              //  Toast.makeText(FoodDescription.this, updateFoodIntakeResponse.getStatus().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateFoodIntakeResponse> call, Throwable t) {
                           // Toast.makeText(FoodDescription.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }


        });


        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if(TextUtils.isEmpty(s.toString())){
                    Double a1=0.0;
                    Double a2=0.0;
                    Double a3=0.0;
                    Double a4=0.0;

                    calories.setText(a1.toString());
                    fats.setText(a2.toString());
                    proteins.setText(a3.toString());
                    carbs.setText(a4.toString());
                    return ;

                }
                else {
                    d1 = valueOf((s.toString()));
                    Double d2 = Double.parseDouble(cal);
                    Double d3 = Double.parseDouble(protein);
                    Double d4 = Double.parseDouble(fat);
                    Double d5 = Double.parseDouble(carb);

                    d6 = d1*d2;
                    Double d7 = d1 * d3;
                    Double d8 = d1 * d4;
                    Double d9 = d1 * d5;

                    calories.setText(String.format("%.2f",d6));
                    proteins.setText(String.format("%.2f",d7));
                    fats.setText(String.format("%.2f",d8));
                    carbs.setText(String.format("%.2f",d9));
                    c=d6;
                    f=d8;
                    cs=d9;
                    p=d7;
                    quant=d1;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}

