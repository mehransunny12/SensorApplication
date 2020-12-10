package com.example.sensorsapplication.OperationalClasses;

import android.os.Environment;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


public class OpenCSVWriter {

    public static final char CSV_SEPARATOR = ';';
    static String prePath = Environment.getExternalStorageDirectory()+"/Android/edata/data/SensorApplicationDownloadResults";


    public static <T> void writeFromListOfObjects(List<T> ObjectList, String OBJECT_PATH_SAMPLE) throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {

        OBJECT_PATH_SAMPLE= OBJECT_PATH_SAMPLE.replaceAll("[\\\\/:*?\"<>|]", "");
        OBJECT_PATH_SAMPLE= OBJECT_PATH_SAMPLE.replaceAll("\\s+", "");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            checkForExternalDirectry(new File(prePath));

            try (
                    Writer writer = Files.newBufferedWriter(Paths.get(prePath + "/" + OBJECT_PATH_SAMPLE));
            ) {
                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withSeparator(CSV_SEPARATOR)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withEscapechar(CSVWriter.NO_ESCAPE_CHARACTER)
                        .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                        .build();

                beanToCsv.write(ObjectList);
            } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                e.printStackTrace();
            }
        }
    }

    /*20200713: Make new Directry of it does not exist in android memory*/
    private static void checkForExternalDirectry(File file) {

        File folder = file;
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }

    }
    /* /storage/emulated/0/Android/edata/data/results/06_session_hr_intervals_20200109_173505
     * /storage/emulated/0/Android/edata/data/results/37-0-N_session_data_20200709_115919.csv */


    public static void save_data_in_StringList_CSV(String OBJECT_PATH_SAMPLE, List<List<String>> entries) {
        try {
            File file = new File(prePath + "/" + OBJECT_PATH_SAMPLE);
            List<String[]> temp = convertStringArrayList(entries);
            CSVWriter writer = new CSVWriter(
                    new FileWriter(file),
                    CSV_SEPARATOR,
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(temp);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> convertStringArrayList(List<List<String>> entries) {

        List<String[]> temp = new LinkedList<>();
        for (int count = 0; count < entries.size(); count++) {
            String[] tempArray = new String[entries.get(count).size()];
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = entries.get(count).get(i);
            }
            temp.add(tempArray);
        }


        return temp;
    }

}
