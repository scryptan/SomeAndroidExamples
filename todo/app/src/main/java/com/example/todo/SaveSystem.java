package com.example.todo;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class SaveSystem {
    private static String fileName = "saved.json";

    public static void SaveState(Context context, String content){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        }catch (Exception e){
            Log.e("SaveSystem", "SaveState: ", e);
        }
    }

    public static InputStreamReader ReadState(Context context){
        try {
            FileInputStream fis = context.openFileInput(fileName);
            return new InputStreamReader(fis);
        }catch (Exception e){
            Log.e("SaveSystem", "ReadState: ", e);
        }
        return null;
    }
}
