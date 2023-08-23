/*
 * Copyright 2018 Shivam Nagpal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagpal.shivam.instamath.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nagpal.shivam.instamath.Adapter.HexadecimalValueAdapter;
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
    private RecyclerView rvHexadecimalInput;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_conversion);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

        ArrayList<String> numberTypeArrayList = new ArrayList<>();
        numberTypeArrayList.add(decimalStr);
        numberTypeArrayList.add(binaryStr);
        numberTypeArrayList.add(octalStr);
        numberTypeArrayList.add(hexadecimalStr);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(NumberConversionActivity.this, android.R.layout.simple_spinner_dropdown_item, numberTypeArrayList);

        spnrInput.setAdapter(stringArrayAdapter);
        spnrOutput.setAdapter(stringArrayAdapter);
        spnrOutput.setSelection(1);

        ArrayList<String> hexadecimalValueArrayList = new ArrayList<>();
        hexadecimalValueArrayList.add("A");
        hexadecimalValueArrayList.add("B");
        hexadecimalValueArrayList.add("C");
        hexadecimalValueArrayList.add("D");
        hexadecimalValueArrayList.add("E");
        hexadecimalValueArrayList.add("F");

        rvHexadecimalInput.setHasFixedSize(true);
        rvHexadecimalInput.setLayoutManager(new GridLayoutManager(NumberConversionActivity.this, 6));
        rvHexadecimalInput.setAdapter(new HexadecimalValueAdapter(NumberConversionActivity.this, hexadecimalValueArrayList));

        spnrInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                etInput.setText(null);
                rvHexadecimalInput.setVisibility(View.GONE);

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
                        rvHexadecimalInput.setVisibility(View.VISIBLE);
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

                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(NumberConversionActivity.this, "Enter a value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (spnrInput.getSelectedItem().toString() + spnrOutput.getSelectedItem().toString()) {

                    case decimalStr + decimalStr:
                    case binaryStr + binaryStr:
                    case octalStr + octalStr:
                    case hexadecimalStr + hexadecimalStr:
                        result = input;
                        break;

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

                    case octalStr + binaryStr:
                        result = octalToBinary(input);
                        break;

                    case octalStr + hexadecimalStr:
                        result = binaryToHexadecimal(octalToBinary(input));
                        break;

                    case hexadecimalStr + decimalStr:
                        result = toDecimal(16, input);
                        break;

                    case hexadecimalStr + binaryStr:
                        result = hexadecimalToBinary(input);
                        break;

                    case hexadecimalStr + octalStr:
                        result = binaryToOctal(hexadecimalToBinary(input));
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
        rvHexadecimalInput = findViewById(R.id.hexadecimal_input_recycler_view);
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
        return Long.toString(decimalOutput);
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
            temp = toDecimal(2, input.toString());
            if (!temp.equals("0")) {
                result.insert(0, temp);
            }
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
            if (!temp.equals("0")) {
                result.insert(0, Character.toUpperCase(Character.forDigit(Integer.parseInt(temp), 16)));
            }
            input.setLength(0);
        }
        return result.toString();
    }

    private String octalToBinary(String str) {
        StringBuilder input = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        String temp;

        while (input.length() > 0) {
            temp = input.substring(input.length() - 1);
            result.insert(0, fromDecimal(2, temp));
            while (result.length() % 3 != 0) {
                result.insert(0, "0");
            }
            input.setLength(input.length() - 1);
        }
        return result.toString();
    }

    private String hexadecimalToBinary(String str) {
        StringBuilder input = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        char temp;

        while (input.length() > 0) {
            temp = input.charAt(input.length() - 1);
            result.insert(0, fromDecimal(2, Integer.toString(Character.digit(temp, 16))));
            while (result.length() % 4 != 0) {
                result.insert(0, "0");
            }
            input.setLength(input.length() - 1);
        }
        return result.toString();
    }

}