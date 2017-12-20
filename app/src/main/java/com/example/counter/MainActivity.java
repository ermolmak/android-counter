package com.example.counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String intKey = "INT_KEY";

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("inf", "onCreate");

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(intKey);
        }

        TextView textView = (TextView) findViewById(R.id.textViewCounter);
        textView.setText(String.format("%d", counter));

        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textViewCounter);
                textView.setText(String.format("%d", ++counter));
            }
        });

        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    return;
                }
                TextView textView = (TextView) findViewById(R.id.textViewCounter);
                textView.setText(String.format("%d", --counter));
            }
        });

        Button buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null) {
                    calculate();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(intKey, counter);

        Log.i("inf", "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

    private void calculate() {
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        int result;
        try {
            result = counter * Integer.parseInt(editText.getText().toString());
            TextView textView = (TextView) findViewById(R.id.textViewResult);
            textView.setText(String.format("%d", result));
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.parse_int_error, Toast.LENGTH_SHORT).show();
        }
    }
}
