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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.MathematicsMethods;

public class FactorizationActivity extends AppCompatActivity {
    private TextView outputTextView;
    private EditText inputEditText;
    private Toast toast;

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
        setContentView(R.layout.activity_factorization);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        inputEditText = findViewById(R.id.factorization_edit_text_input);
        outputTextView = findViewById(R.id.factorization_text_view_output);
        Button submitButton = findViewById(R.id.factorization_button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Long num = Long.parseLong(inputEditText.getText().toString());
                    Long[] longs = MathematicsMethods.getPrimeFactors(num);
                    outputTextView.setText(arrayToString(longs));
                } catch (NumberFormatException e) {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(FactorizationActivity.this, "Invalid Input", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private String arrayToString(Long[] longs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Long l : longs) {
            stringBuilder.append(l);
            stringBuilder.append(", ");
        }
        int len = stringBuilder.length();
        if (len > 2) {
            stringBuilder.delete(len - 2, len);
        }
        return stringBuilder.toString();
    }
}
