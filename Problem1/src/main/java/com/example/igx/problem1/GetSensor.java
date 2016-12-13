package com.example.igx.problem1;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by 1a1a1 on 2016-12-13.
 */

    import android.app.Activity;
    import android.content.Context;
    import android.hardware.Sensor;
    import android.hardware.SensorManager;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.hardware.SensorEventListener;
    import android.hardware.SensorEvent;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;


public class GetSensor extends Activity {

    public double gx=3.3333;
    public double gy=3.112;
    public double gz=3.1234;

    public double ax=1.123123;
    public double ay=1.12434;
    public double az=1.12421;

    public double lx=2.123124;
    public double ly=2.124;
    public double lz=2.1243;

    public double GYx=1.523;
    public double GYy=1.15332;
    public double GYz=1.1235;



    public SensorManager mSM;

    public Sensor myGravity;
    public Sensor myAccel;
    public Sensor myLinear;
    public Sensor myGyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mSM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        myGravity = mSM.getDefaultSensor(Sensor.TYPE_GRAVITY);
        myAccel= mSM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        myLinear=mSM.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        myGyro=mSM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSM.registerListener(mySensorListener, myGravity,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSM.registerListener(mySensorListener, myAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSM.registerListener(mySensorListener, myLinear,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSM.registerListener(mySensorListener, myGyro,
                SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    protected void onStart() {
        super.onStart();

        mSM.registerListener(mySensorListener, myGravity,
                SensorManager.SENSOR_DELAY_GAME);

        mSM.registerListener(mySensorListener, myAccel,
                SensorManager.SENSOR_DELAY_GAME);

        mSM.registerListener(mySensorListener, myLinear,
                SensorManager.SENSOR_DELAY_GAME);
        mSM.registerListener(mySensorListener, myGyro,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSM.unregisterListener(mySensorListener);
    }

    public SensorEventListener mySensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

            switch(event.sensor.getType()){
                case Sensor.TYPE_GRAVITY:
                    gx=event.values[0];
                    gy=event.values[1];
                    gz=event.values[2];
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    ax=event.values[0];
                    ay=event.values[1];
                    az=event.values[2];
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    lx=event.values[0];
                    ly=event.values[1];
                    lz=event.values[2];
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    GYx=event.values[0];
                    GYy=event.values[1];
                    GYz=event.values[2];
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

}

