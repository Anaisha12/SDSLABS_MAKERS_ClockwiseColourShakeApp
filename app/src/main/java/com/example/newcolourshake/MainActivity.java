package com.example.newcolourshake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView te1,te2,te3;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        te1 = findViewById(R.id.t1);
        te2 = findViewById(R.id.t2);
        te3 = findViewById(R.id.t3);
        Animation t,b,c;
        relativeLayout = findViewById(R.id.rl);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        t= AnimationUtils.loadAnimation(getApplication(),R.anim.logianim);
        b=AnimationUtils.loadAnimation(getApplication(),R.anim.txtanim1);
        c=AnimationUtils.loadAnimation(getApplication(),R.anim.txtanim2);
        te1.setAnimation(t);
        te2.setAnimation(b);
        te3.setAnimation(c);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Shake.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}