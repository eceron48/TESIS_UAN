package com.example.monitor_ua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.hardware.Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_MOTION_DETECT;
import static android.hardware.SensorManager.SENSOR_STATUS_ACCURACY_HIGH;
import static android.os.BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    private TextView wifi;
    private TextView acelerometro;
    private TextView giroscopio;
    private TextView luminosidad;
    private TextView proximidad;
    private TextView salida;
    private BatteryReceiver mbattery=new BatteryReceiver();
    private IntentFilter filtro= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mbattery,filtro);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        wifi = (TextView) findViewById(R.id.txt_gps);
        acelerometro = (TextView) findViewById(R.id.txt_acelerometro);
        giroscopio = (TextView) findViewById(R.id.txt_giroscopio);
        luminosidad = (TextView) findViewById(R.id.txt_luminosidad);
        proximidad = (TextView) findViewById(R.id.txt_proximidad);
        salida = (TextView) findViewById(R.id.txt_salida);



        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.
                getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : listaSensores) {

            log(sensor.getName()+" consumo "+sensor.getPower());


        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Toast.makeText(this, "acelerometro",Toast.LENGTH_SHORT).show();
            Sensor acelerometro = listaSensores.get(0);

            sensorManager.registerListener(this, acelerometro,
                    SENSOR_STATUS_ACCURACY_HIGH);}


        listaSensores = sensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if (!listaSensores.isEmpty()) {
            Toast.makeText(this, "luz",Toast.LENGTH_SHORT).show();
            Sensor luminosidad = listaSensores.get(0);

            sensorManager.registerListener(this, luminosidad,
                    SENSOR_STATUS_ACCURACY_HIGH);}
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        if (!listaSensores.isEmpty()) {

            Sensor proximidad = listaSensores.get(0);
            Toast.makeText(this, "proximidad",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, proximidad,
                    SENSOR_STATUS_ACCURACY_HIGH);}





        listaSensores = sensorManager.getSensorList(TYPE_GYROSCOPE);

        if (!listaSensores.isEmpty()) {
            Toast.makeText(this, "giroscopio",Toast.LENGTH_SHORT).show();
            Sensor giroscopio = listaSensores.get(0);

            sensorManager.registerListener(this, giroscopio,
                    SENSOR_STATUS_ACCURACY_HIGH);}


        listaSensores = sensorManager.getSensorList(TYPE_GEOMAGNETIC_ROTATION_VECTOR);

        if (!listaSensores.isEmpty()) {
            Toast.makeText(this, "vector",Toast.LENGTH_SHORT).show();
            Sensor vector = listaSensores.get(0);

            sensorManager.registerListener(this, vector,
                    SENSOR_STATUS_ACCURACY_HIGH);}

        listaSensores = sensorManager.getSensorList(TYPE_MOTION_DETECT);

        if (!listaSensores.isEmpty()) {
            Toast.makeText(this, "motion",Toast.LENGTH_SHORT).show();
            Sensor motion = listaSensores.get(0);

            sensorManager.registerListener(this, motion,
                    SENSOR_STATUS_ACCURACY_HIGH);}



    }


    private void log(String string) {
        salida.append(string + "\n");
    }





    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {

            switch(event.sensor.getType()) {

                case Sensor.TYPE_ACCELEROMETER:

                    String acel=String.valueOf(event.sensor.getPower());
                    acelerometro.setText(acel+" mA");

                    break;

                case Sensor.TYPE_GYROSCOPE:


                    String giro;
                    giro=String.valueOf(event.sensor.getPower());
                    giroscopio.setText(giro+" mA");


                    break;



                case Sensor.TYPE_LIGHT:

                    String lumi;
                    lumi=String.valueOf(event.sensor.getPower());
                    luminosidad.setText(lumi+" mA");
                    break;

                case Sensor.TYPE_PROXIMITY:
                    String proxi;
                    proxi=String.valueOf(event.sensor.getPower());
                    proximidad.setText(proxi+" mA");
                    break;

                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    String vector;
                    vector=String.valueOf(event.sensor.getPower());
                    wifi.setText(vector+" mA");
                    break;

                default:

                    Toast.makeText(
                            this, "sin datos de sensores",Toast.LENGTH_SHORT
                    ).show();

            }

        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}