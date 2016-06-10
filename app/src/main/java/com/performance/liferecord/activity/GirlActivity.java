package com.performance.liferecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;
import com.squareup.picasso.Picasso;

public class GirlActivity extends AppCompatActivity {

    String imageUrl;
    private ImageView girlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        girlImage = (ImageView) findViewById(R.id.girlImage);

        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(GankData.IMAGE_URL);
        Picasso.with(this).load(imageUrl).into(girlImage);

    }
}
