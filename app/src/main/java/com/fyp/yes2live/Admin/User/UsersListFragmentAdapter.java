package com.fyp.yes2live.Admin.User;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class UsersListFragmentAdapter extends FragmentStateAdapter {


    public UsersListFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        return new DefaultUsersList();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
