package com.example.transtlator;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class SaveSystem {
    private static String fileName = "saved.json";

    public static void SaveState(Context context, String content){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes("UTF-8"));
            fos.close();
        }catch (Exception e){
            Log.e("SaveSystem", "SaveState: ", e);
        }
    }

    public static String ReadState(Context context){
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader reader =  new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String content = "";
            int i;

            while((i = reader.read())!= -1){
                content += (char)i;
            }
            return content;
        }catch (Exception e){
            Log.e("SaveSystem", "ReadState: ", e);
        }
        return null;
    }
}
