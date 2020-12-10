package com.example.sensorsapplication.SensorsFragments;

import android.app.Application;
import android.content.Context;
import android.database.DataSetObserver;
import android.hardware.Sensor;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.sensorsapplication.ObjectClasses.SensorClass;
import com.example.sensorsapplication.R;

import java.util.List;

public class CustomAdapter implements ListAdapter {

    List<Sensor> sensorList;
    Context context;
    OnSelectedListener callback;

    public CustomAdapter(List<Sensor> sensorList, Context context, OnSelectedListener callback) {
        this.sensorList = sensorList;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return sensorList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        SensorClass sensorClassObject = getSensorObject(sensorList.get(position));
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_row_sensor, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("ListView", sensorClassObject.getName());
                    if (sensorClassObject.getName().contains("Accelerometer"))
                        callback.Proceedselction(1);
                }

            });
            TextView sensorName = convertView.findViewById(R.id.sensor_name);
            TextView sensorVender = convertView.findViewById(R.id.sensor_vender);
            TextView sensorMaxDelay = convertView.findViewById(R.id.sensor_maxdelay);
            TextView sensorMaxRange = convertView.findViewById(R.id.sensor_maxrange);
            TextView sensorPower = convertView.findViewById(R.id.sensor_power);
            TextView sensorResolution = convertView.findViewById(R.id.sensor_resolution);
            TextView sensorVersion = convertView.findViewById(R.id.sensor_version);

            sensorName.setText(sensorClassObject.getName());
            sensorVender.setText(sensorClassObject.getVender());
            sensorMaxDelay.setText(sensorClassObject.getDelay());
            sensorMaxRange.setText(sensorClassObject.getMaximumRange());
            sensorPower.setText(sensorClassObject.getPower());
            sensorResolution.setText(sensorClassObject.getResolution());
            sensorVersion.setText(sensorClassObject.getVersion());


        }
        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return sensorList.size();
    }

    @Override
    public boolean isEmpty() {
        return sensorList.size() > 0;
    }


    private SensorClass getSensorObject(Sensor sensor) {
        return new SensorClass(sensor.getName(), sensor.getVendor(), sensor.getMaxDelay(), sensor.getMaximumRange() + "", sensor.getPower() + "", sensor.getResolution() + "", sensor.getVersion() + "");

    }
}
