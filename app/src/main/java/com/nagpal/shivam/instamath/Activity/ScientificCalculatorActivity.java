package com.nagpal.shivam.instamath.Activity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nagpal.shivam.expressionparser.Expression;
import com.nagpal.shivam.expressionparser.ExpressionParserException;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.ConstantMethods;
import com.nagpal.shivam.instamath.Utils.Constants;

import java.text.DecimalFormat;


public class ScientificCalculatorActivity extends AppCompatActivity {

    private AlertDialog expandableButtonDialog;
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

        Expression.setNormalizeTrigonometricFunctions(true);

        Button btnZero = findViewById(R.id.button_scientific_zero);
        btnZero.setOnClickListener(buttonOnClickListener(false));

        Button btnOne = findViewById(R.id.button_scientific_one);
        btnOne.setOnClickListener(buttonOnClickListener(false));

        Button btnTwo = findViewById(R.id.button_scientific_two);
        btnTwo.setOnClickListener(buttonOnClickListener(false));

        Button btnThree = findViewById(R.id.button_scientific_three);
        btnThree.setOnClickListener(buttonOnClickListener(false));

        Button btnFour = findViewById(R.id.button_scientific_four);
        btnFour.setOnClickListener(buttonOnClickListener(false));

        Button btnFive = findViewById(R.id.button_scientific_five);
        btnFive.setOnClickListener(buttonOnClickListener(false));

        Button btnSix = findViewById(R.id.button_scientific_six);
        btnSix.setOnClickListener(buttonOnClickListener(false));

        Button btnSeven = findViewById(R.id.button_scientific_seven);
        btnSeven.setOnClickListener(buttonOnClickListener(false));

        Button btnEight = findViewById(R.id.button_scientific_eight);
        btnEight.setOnClickListener(buttonOnClickListener(false));

        Button btnNine = findViewById(R.id.button_scientific_nine);
        btnNine.setOnClickListener(buttonOnClickListener(false));

        Button btnPlus = findViewById(R.id.button_scientific_plus);
        btnPlus.setOnClickListener(buttonOnClickListener(false));

        Button btnMinus = findViewById(R.id.button_scientific_minus);
        btnMinus.setOnClickListener(buttonOnClickListener(false));

        Button btnMultiply = findViewById(R.id.button_scientific_multiply);
        btnMultiply.setOnClickListener(buttonOnClickListener(false));

        Button btnDivide = findViewById(R.id.button_scientific_divide);
        btnDivide.setOnClickListener(buttonOnClickListener(false));

        Button btnDot = findViewById(R.id.button_scientific_dot);
        btnDot.setOnClickListener(buttonOnClickListener(false));

        Button btnSine = findViewById(R.id.button_scientific_sine);
        btnSine.setOnClickListener(buttonOnClickListener(true));
        btnSine.setOnLongClickListener(buttonOnLongClickListener(new String[]{"sin", "asin"}, true));

        Button btnCosine = findViewById(R.id.button_scientific_cosine);
        btnCosine.setOnClickListener(buttonOnClickListener(true));
        btnCosine.setOnLongClickListener(buttonOnLongClickListener(new String[]{"cos", "acos"}, true));

        Button btnTangent = findViewById(R.id.button_scientific_tangent);
        btnTangent.setOnClickListener(buttonOnClickListener(true));
        btnTangent.setOnLongClickListener(buttonOnLongClickListener(new String[]{"tan", "atan"}, true));

        Button btnFactorial = findViewById(R.id.button_scientific_factorial);
        btnFactorial.setOnClickListener(buttonOnClickListener(false));

        Button btnNaturalLog = findViewById(R.id.button_scientific_natural_log);
        btnNaturalLog.setOnClickListener(buttonOnClickListener(true));

        Button btnLogBaseTen = findViewById(R.id.button_scientific_log_base_ten);
        btnLogBaseTen.setOnClickListener(buttonOnClickListener(true));

