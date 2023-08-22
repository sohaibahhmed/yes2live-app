package com.fyp.yes2live.Admin.Exercise;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fyp.yes2live.Admin.MealFood.FoodList;
import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseList extends AppCompatActivity {

    private FloatingActionButton addYourOwnExercise;
    private MaterialCardView addExerciseCardView;
    private MaterialButton addBtnFinal;
    private EditText ETaddExerciseName,ETaddExerciseSize;
    private Animation fromBottom, toBottom, rotateClose, rotateOpen;
    private boolean fabIsOpen = false;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    APIInterface apiInterface;

    private void initViews() {

        addExerciseCardView = findViewById(R.id.addExerciseCardView);

        addYourOwnExercise = findViewById(R.id.addYourOwnExercise);
        addBtnFinal = findViewById(R.id.addBtnFinal2);
        ETaddExerciseSize = findViewById(R.id.ETaddExerciseMet);
        ETaddExerciseName = findViewById(R.id.ETaddExerciseName);

        tabLayout = findViewById(R.id.tabLayout2);
        viewPager = findViewById(R.id.viewPager2);
    }

    private void setAddYourOwnFood() {
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);

        if (fabIsOpen) {
            addYourOwnExercise.startAnimation(rotateClose);
            addExerciseCardView.startAnimation(toBottom);

            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            addExerciseCardView.setVisibility(View.GONE);
            ETaddExerciseName.setText("");
            ETaddExerciseSize.setText("");

            fabIsOpen = false;
        } else {
            addYourOwnExercise.startAnimation(rotateOpen);
            addExerciseCardView.startAnimation(fromBottom);

            addExerciseCardView.setVisibility(View.VISIBLE);

            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);

            fabIsOpen = true;
        }
    }

    private void insertToFoodDB(SearchExerciseResponse exercise) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GeneralResponse> call = apiInterface.addExercise(exercise);
        //call.enque is used to catch the response of api
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                GeneralResponse exerciseResponse = response.body();
                if (exerciseResponse.getStatus().equals("SUCCESS")) {
                    Toast.makeText(ExerciseList.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ExerciseList.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(ExerciseList.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void addExerciseToExerciseDB() {
        String foodName = ETaddExerciseName.getText().toString().trim();
        String size = ETaddExerciseSize.getText().toString().trim();

        SearchExerciseResponse exercise = new SearchExerciseResponse(foodName,Float.valueOf(size));;
        insertToFoodDB(exercise);
    }

    private void setupTabView() {

        ExerciseListFragmentAdapter adapter = new ExerciseListFragmentAdapter(this);
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        initViews();
        setupTabView();
//
//        Intent intent = getIntent();
//        mealId = intent.getIntExtra("mealId", 1);
//        table = intent.getStringExtra("tableName");
//
//        setupTabView();
//
        addYourOwnExercise.setOnClickListener(v -> {
            setAddYourOwnFood();
        });

        addBtnFinal.setOnClickListener(v -> {

            Boolean notOkay = ETaddExerciseName.getText().toString().trim().equals("")
                    || ETaddExerciseSize.getText().toString().trim().equals("");

            if (notOkay) {
                Toast.makeText(ExerciseList.this, "ERROR! empty field", Toast.LENGTH_LONG).show();
            } else {
                addExerciseToExerciseDB();
                Toast.makeText(ExerciseList.this, "" + ETaddExerciseName.getText().toString() + " added to the list", Toast.LENGTH_SHORT).show();

                addYourOwnExercise.startAnimation(rotateClose);
                addExerciseCardView.startAnimation(toBottom);

                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);

                addExerciseCardView.setVisibility(View.GONE);
                ETaddExerciseName.setText("");
                ETaddExerciseSize.setText("");

                fabIsOpen = false;

                setupTabView();
            }
        });
    }
}