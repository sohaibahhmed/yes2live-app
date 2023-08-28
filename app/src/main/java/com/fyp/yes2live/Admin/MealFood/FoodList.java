package com.fyp.yes2live.Admin.MealFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.auth.login;
import com.fyp.yes2live.homepage;
import com.fyp.yes2live.model.UserDto;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchItemResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodList extends AppCompatActivity {

    private int mealId;
    private String table;
    private FloatingActionButton addYourOwnFood;
    private MaterialCardView addFoodCardView;
    private MaterialButton addBtnFinal;
    private EditText ETaddFoodFoodName, ETaddFoodCarbs, ETaddFoodProtein, ETaddFoodFats, ETaddFoodSize, servingType;
    private Animation fromBottom, toBottom, rotateClose, rotateOpen;
    private boolean fabIsOpen = false;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    APIInterface apiInterface;

    private void initViews() {

        addFoodCardView = findViewById(R.id.addFoodCardView);

        addYourOwnFood = findViewById(R.id.addYourOwnFood);
        addBtnFinal = findViewById(R.id.addBtnFinal);
        ETaddFoodSize = findViewById(R.id.ETaddFoodSize);
        ETaddFoodFats = findViewById(R.id.ETaddFoodFats);
        ETaddFoodProtein = findViewById(R.id.ETaddFoodProtein);
        ETaddFoodCarbs = findViewById(R.id.ETaddFoodCarbs);
        ETaddFoodFoodName = findViewById(R.id.ETaddFoodFoodName);
        servingType = findViewById(R.id.servingType);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setAddYourOwnFood() {
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);

        if (fabIsOpen) {
            addYourOwnFood.startAnimation(rotateClose);
            addFoodCardView.startAnimation(toBottom);

            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            addFoodCardView.setVisibility(View.GONE);
            ETaddFoodFoodName.setText("");
            ETaddFoodCarbs.setText("");
            ETaddFoodProtein.setText("");
            ETaddFoodFats.setText("");
            ETaddFoodSize.setText("");

            fabIsOpen = false;
        } else {
            addYourOwnFood.startAnimation(rotateOpen);
            addFoodCardView.startAnimation(fromBottom);

            addFoodCardView.setVisibility(View.VISIBLE);

            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);

            fabIsOpen = true;
        }
    }

    private void insertToFoodDB(SearchItemResponse item) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GeneralResponse> call = apiInterface.addMealFood(item);
        //call.enque is used to catch the response of api
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                GeneralResponse itemResponse = response.body();
                if (itemResponse.getStatus().equals("SUCCESS")) {
                    Toast.makeText(FoodList.this, itemResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FoodList.this, itemResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(FoodList.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void addFoodToFoodDB() {
        String foodName = ETaddFoodFoodName.getText().toString().trim();
        double carbs = Double.parseDouble(ETaddFoodCarbs.getText().toString().trim());
        double fats = Double.parseDouble(ETaddFoodFats.getText().toString().trim());
        double protein = Double.parseDouble(ETaddFoodProtein.getText().toString().trim());
        String size = ETaddFoodSize.getText().toString().trim();
        double kcals = 4 * protein + 4 * carbs + 9 * fats;
        String servingType2 = servingType.getText().toString().trim();

        SearchItemResponse item = new SearchItemResponse(foodName,kcals,protein,fats,carbs,Integer.valueOf(size),servingType2);

        insertToFoodDB(item);
    }

    private void setupTabView() {

        FoodListFragmentAdapter adapter = new FoodListFragmentAdapter(this);
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        initViews();
        setupTabView();
//
//        Intent intent = getIntent();
//        mealId = intent.getIntExtra("mealId", 1);
//        table = intent.getStringExtra("tableName");
//
//        setupTabView();
//      + button icon
        addYourOwnFood.setOnClickListener(v -> {
            setAddYourOwnFood();
        });
        //add button
        addBtnFinal.setOnClickListener(v -> {

            Boolean notOkay = ETaddFoodFoodName.getText().toString().trim().equals("")
                    || ETaddFoodCarbs.getText().toString().trim().equals("")
                    || ETaddFoodProtein.getText().toString().trim().equals("")
                    || ETaddFoodFats.getText().toString().trim().equals("")
                    || ETaddFoodSize.getText().toString().trim().equals("");

            if (notOkay) {
                Toast.makeText(FoodList.this, "ERROR! empty field", Toast.LENGTH_LONG).show();
            } else {
                addFoodToFoodDB();
                addYourOwnFood.startAnimation(rotateClose);
                addFoodCardView.startAnimation(toBottom);

                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);

                addFoodCardView.setVisibility(View.GONE);
                ETaddFoodFoodName.setText("");
                ETaddFoodCarbs.setText("");
                ETaddFoodProtein.setText("");
                ETaddFoodFats.setText("");
                ETaddFoodSize.setText("");

                fabIsOpen = false;

                setupTabView();
            }
        });
    }
}