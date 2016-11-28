package com.example.kanwal.sensortest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int color=Color.RED;
    private static final int light_color=Color.argb(255, 255,255, 0);
   @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent open_sensor_act=new Intent(MainActivity.this, SensorTest.class);

       if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
           Notification.Builder notification_builder=new Notification.Builder(this)
                   .setSmallIcon(R.drawable.ic_stat_smiley_transparent)
                   .setColor(color)
                   .setContentTitle("Welcome to Notification")
                   .setContentText("Let's go to Sensor Act")
                   .setLights(light_color, 2000, 3000);
           PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, open_sensor_act, 0);
           notification_builder.setContentIntent(pendingIntent);
           Notification notification=notification_builder.build();
           NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
           notificationManager.notify(99, notification);
       }else{
           Notification.Builder notification_builder=new Notification.Builder(this)
                   .setSmallIcon(R.drawable.ic_stat_smiley_transparent)
                   .setContentTitle("Welcome to Notification")
                   .setContentText("Let's go to Sensor Act")
                   .setLights(light_color, 2000, 3000);
           PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, open_sensor_act, 0);
           notification_builder.setContentIntent(pendingIntent);
           Notification notification=notification_builder.build();
           NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
           notificationManager.notify(99, notification);
       }

   }
}
