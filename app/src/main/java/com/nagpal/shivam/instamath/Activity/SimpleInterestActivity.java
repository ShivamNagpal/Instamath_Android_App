package com.nagpal.shivam.instamath.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nagpal.shivam.instamath.R;

public class SimpleInterestActivity extends AppCompatActivity {


    private TextView textViewResult;
    private EditText editTextPrincipal;
    private EditText editTextRate;
    private EditText editTextTime;
    private double simpleInterest;
    private double principalValue;
    private double rateValue;
    private double timeValue;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_interest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editTextPrincipal = (EditText) findViewById(R.id.principal_edit_text);
        editTextRate = (EditText) findViewById(R.id.rate_edit_text);
        editTextTime = (EditText) findViewById(R.id.time_edit_text);
        textViewResult = (TextView) findViewById(R.id.result_text_view);
        Button btnResult = (Button) findViewById(R.id.result_button);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPrincipalValue;
                String strRateValue;
                String strTimeValue;
                strPrincipalValue = editTextPrincipal.getText().toString();
                strRateValue = editTextRate.getText().toString();
                strTimeValue = editTextTime.getText().toString();

                if (!(strPrincipalValue.isEmpty() || strRateValue.isEmpty() || strTimeValue.isEmpty())) {
                    principalValue = Double.parseDouble(strPrincipalValue);
                    rateValue = Double.parseDouble(strRateValue);
                    timeValue = Double.parseDouble(strTimeValue);
                    simpleInterest = (principalValue * rateValue * timeValue) / 100;
                } else {
                    simpleInterest = Double.NaN;
                    Toast.makeText(SimpleInterestActivity.this, "Check Values", Toast.LENGTH_SHORT).show();
                }
                String strDisplay = "Simple Interest: " + simpleInterest;
                textViewResult.setText(strDisplay);
            }
        });
    }
}
