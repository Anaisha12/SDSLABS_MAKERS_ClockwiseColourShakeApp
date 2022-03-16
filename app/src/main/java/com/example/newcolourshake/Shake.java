package com.example.newcolourshake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Shake extends AppCompatActivity implements SensorEventListener{
    private TextView x,y,z;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private boolean isAccelerometerSensorAvailable , itIsNotFirstTime=false;
    private float currentx , currenty, currentz , lastx , lasty , lastz , xdiff,ydiff,zdiff;
    private float shakethreshold=5f;
    private Vibrator vibrator;
    private  int a=0;
    RelativeLayout relativeLayout;
    ImageView img1,img2,img3,img4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        x=findViewById(R.id.x);
        y=findViewById(R.id.y);
        z=findViewById(R.id.z);
        img1=findViewById(R.id.i1);
        img2=findViewById(R.id.i2);
        img3=findViewById(R.id.i3);
        img4=findViewById(R.id.i4);
        img1.setBackgroundColor(Color.RED);
        img2.setBackgroundColor(Color.GREEN);
        img3.setBackgroundColor(Color.CYAN);
        img4.setBackgroundColor(Color.YELLOW);
        //vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelerometerSensorAvailable=true;
        }
        else
        {
            x.setText("Accelerometer sensor is not available");
            isAccelerometerSensorAvailable=false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x.setText(sensorEvent.values[0]+"m/s2");
        y.setText(sensorEvent.values[0]+"m/s2");
        z.setText(sensorEvent.values[0]+"m/s2");

        currentx=sensorEvent.values[0];
        currenty=sensorEvent.values[0];
        currentz=sensorEvent.values[0];

        if(itIsNotFirstTime)
        {
            xdiff=Math.abs(currentx-lastx);
            ydiff=Math.abs(currenty-lasty);
            zdiff=Math.abs(currentz-lastz);

            if((xdiff>=shakethreshold && ydiff>=shakethreshold)||(xdiff>=shakethreshold && zdiff>=shakethreshold)||(zdiff>=shakethreshold && ydiff>=shakethreshold))
            {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                {
                    if(a==0)
                        a=1;
                    else if(a==1)
                        a=2;
                    else if(a==2)
                        a=3;
                    else
                        a=0;
                    change();
                }
                else
                {
                }

            }
        }

        lastx=currentx;
        lasty=currenty;
        lastz=currentz;
        itIsNotFirstTime=true;
    }

    private void change()
    {
        if(a==1)
        {
            img1.setBackgroundColor(Color.CYAN);
            img2.setBackgroundColor(Color.RED);
            img3.setBackgroundColor(Color.YELLOW);
            img4.setBackgroundColor(Color.GREEN);
        }
        else if(a==2)
        {
            img1.setBackgroundColor(Color.YELLOW);
            img2.setBackgroundColor(Color.CYAN);
            img3.setBackgroundColor(Color.GREEN);
            img4.setBackgroundColor(Color.RED);
        }
        else if(a==3)
        {
            img1.setBackgroundColor(Color.GREEN);
            img2.setBackgroundColor(Color.YELLOW);
            img3.setBackgroundColor(Color.RED);
            img4.setBackgroundColor(Color.CYAN);
        }
        else
        {
            img1.setBackgroundColor(Color.RED);
            img2.setBackgroundColor(Color.GREEN);
            img3.setBackgroundColor(Color.CYAN);
            img4.setBackgroundColor(Color.YELLOW);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isAccelerometerSensorAvailable==true)
            sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isAccelerometerSensorAvailable)
            sensorManager.unregisterListener(this);
    }
}