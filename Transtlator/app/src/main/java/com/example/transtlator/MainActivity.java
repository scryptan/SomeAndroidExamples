package com.example.transtlator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Character, String> alphabet = new HashMap<Character, String>()
    {{
            put('А', "A");
        put('Б', "B");
        put('В',"V");
        put('Г',"G");
        put('Д' ,"D");
        put('Е' ,"E");
        put('Ё' ,"YO");
        put('Ж' ,"ZH");
        put('З' ,"Z");
        put('И' ,"I");
        put('Й' ,"Y");
        put('К' ,"K");
        put('Л' ,"L");
        put('М' ,"M");
        put('Н' ,"N");
        put('О' ,"O");
        put('П' ,"P");
        put('Р' ,"R");
        put('С' ,"S");
        put('Т' ,"T");
        put('У' ,"U");
        put('Ф' ,"F");
        put('Х' ,"KH");
        put('Ц' ,"TS");
        put('Ч' ,"CH");
        put('Ш' ,"SH");
        put('Щ' ,"SHCH");
        put('Ъ' , null);
        put('Ы' ,"Y");
        put('Ь' , null);
        put('Э' ,"E");
        put('Ю' ,"YU");
        put('Я' , "YA");
        put(' ', " ");
    }};
    private EditText inputBox;
    private TextView outputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputBox = findViewById(R.id.answerTV);
        inputBox = findViewById(R.id.inputTV);
        try {
            Log.e("TAG", "onCreate: "+new String(SaveSystem.ReadState(getApplicationContext()).getBytes("UTF-8"), "UTF-8") );
            inputBox.setText(new String(SaveSystem.ReadState(getApplicationContext()).getBytes(), "UTF-8"));
            TextUpdater();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        inputBox.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Log.e("TAG", "afterTextChanged: "+ inputBox.getText().toString().toUpperCase());
                    SaveSystem.SaveState(getApplicationContext(), inputBox.getText().toString().toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextUpdater();
            }
        });
    }

    public void TextUpdater()
    {
        char[] tempInput = inputBox.getText().toString().toUpperCase().toCharArray();
        String tempOutput = "";
        for (char letter : tempInput) {
            String nextLetter = alphabet.get(letter);
            if(nextLetter == null)
                nextLetter = "";
            tempOutput += nextLetter;
        }
        outputBox.setText(tempOutput);
    }
}