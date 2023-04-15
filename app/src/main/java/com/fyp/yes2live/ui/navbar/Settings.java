package com.fyp.yes2live.ui.navbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.R;

import io.getstream.avatarview.AvatarView;

public class Settings extends AppCompatActivity {

    AvatarView avatarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        avatarView = (AvatarView) findViewById(R.id.avatar_imageView);

        avatarView.setAvatarInitials("AA");

    }
}
