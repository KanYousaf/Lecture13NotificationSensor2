package com.example.kanwal.sensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorTest extends AppCompatActivity {
    private TextView acceleration_tv,proximity_tv,display_greater_number_tv;
    private SensorManager mSensorManager;
    private SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        acceleration_tv = (TextView) this.findViewById(R.id.display_acceleration);
        display_greater_number_tv = (TextView) this.findViewById(R.id.display_greater_value);
        proximity_tv = (TextView) this.findViewById(R.id.display_proximity);

        mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                Sensor sensor=sensorEvent.sensor;

                if(sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                    acceleration_tv.setText("X: "+sensorEvent.values[0]
                                            +" Y: "+sensorEvent.values[1]
                                            +" Z: "+sensorEvent.values[2]);
                    if(sensorEvent.values[0] > sensorEvent.values[1] &&
                            sensorEvent.values[0] > sensorEvent.values[2]){
                        display_greater_number_tv.setText(String.valueOf(sensorEvent.values[0]));
                    }else if(sensorEvent.values[1] > sensorEvent.values[0] &&
                            sensorEvent.values[1] > sensorEvent.values[2]){
                        display_greater_number_tv.setText(String.valueOf(sensorEvent.values[1]));
                    } else{
                        display_greater_number_tv.setText(String.valueOf(sensorEvent.values[2]));
                    }
                }else if(sensor.getType()==Sensor.TYPE_PROXIMITY){
                    proximity_tv.setText("The Value "+sensorEvent.values[0]);
                }

                mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), mSensorManager.SENSOR_DELAY_NORMAL);
                mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), mSensorManager.SENSOR_DELAY_NORMAL);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), mSensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), mSensorManager.SENSOR_DELAY_NORMAL);
    }
}
