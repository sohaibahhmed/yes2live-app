package com.fyp.yes2live.Admin.User;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.GeneralResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersList extends AppCompatActivity {
    private ViewPager2 viewPager;

    APIInterface apiInterface;

    private void initViews() {
        viewPager = findViewById(R.id.viewPager2);
    }



    private void setupTabView() {

        UsersListFragmentAdapter adapter = new UsersListFragmentAdapter(this);
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        initViews();
        setupTabView();
    }
}