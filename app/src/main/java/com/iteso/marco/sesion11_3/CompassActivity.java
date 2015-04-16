package com.iteso.marco.sesion11_3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by marco on 4/15/15.
 */
public class CompassActivity extends ActionBarActivity
{
    private static SensorManager sensorService;
    private MyCompassView compassView;
    private Sensor sensor;
    private SensorEventListener mySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float azimuth = event.values[0];
            compassView.updateData(azimuth);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        compassView = new MyCompassView(this);
        setContentView(compassView);

        sensorService = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if(sensor != null)
        {
            sensorService.registerListener(mySensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.i("Compass Main Activity", "Registered for ORIENTATION sensor");
        }
        else
        {
            Log.e("Compass Main Activity", "Fail to register for ORIENTATION sensor");
            Toast.makeText(this, "ORIENTATION sensor not found", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensor != null)
        {
            sensorService.unregisterListener(mySensorEventListener);
        }
    }
}
