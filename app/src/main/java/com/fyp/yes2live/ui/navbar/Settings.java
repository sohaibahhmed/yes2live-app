package com.fyp.yes2live.ui.navbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.yes2live.Admin.Daashboard;
import com.fyp.yes2live.MainActivity;
import com.fyp.yes2live.R;
import com.fyp.yes2live.homepage;

import io.getstream.avatarview.AvatarView;

public class Settings extends AppCompatActivity {

    AvatarView avatarView;
    ImageButton previousBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        avatarView = (AvatarView) findViewById(R.id.avatar_imageView);

        avatarView.setAvatarInitials("AA");
        previousBtn= findViewById(R.id.PreviousButton);

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Settings.this, homepage.class);
                startActivity(intent);
            }
        });

    }
}
