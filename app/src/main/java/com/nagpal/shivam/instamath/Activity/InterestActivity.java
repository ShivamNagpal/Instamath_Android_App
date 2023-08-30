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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nagpal.shivam.instamath.R;

public class InterestActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Interest Activity";
    private static final String SIMPLE_INTEREST = "Simple Interest";
    private static final String COMPOUND_INTEREST = "Compound Interest";

    private EditText editTextPrincipal;
    private EditText editTextRate;
    private EditText editTextTime;
    private Spinner interestSpinner;
    private TextView textViewResult;

    private double amount;
    private double interest;
    private double principalValue;
    private double rateValue;
    private double timeValue;

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
        setContentView(R.layout.activity_interest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

        Button btnResult = findViewById(R.id.interest_button_result);

        interestSpinner.setAdapter(new ArrayAdapter<>(InterestActivity.this, android.R.layout.simple_spinner_dropdown_item, new String[]{SIMPLE_INTEREST, COMPOUND_INTEREST}));

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPrincipalValue;
                String strRateValue;
                String strTimeValue;
                String strDisplay = null;
                strPrincipalValue = editTextPrincipal.getText().toString();
                strRateValue = editTextRate.getText().toString();
                strTimeValue = editTextTime.getText().toString();

                try {
                    principalValue = Double.parseDouble(strPrincipalValue);
                    rateValue = Double.parseDouble(strRateValue);
                    timeValue = Double.parseDouble(strTimeValue);
                } catch (NumberFormatException e) {
                    Log.e(LOG_TAG, "Error parsing entered values.", e);
                    Toast.makeText(InterestActivity.this, "Check Values", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (interestSpinner.getSelectedItem().toString()) {
                    case SIMPLE_INTEREST:

                        interest = (principalValue * rateValue * timeValue) / 100;
                        strDisplay = "Simple Interest: " + interest;
                        break;

                    case COMPOUND_INTEREST:

                        amount = principalValue * Math.pow((1 + rateValue / 100), timeValue);
                        strDisplay = "Amount: " + amount;
                        break;
                }

                textViewResult.setText(strDisplay);

            }
        });
    }

    private void initViews() {
        editTextPrincipal = findViewById(R.id.interest_edit_text_principal);
        editTextRate = findViewById(R.id.interest_edit_text_rate);
        editTextTime = findViewById(R.id.interest_edit_text_time);
        textViewResult = findViewById(R.id.interest_text_view_result);
        interestSpinner = findViewById(R.id.interest_spinner);
    }
}
