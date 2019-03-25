package com.example.loginprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    ImageButton mImageButton;
    Button mChatRoomButton, mToolbarButton, mWeatherButton,mLab8;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mImageButton = findViewById(R.id.imageButton1);
        mChatRoomButton = findViewById(R.id.button1);
        mToolbarButton = findViewById(R.id.button2);
        mWeatherButton = findViewById(R.id.button3);
        mLab8 = findViewById(R.id.button4);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });

        mChatRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatRoomIntent = new Intent (ProfileActivity.this, ChatRoomActivity.class);
                ProfileActivity.this.startActivity(chatRoomIntent);

            }
        });

        mToolbarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toolbarIntent = new Intent(ProfileActivity.this, TestToolbar.class);
                ProfileActivity.this.startActivity(toolbarIntent);
            }
        });

        mWeatherButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toolbarIntent = new Intent(ProfileActivity.this, WeatherForecast.class);
                ProfileActivity.this.startActivity(toolbarIntent);
            }
        });
        mLab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab8Intent = new Intent(ProfileActivity.this, ChatRoomActivity1.class);
                ProfileActivity.this.startActivity(lab8Intent);
            }
        });



     }

    static final int REQUEST_IMAGE_CAPTURE = 1;

   private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

}