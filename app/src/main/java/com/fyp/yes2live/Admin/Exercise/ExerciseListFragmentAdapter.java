package com.fyp.yes2live.Admin.Exercise;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ExerciseListFragmentAdapter extends FragmentStateAdapter {


    public ExerciseListFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        return new DefaultExerciseList();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
