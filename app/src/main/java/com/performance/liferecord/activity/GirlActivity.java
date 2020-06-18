package com.performance.liferecord.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.performance.liferecord.databinding.ActivityGirlBinding;
import com.performance.liferecord.model.GankData;
import com.squareup.picasso.Picasso;

public class GirlActivity extends AppCompatActivity {

    String imageUrl;
    private ActivityGirlBinding mActivityGirlBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityGirlBinding = ActivityGirlBinding.inflate(getLayoutInflater());
        setContentView(mActivityGirlBinding.getRoot());
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(GankData.IMAGE_URL);
        Picasso.with(this).load(imageUrl).into(mActivityGirlBinding.girlImage);
    }
}