        Button btnSquareRoot = findViewById(R.id.button_scientific_square_root);
        btnSquareRoot.setOnClickListener(buttonOnClickListener(true));
        btnSquareRoot.setOnLongClickListener(buttonOnLongClickListener(new String[]{"\u221A", "\u221B"}, true));

        Button btnPower = findViewById(R.id.button_scientific_power);
        btnPower.setOnClickListener(buttonOnClickListener(false));

        Button btnEular = findViewById(R.id.button_scientific_eular);
        btnEular.setOnClickListener(buttonOnClickListener(false));


        Button btnPi = findViewById(R.id.button_scientific_pi);
        btnPi.setOnClickListener(buttonOnClickListener(false));

        Button btnParenthesis = findViewById(R.id.button_scientific_parenthesis_left);
        btnParenthesis.setOnClickListener(buttonOnClickListener(false));

        Button btnParenthesisRight = findViewById(R.id.button_scientific_parenthesis_right);
        btnParenthesisRight.setOnClickListener(buttonOnClickListener(false));

        Button btnEquate = findViewById(R.id.button_scientific_equate);
        btnEquate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExpressionDisplay(stringBuilder.toString());
                result = Double.NaN;
                try {
                    Expression expression = new Expression(convertToExpressionString(stringBuilder.toString()));
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
        Button btnBack = findViewById(R.id.button_scientific_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    updateResultDisplay(stringBuilder.toString());
                }
            }
        });
        Button btnClear = findViewById(R.id.button_scientific_clear);
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
        txtViewScientificResultDisplay = findViewById(R.id.text_view_scientific_result_display);
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

    private View showExpandableButtons(String[] buttonLabels, final Boolean isFunction) {
        Resources resources = getResources();

        LinearLayout rootLayout = new LinearLayout(ScientificCalculatorActivity.this);
        rootLayout.setGravity(Gravity.CENTER);

        int buttonMargin = (int) ConstantMethods.dpToFloat(resources, 16);

        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams((int) ConstantMethods.dpToFloat(resources, 56), (int) ConstantMethods.dpToFloat(resources, 56));
        buttonLayoutParams.setMargins(buttonMargin, buttonMargin, buttonMargin, buttonMargin);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(((Button) view).getText().toString());
                if (isFunction) {
                    stringBuilder.append("(");
                }
                updateResultDisplay(stringBuilder.toString());
                dismissExpandableButtonDialog();
            }
        };
        for (String label : buttonLabels) {
            Button button = new Button(ScientificCalculatorActivity.this);
            button.setLayoutParams(buttonLayoutParams);
            button.setText(label);
            button.setAllCaps(false);

            button.setBackground(resources.getDrawable(R.drawable.drawable_background_button_calculator));
            button.setOnClickListener(onClickListener);
            rootLayout.addView(button);
        }
        return rootLayout;
    }

    private void dismissExpandableButtonDialog() {
        if (expandableButtonDialog != null) {
            expandableButtonDialog.dismiss();
            expandableButtonDialog = null;
        }

    }

    private String convertToExpressionString(String string) {
        string = string.replaceAll("\u00D7", "*");
        string = string.replaceAll("\u00F7", "/");
        string = string.replaceAll("\u221A", "sqrt");
        string = string.replaceAll("\u221B", "cbrt");
        string = string.replaceAll("\u03C0", "pi");
        return string;
    }

    private View.OnClickListener buttonOnClickListener(final Boolean isFunction) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(((Button) view).getText().toString());
                if (isFunction) {
                    stringBuilder.append("(");
                }
                updateResultDisplay(stringBuilder.toString());
            }
        };
    }

    private View.OnLongClickListener buttonOnLongClickListener(final String[] buttonLabels, final Boolean isFunction) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScientificCalculatorActivity.this);
                builder.setView(showExpandableButtons(buttonLabels, isFunction));
                expandableButtonDialog = builder.create();
                expandableButtonDialog.show();
                return true;
            }
        };
    }


}