package com.nagpal.shivam.instamath.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nagpal.shivam.expressionparser.Expression;
import com.nagpal.shivam.expressionparser.ExpressionParserException;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.Constants;

import java.text.DecimalFormat;

public class BasicCalculatorActivity extends AppCompatActivity {

    private StringBuilder stringBuilder;
    private TextView txtViewBasicResultDisplay;
    private TextView txtViewBasicExpressionDisplay;
    private HorizontalScrollView hsvResult;
    private double result;
    private DecimalFormat decimalFormat;

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
        setContentView(R.layout.activity_basic_calculator);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        stringBuilder = new StringBuilder();

        initViews();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, MODE_PRIVATE);

        StringBuilder patternDecimalFormat = new StringBuilder("#0.");
        for (int i = 0; i < sharedPreferences.getFloat(OldPreferencesActivity.PREFERENCES_FIX_KEY, OldPreferencesActivity.PREFERENCES_FIX_DEFAULT_VALUE); i++) {
            patternDecimalFormat.append("0");
        }
        decimalFormat = new DecimalFormat(patternDecimalFormat.toString());

        Button btnZero = (Button) findViewById(R.id.button_basic_zero);
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("0");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnOne = (Button) findViewById(R.id.button_basic_one);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("1");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnTwo = (Button) findViewById(R.id.button_basic_two);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("2");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnThree = (Button) findViewById(R.id.button_basic_three);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("3");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnFour = (Button) findViewById(R.id.button_basic_four);
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("4");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnFive = (Button) findViewById(R.id.button_basic_five);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("5");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnSix = (Button) findViewById(R.id.button_basic_six);
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("6");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnSeven = (Button) findViewById(R.id.button_basic_seven);
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("7");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnEight = (Button) findViewById(R.id.button_basic_eight);
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("8");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnNine = (Button) findViewById(R.id.button_basic_nine);
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("9");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnPlus = (Button) findViewById(R.id.button_basic_plus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("+");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnMinus = (Button) findViewById(R.id.button_basic_minus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("-");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnMultiply = (Button) findViewById(R.id.button_basic_multiply);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("*");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnDivide = (Button) findViewById(R.id.button_basic_divide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("/");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnPercent = (Button) findViewById(R.id.button_basic_percent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("%");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnDot = (Button) findViewById(R.id.button_basic_dot);
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(".");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnEquate = (Button) findViewById(R.id.button_basic_equate);
        btnEquate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExpressionDisplay(stringBuilder.toString());
                result = Double.NaN;
                try {
                    Expression expression = new Expression(stringBuilder.toString());
                    result = expression.evaluate();
                    result = Double.parseDouble(decimalFormat.format(result));
                    if (Math.abs(result) == 0) {
                        result = Math.abs(result);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (ExpressionParserException e) {
                    e.printStackTrace();
                    Toast.makeText(BasicCalculatorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                stringBuilder.setLength(0);
                stringBuilder.append(result);
                String temp = "= " + stringBuilder.toString();
                txtViewBasicResultDisplay.setText(temp);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hsvResult.fullScroll(View.FOCUS_LEFT);
                    }
                }, 25);
//                updateResultDisplay("= " + stringBuilder.toString());
            }
        });
        Button btnBack = (Button) findViewById(R.id.button_basic_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    updateResultDisplay(stringBuilder.toString());
                }
            }
        });

        Button btnClear = (Button) findViewById(R.id.button_basic_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.setLength(0);
                updateExpressionDisplay(null);
                updateResultDisplay("0");
            }
        });
        txtViewBasicExpressionDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.setLength(0);
                String expression = txtViewBasicExpressionDisplay.getText().toString();
                if (!expression.isEmpty()) {
                    stringBuilder.append(expression);
                    updateResultDisplay(stringBuilder.toString());
                }
                updateExpressionDisplay(null);
            }
        });
    }

    private void initViews() {
        txtViewBasicResultDisplay = findViewById(R.id.text_view_basic_result_display);
        txtViewBasicExpressionDisplay = findViewById(R.id.text_view_basic_expression_display);
        hsvResult = (HorizontalScrollView) txtViewBasicResultDisplay.getParent();
    }

    private void updateExpressionDisplay(String string) {
        txtViewBasicExpressionDisplay.setText(string);
    }

    private void updateResultDisplay(String string) {
        txtViewBasicResultDisplay.setText(string);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hsvResult.fullScroll(View.FOCUS_RIGHT);
            }
        }, 25);
    }
}