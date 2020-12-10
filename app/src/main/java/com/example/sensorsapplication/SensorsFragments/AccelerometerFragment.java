package com.example.sensorsapplication.SensorsFragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sensorsapplication.ObjectClasses.AccelerometerData;
import com.example.sensorsapplication.OperationalClasses.ConfirmationDialogAndSaveData;
import com.example.sensorsapplication.OperationalClasses.SystemDateTime;
import com.example.sensorsapplication.OperationalClasses.TimerForDataCollection;
import com.example.sensorsapplication.R;

import java.util.LinkedList;
import java.util.List;


public class AccelerometerFragment extends Fragment implements SensorEventListener {

    //set instances for the sensorManager, accelerometer, and textViews
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TimerForDataCollection timerForDataCollection;


    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    private TextView name;

    Button btnStart, btnStop, btnPause;
    private boolean firstLineCSV = true;
    List<AccelerometerData> accelerometerDataList = new LinkedList<>();


    public AccelerometerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accelerometer, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupOnClickListener();
    }


    private void setupViews(View view) {
        //retrieve widgets
        xValue = (TextView) view.findViewById(R.id.xValue);
        yValue = (TextView) view.findViewById(R.id.yValue);
        zValue = (TextView) view.findViewById(R.id.zValue);

        // Buttons
        btnStart = (Button) view.findViewById(R.id.btn_start);
        btnPause = (Button) view.findViewById(R.id.btn_pause);
        btnStop = (Button) view.findViewById(R.id.btn_stop);
        name = (TextView) view.findViewById(R.id.title);

        btnStop.setVisibility(View.GONE);
        btnPause.setVisibility(View.GONE);


        //define instances
        defineInstances(view);
        //TextViews
        name.setText(accelerometer.getName());
    }

    private void defineInstances(View view) {
        //define instances
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        timerForDataCollection = new TimerForDataCollection(getContext(), view);

        accelerometerDataList = new LinkedList<>();


    }

    private void setupOnClickListener() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDataCollection();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseDataCollection();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopDataCollection();
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //get the current values of the accelerometer for each axis
        float current_xValue = sensorEvent.values[0];
        float current_yValue = sensorEvent.values[1];
        float current_zValue = sensorEvent.values[2];

        //display the current values of the  accelerometer for each axis onto the
        //textView widgets
        xValue.setText(getResources().getString(R.string.accelerometer_x_value, current_xValue));
        yValue.setText(getResources().getString(R.string.accelerometer_y_value, current_yValue));
        zValue.setText(getResources().getString(R.string.accelerometer_z_value, current_zValue));

        saveUpdatedData(current_xValue, current_yValue, current_zValue);
    }

    private void saveUpdatedData(float current_xValue, float current_yValue, float current_zValue) {
        if (firstLineCSV) {
            firstLineCSV = false;
            accelerometerDataList.add(new AccelerometerData(SystemDateTime.geTime(), timerForDataCollection.getStopWatch(), accelerometer.getName(), accelerometer.getVendor(), accelerometer.getMaxDelay() + "", accelerometer.getMaximumRange() + "", accelerometer.getPower() + "", accelerometer.getResolution() + "", accelerometer.getVersion() + "", current_xValue, current_yValue, current_zValue));
        } else {
            accelerometerDataList.add(new AccelerometerData(SystemDateTime.geTime(), timerForDataCollection.getStopWatch(), "", "", "", "", "", "", "", current_xValue, current_yValue, current_zValue));
        }
    }

    private void startDataCollection() {
        btnStart.setVisibility(View.GONE);
        btnStop.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.VISIBLE);

        sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
        timerForDataCollection.startTimer();
    }

    private void pauseDataCollection() {
        btnStart.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);

        sensorManager.unregisterListener(this);
        timerForDataCollection.pauseTimer();
    }

    private void stopDataCollection() {
        sensorManager.unregisterListener(this);
        timerForDataCollection.resetTimer();

        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
        btnPause.setVisibility(View.GONE);

        showDataSavingConfirmationDialog();
        resetData();
    }

    private void resetData() {
        firstLineCSV = true;
        accelerometerDataList = new LinkedList<>();
    }

    private void showDataSavingConfirmationDialog() {
        ConfirmationDialogAndSaveData confirmationDialogAndSaveData = new ConfirmationDialogAndSaveData(getContext(), accelerometerDataList);
        confirmationDialogAndSaveData.showConformationDialog();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //accelerometer does not report accuracy changes

    }

    @Override
    public void onStart() {
        super.onStart();
        //     sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }


}