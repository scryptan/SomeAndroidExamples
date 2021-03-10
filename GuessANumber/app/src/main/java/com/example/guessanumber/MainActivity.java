package com.example.guessanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView guessTV;
    private TextView answerTV;

    private int middle = 50;
    private int low = 1;
    private int high = 100;
    private String message = "Ваше число >=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessTV = findViewById(R.id.guessTV);
        answerTV = findViewById(R.id.answerTV);
        answerTV.setVisibility(View.INVISIBLE);
        guessTV.setText(String.format("%s%d?", message, middle));
    }

    public void notAbove(View view) {
        updater(false);
    }

    public void above(View view) {
        updater(true);
    }

    private void updater(boolean isAbove) {
        if (middle == low || middle == high) {
            answerTV.setText(String.format("Ваше число:%d", middle));
            answerTV.setVisibility(View.VISIBLE);
            return;
        }

        if (isAbove)
            low = middle;
        else
            high = middle;

        middle = (low + high) / 2;
        guessTV.setText(String.format("%s%d?", message, middle));
    }
}