package com.example.sensorsapplication.SensorsFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sensorsapplication.R;

import java.util.List;

import static com.example.sensorsapplication.R.drawable.list_divider;


public class MainFragment extends Fragment {

    OnSelectedListener callback;
    private SensorManager sensorManager;
    List<Sensor> deviceSensors;


    public MainFragment() {
        // Required empty public constructor
    }

    public void setSelectedListener(OnSelectedListener callback) {
        this.callback = callback;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        loadAvailableSensorDate();
        setUpViews(view);
    }

    private void loadAvailableSensorDate() {
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    private void setUpViews(View view) {
        ListView list = (ListView) view.findViewById(R.id.sensor_list);
        CustomAdapterList(list);
    }

    private void CustomAdapterList(ListView list) {
        if (!deviceSensors.isEmpty()) {
            CustomAdapter customAdapter = new CustomAdapter(deviceSensors, getContext(), callback);
            list.setAdapter(customAdapter);
        } else list.setVisibility(View.GONE);
    }
}