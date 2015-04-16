package com.iteso.marco.sesion11_3;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marco on 4/15/15.
 */
public class SensorsActivity extends ActionBarActivity
{
    private TextView sensorsData;
    private SensorManager sMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorsData = (TextView)findViewById(R.id.textView1);
        sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sb = new StringBuilder();

        for(Sensor s : list)
        {
            sb.append(s.getName() + "\n");
            sb.append(s.getVendor() + "\n");
            sb.append(s.getVersion() + "\n");
        }

        sensorsData.setText(sb.toString());
    }
}
