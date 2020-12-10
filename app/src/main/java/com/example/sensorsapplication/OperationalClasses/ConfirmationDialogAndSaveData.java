package com.example.sensorsapplication.OperationalClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.sensorsapplication.ObjectClasses.AccelerometerData;
import com.example.sensorsapplication.R;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.List;

public class ConfirmationDialogAndSaveData {
    Context context;
    List<AccelerometerData> accelerometerDataList;

    public ConfirmationDialogAndSaveData(Context context, List<AccelerometerData> accelerometerDataList) {
        this.context = context;
        this.accelerometerDataList = accelerometerDataList;
    }

    public void showConformationDialog(){
        new AlertDialog.Builder(context)
                .setTitle("Save Sensor Data")
                .setMessage("Do you want to Save Sensor Data?")
                .setIcon(R.mipmap.microchip)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        saveDataCSV();
                        Toast.makeText(context, "Data Saved Successfully to /Android/edata/data/SensorApplicationDownloadResults Folder!!!", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void saveDataCSV() {
        try {
            OpenCSVWriter.writeFromListOfObjects(accelerometerDataList, "AccelerometerData"+SystemDateTime.getDate()+SystemDateTime.geTimeForCSV()+".csv");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
