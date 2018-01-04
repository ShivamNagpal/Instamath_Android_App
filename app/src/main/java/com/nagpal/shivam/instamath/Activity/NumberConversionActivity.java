package com.nagpal.shivam.instamath.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nagpal.shivam.instamath.R;

import java.util.ArrayList;

public class NumberConversionActivity extends AppCompatActivity {
    private static final String decimalStr = "Decimal";
    private static final String binaryStr = "Binary";
    private static final String octalStr = "Octal";
    private static final String hexadecimalStr = "Hexadecimal";
    private EditText etInput;
    private EditText etOutput;
    private Spinner spnrInput;
    private Spinner spnrOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_conversion);

        initViews();

        ArrayList<String> numberTypeArrayList = new ArrayList<>();
        numberTypeArrayList.add(decimalStr);
        numberTypeArrayList.add(binaryStr);
        numberTypeArrayList.add(octalStr);
        numberTypeArrayList.add(hexadecimalStr);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(NumberConversionActivity.this, android.R.layout.simple_spinner_item, numberTypeArrayList);

        spnrInput.setAdapter(stringArrayAdapter);
        spnrOutput.setAdapter(stringArrayAdapter);
        spnrOutput.setSelection(1);

        spnrInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etInput.setText(null);
                switch (adapterView.getSelectedItem().toString()) {
                    case decimalStr:
                        etInput.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                        break;
                    case binaryStr:
                        etInput.setKeyListener(DigitsKeyListener.getInstance("01"));
                        break;
                    case octalStr:
                        etInput.setKeyListener(DigitsKeyListener.getInstance("01234567"));
                        break;
                    case hexadecimalStr:
                        etInput.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEF"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnSubmit = findViewById(R.id.submit_button_number_conversion_activity);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                String input = etInput.getText().toString();
                switch (spnrInput.getSelectedItem().toString() + spnrOutput.getSelectedItem().toString()) {

                    case decimalStr + binaryStr:
                        result = fromDecimal(2, input);
                        break;

                    case decimalStr + octalStr:
                        result = fromDecimal(8, input);
                        break;

                    case decimalStr + hexadecimalStr:
                        result = fromDecimal(16, input);
                        break;

                    case binaryStr + decimalStr:
                        result = toDecimal(2, input);
                        break;

                    case binaryStr + octalStr:
                        result = binaryToOctal(input);
                        break;

                    case binaryStr + hexadecimalStr:
                        result = binaryToHexadecimal(input);
                        break;

                    case octalStr + decimalStr:
                        result = toDecimal(8, input);
                        break;

                    case hexadecimalStr + decimalStr:
                        result = toDecimal(16, input);
                        break;
                }
                etOutput.setText(result);
            }
        });
    }

    private void initViews() {
        spnrInput = findViewById(R.id.input_type_spinner_number_conversion_activity);
        spnrOutput = findViewById(R.id.output_type_spinner_number_conversion_activity);
        etInput = findViewById(R.id.input_type_edit_text_number_conversion_activity);
        etOutput = findViewById(R.id.output_type_edit_text_number_conversion_activity);
    }


    private String fromDecimal(int base, String str) {
        int decimalNumber;
        char temp;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            decimalNumber = Integer.parseInt(str);
            while (decimalNumber != 0) {
                temp = Character.forDigit(decimalNumber % base, base);
                temp = Character.toUpperCase(temp);
                stringBuilder.insert(0, temp);
                decimalNumber /= base;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String toDecimal(int base, String str) {
        long decimalOutput = 0;
        int i = 0;
        int temp;
        StringBuilder input = new StringBuilder(str);
        while (input.length() > 0) {
            temp = Character.digit(input.charAt(input.length() - 1), base);
            decimalOutput += temp * Math.pow(base, i++);
            input.setLength(input.length() - 1);
        }
        return decimalOutput != 0 ? Long.toString(decimalOutput) : null;
    }

    private String binaryToOctal(String str) {
        StringBuilder input = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        String temp;

        while (input.length() >= 3) {
            temp = input.substring(input.length() - 3);
            result.insert(0, toDecimal(2, temp));
            input.setLength(input.length() - 3);
        }

        if (input.length() > 0) {
            result.insert(0, toDecimal(2, input.toString()));
            input.setLength(0);
        }
        return result.toString();
    }

    private String binaryToHexadecimal(String str) {
        StringBuilder input = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        String temp;

        while (input.length() >= 4) {
            temp = input.substring(input.length() - 4);
            temp = toDecimal(2, temp);
            result.insert(0, Character.toUpperCase(Character.forDigit(Integer.parseInt(temp), 16)));
            input.setLength(input.length() - 4);
        }

        if (input.length() > 0) {
            temp = input.toString();
            temp = toDecimal(2, temp);
            result.insert(0, Character.toUpperCase(Character.forDigit(Integer.parseInt(temp), 16)));
            input.setLength(0);
        }
        return result.toString();
    }

}

  /*private String toDecimal(int base, String str) {
        int inputNumber;
        int i = 0;
        int decimalNumber = 0;
        int val;
        try {
            inputNumber = Integer.parseInt(str);
            while (inputNumber != 0) {
                val = inputNumber % 10;
                inputNumber /= 10;
                decimalNumber += val * Math.pow(base, i++);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Integer.toString(decimalNumber);
    }*/


  /*private String binaryToOctal(String str) {
        int binaryNumber;
        int val;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            binaryNumber = Integer.parseInt(str);
            while (binaryNumber != 0) {
                val = binaryNumber % 1000;
                binaryNumber /= 1000;
                stringBuilder.insert(0, toDecimal(2, Integer.toString(val)));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }*/