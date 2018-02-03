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

public class BasicCalculatorActivity extends AppCompatActivity {

    private AlertDialog expandableButtonDialog;
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
        for (int i = 0; i < sharedPreferences.getFloat(PreferencesActivity.PREFERENCES_FIX_KEY, PreferencesActivity.PREFERENCES_FIX_DEFAULT_VALUE); i++) {
            patternDecimalFormat.append("0");
        }
        decimalFormat = new DecimalFormat(patternDecimalFormat.toString());

        View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(((Button) view).getText().toString());
                updateResultDisplay(stringBuilder.toString());
            }
        };

        Button btnZero = findViewById(R.id.button_basic_zero);
        btnZero.setOnClickListener(buttonOnClickListener);

        Button btnOne = findViewById(R.id.button_basic_one);
        btnOne.setOnClickListener(buttonOnClickListener);

        Button btnTwo = findViewById(R.id.button_basic_two);
        btnTwo.setOnClickListener(buttonOnClickListener);

        Button btnThree = findViewById(R.id.button_basic_three);
        btnThree.setOnClickListener(buttonOnClickListener);

        Button btnFour = findViewById(R.id.button_basic_four);
        btnFour.setOnClickListener(buttonOnClickListener);

        Button btnFive = findViewById(R.id.button_basic_five);
        btnFive.setOnClickListener(buttonOnClickListener);

        Button btnSix = findViewById(R.id.button_basic_six);
        btnSix.setOnClickListener(buttonOnClickListener);

        Button btnSeven = findViewById(R.id.button_basic_seven);
        btnSeven.setOnClickListener(buttonOnClickListener);

        Button btnEight = findViewById(R.id.button_basic_eight);
        btnEight.setOnClickListener(buttonOnClickListener);

        Button btnNine = findViewById(R.id.button_basic_nine);
        btnNine.setOnClickListener(buttonOnClickListener);

        Button btnPlus = findViewById(R.id.button_basic_plus);
        btnPlus.setOnClickListener(buttonOnClickListener);

        Button btnMinus = findViewById(R.id.button_basic_minus);
        btnMinus.setOnClickListener(buttonOnClickListener);

        Button btnMultiply = findViewById(R.id.button_basic_multiply);
        btnMultiply.setOnClickListener(buttonOnClickListener);

        Button btnDivide = findViewById(R.id.button_basic_divide);
        btnDivide.setOnClickListener(buttonOnClickListener);

        Button btnPercent = findViewById(R.id.button_basic_percent);
        btnPercent.setOnClickListener(buttonOnClickListener);
        btnPercent.setOnLongClickListener(buttonOnLongClickListener(new String[]{"%", "#"}));

        Button btnDot = findViewById(R.id.button_basic_dot);
        btnDot.setOnClickListener(buttonOnClickListener);

        Button btnEquate = findViewById(R.id.button_basic_equate);
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
            }
        });
        Button btnBack = findViewById(R.id.button_basic_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    updateResultDisplay(stringBuilder.toString());
                }
            }
        });

        Button btnClear = findViewById(R.id.button_basic_clear);
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

    private View showExpandableButtons(String[] buttonLabels) {
        Resources resources = getResources();

        LinearLayout rootLayout = new LinearLayout(BasicCalculatorActivity.this);
        rootLayout.setGravity(Gravity.CENTER);

        int buttonMargin = (int) ConstantMethods.dpToFloat(resources, 16);

        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams((int) ConstantMethods.dpToFloat(resources, 56), (int) ConstantMethods.dpToFloat(resources, 56));
        buttonLayoutParams.setMargins(buttonMargin, buttonMargin, buttonMargin, buttonMargin);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.append(((Button) view).getText().toString());
                updateResultDisplay(stringBuilder.toString());
                dismissExpandableButtonDialog();
            }
        };
        for (String label : buttonLabels) {
            Button button = new Button(BasicCalculatorActivity.this);
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
        return string;
    }

    private View.OnLongClickListener buttonOnLongClickListener(final String[] buttonLabels) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BasicCalculatorActivity.this);
                builder.setView(showExpandableButtons(buttonLabels));
                expandableButtonDialog = builder.create();
                expandableButtonDialog.show();
                return true;
            }
        };
    }
}