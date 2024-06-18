package com.example.healthcareapplicationfinalproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;

public class SensorPage implements SensorEventListener {

    private MediaPlayer mediaPlayer;
    private boolean isRunning = false;
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private Context context;

    public SensorPage(Context context) {
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    public void registerSensor() {
        if (temperatureSensor != null) {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unregisterSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float threshold = 30;
        float temperatureValue = event.values[0];
        if (temperatureValue > threshold && !isRunning) {
            isRunning = true;
            mediaPlayer = MediaPlayer.create(context, R.raw.sensorsound);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    isRunning = false;
                    mediaPlayer.release();
                }
            });
            mediaPlayer.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }
}