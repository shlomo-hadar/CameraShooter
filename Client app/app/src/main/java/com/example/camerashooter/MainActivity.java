package com.example.camerashooter;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import shop_fragments.ShopActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Sound sound;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new Sound(this, R.raw.bcs);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        sound.playSound();

        switch (v.getId()){
            case R.id.join_game:
                startActivity(new Intent(getApplicationContext(), ConnectionActivity.class));
                break;

            case R.id.shop:
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {}
}
