package com.example.et;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        ImageView icon = (ImageView) findViewById(R.id.ic);
        TextView title = (TextView) findViewById(R.id.name);
        TextView moto_1 = (TextView) findViewById(R.id.moto_1);
        TextView moto_2 = (TextView) findViewById(R.id.moto_2);
        TextView moto_3 = (TextView) findViewById(R.id.moto_3);
        TextView moto_4 = (TextView) findViewById(R.id.moto_4);
        TextView moto_5 = (TextView) findViewById(R.id.moto_5);

        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        icon.startAnimation(slideAnimation);
        title.startAnimation(slideAnimation);
        moto_1.startAnimation(slideAnimation);
        moto_2.startAnimation(slideAnimation);
        moto_3.startAnimation(slideAnimation);
        moto_4.startAnimation(slideAnimation);
        moto_5.startAnimation(slideAnimation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2500);
    }


}