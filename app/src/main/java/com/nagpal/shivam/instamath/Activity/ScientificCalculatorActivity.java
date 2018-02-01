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


public class ScientificCalculatorActivity extends AppCompatActivity {

    private StringBuilder stringBuilder;
    private TextView txtViewScientificResultDisplay;
    private TextView txtViewScientificExpressionDisplay;
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
        setContentView(R.layout.activity_scientific_calculator);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        stringBuilder = new StringBuilder();
        initViews();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, MODE_PRIVATE);

        StringBuilder patternDecimalFormat = new StringBuilder("#0.");
        for (int i = 0; i < sharedPreferences.getFloat(PreferencesActivity.PREFERENCES_FIX_KEY, PreferencesActivity.PREFERENCES_FIX_DEFAULT_VALUE); i++) {
            patternDecimalFormat.append("0");
        }
        decimalFormat = new DecimalFormat(patternDecimalFormat.toString());

        switch (PreferencesActivity.PREFERENCE_ANGLE_UNITS[sharedPreferences.getInt(PreferencesActivity.PREFERENCES_ANGLE_UNIT_KEY, PreferencesActivity.PREFERENCES_ANGLE_UNIT_DEFAULT_VALUE)]) {
            case PreferencesActivity.PREFERENCES_ANGLE_UNIT_DEGREE:
                Expression.setAngleUnits(Expression.ANGLE_UNITS_DEGREE);
                break;
            case PreferencesActivity.PREFERENCES_ANGLE_UNIT_RADIAN:
                Expression.setAngleUnits(Expression.ANGLE_UNITS_RADIAN);
                break;
        }

        Button btnZero = (Button) findViewById(R.id.button_scientific_zero);
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("0");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnOne = (Button) findViewById(R.id.button_scientific_one);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("1");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnTwo = (Button) findViewById(R.id.button_scientific_two);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("2");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnThree = (Button) findViewById(R.id.button_scientific_three);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("3");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnFour = (Button) findViewById(R.id.button_scientific_four);
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("4");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnFive = (Button) findViewById(R.id.button_scientific_five);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("5");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnSix = (Button) findViewById(R.id.button_scientific_six);
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("6");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnSeven = (Button) findViewById(R.id.button_scientific_seven);
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("7");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnEight = (Button) findViewById(R.id.button_scientific_eight);
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("8");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnNine = (Button) findViewById(R.id.button_scientific_nine);
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("9");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnPlus = (Button) findViewById(R.id.button_scientific_plus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("+");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnMinus = (Button) findViewById(R.id.button_scientific_minus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("-");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnMultiply = (Button) findViewById(R.id.button_scientific_multiply);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("*");
                updateResultDisplay(stringBuilder.toString());
            }
        });
        Button btnDivide = (Button) findViewById(R.id.button_scientific_divide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("/");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnDot = (Button) findViewById(R.id.button_scientific_dot);
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(".");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnSine = (Button) findViewById(R.id.button_scientific_sine);
        btnSine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("sin(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnCosine = (Button) findViewById(R.id.button_scientific_cosine);
        btnCosine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("cos(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnTangent = (Button) findViewById(R.id.button_scientific_tangent);
        btnTangent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("tan(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnFactorial = (Button) findViewById(R.id.button_scientific_factorial);
        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("!");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnNaturalLog = (Button) findViewById(R.id.button_scientific_natural_log);
        btnNaturalLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("ln(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnLogBaseTen = (Button) findViewById(R.id.button_scientific_log_base_ten);
        btnLogBaseTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("log(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnSquareRoot = (Button) findViewById(R.id.button_scientific_square_root);
        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("sqrt(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnPower = (Button) findViewById(R.id.button_scientific_power);
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("^");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnEular = (Button) findViewById(R.id.button_scientific_eular);
        btnEular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("e");
                updateResultDisplay(stringBuilder.toString());
            }
        });


        Button btnPi = (Button) findViewById(R.id.button_scientific_pi);
        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("pi");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnParenthesis = (Button) findViewById(R.id.button_scientific_parenthesis_left);
        btnParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append("(");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnParenthesisRight = (Button) findViewById(R.id.button_scientific_parenthesis_right);
        btnParenthesisRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(")");
                updateResultDisplay(stringBuilder.toString());
            }
        });

        Button btnEquate = (Button) findViewById(R.id.button_scientific_equate);
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
                    Toast.makeText(ScientificCalculatorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                stringBuilder.setLength(0);
                stringBuilder.append(result);
                String temp = "= " + result;
                txtViewScientificResultDisplay.setText(temp);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hsvResult.fullScroll(View.FOCUS_LEFT);
                    }
                }, 25);
            }
        });
        Button btnBack = (Button) findViewById(R.id.button_scientific_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    updateResultDisplay(stringBuilder.toString());
                }
            }
        });
        Button btnClear = (Button) findViewById(R.id.button_scientific_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.setLength(0);
                updateResultDisplay("0");
                updateExpressionDisplay(null);
            }
        });

        txtViewScientificExpressionDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.setLength(0);
                String expression = txtViewScientificExpressionDisplay.getText().toString();
                if (!expression.isEmpty()) {
                    stringBuilder.append(expression);
                    updateResultDisplay(stringBuilder.toString());
                }
                updateExpressionDisplay(null);
            }
        });

    }

    private void initViews() {
        txtViewScientificExpressionDisplay = findViewById(R.id.text_view_scientific_expression_display);
        txtViewScientificResultDisplay = (TextView) findViewById(R.id.text_view_scientific_result_display);
        hsvResult = (HorizontalScrollView) txtViewScientificResultDisplay.getParent();
    }

    /**
     * updateResultDisplay Method in ScientificCalculatorActivity
     *
     * @param string (A String Variable)
     *               Updates the text in the View with the string.
     */
    private void updateExpressionDisplay(String string) {
        txtViewScientificExpressionDisplay.setText(string);
    }

    private void updateResultDisplay(String string) {
        txtViewScientificResultDisplay.setText(string);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hsvResult.fullScroll(View.FOCUS_RIGHT);

            }
        }, 25);
    }

}