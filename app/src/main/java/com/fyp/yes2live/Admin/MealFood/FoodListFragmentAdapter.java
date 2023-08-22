package com.fyp.yes2live.Admin.MealFood;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FoodListFragmentAdapter extends FragmentStateAdapter {


    public FoodListFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        return new DefaultFoodList();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
