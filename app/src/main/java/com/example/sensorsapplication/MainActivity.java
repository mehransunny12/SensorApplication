package com.example.sensorsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import com.example.sensorsapplication.Permissions.Permissions;
import com.example.sensorsapplication.SensorsFragments.AccelerometerFragment;
import com.example.sensorsapplication.SensorsFragments.MainFragment;
import com.example.sensorsapplication.SensorsFragments.OnSelectedListener;

public class MainActivity extends AppCompatActivity implements OnSelectedListener {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkWritingPermission();
        set_Fragment(new MainFragment()); // main fragment
    }

    private void set_Fragment(Fragment fragment) {
        // Begin the transaction
        ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_holder, fragment);
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof MainFragment) {
            MainFragment mainFragment = (MainFragment) fragment;
            mainFragment.setSelectedListener(this);
        }
    }

    @Override
    public void Proceedselction(int selection) {
        // Call new Fragment with all details for Accelerometer
        set_Fragment(new AccelerometerFragment()); // Accelerometer fragment
    }

    private void checkWritingPermission() {
        Permissions permissions = new Permissions(this);
        permissions.requestAccessPubSpacePermissions();
    }
}