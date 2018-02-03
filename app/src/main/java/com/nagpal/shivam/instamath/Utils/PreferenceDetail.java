package com.nagpal.shivam.instamath.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nagpal.shivam.instamath.R;

public class PreferenceDetail {

    public static final byte TYPE_NUMBER = 1;
    public static final byte TYPE_STRING = 2;

    private byte type;

    private int labelIndex;

    private Context context;

    private float numberValue;
    private float maxValue;
    private float minValue;
    private float stepValue;

    private String key;
    private String title;
    private String[] spinnerLabels;

    private SharedPreferences sharedPreferences;

    public PreferenceDetail(@NonNull Context context, String title, String key, byte type, float defaultValue, float minValue, float maxValue, float stepValue) {

        this.context = context;
        this.title = title;
        this.key = key;
        this.type = type;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.stepValue = stepValue;
        sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, Context.MODE_PRIVATE);
        numberValue = sharedPreferences.getFloat(key, defaultValue);
    }

    public PreferenceDetail(@NonNull Context context, String title, String key, byte type, int defaultIndex, String[] spinnerLabels) {

        this.context = context;
        this.title = title;
        this.key = key;
        this.type = type;
        this.spinnerLabels = spinnerLabels;
        sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, Context.MODE_PRIVATE);
        labelIndex = sharedPreferences.getInt(key, defaultIndex);
    }

    public String getValue() {
        switch (type) {
            case TYPE_NUMBER:
                return Float.toString(numberValue);
            case TYPE_STRING:
                return spinnerLabels[labelIndex];
        }
        return null;
    }

    public int getLabelIndex() {
        return labelIndex;
    }

    public float getNumberValue() {
        return numberValue;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public byte getType() {
        return type;
    }

    public View getView() {
        switch (type) {
            case TYPE_NUMBER:
                return getNumberTypeView();
            case TYPE_STRING:
                return getStringTypeView();
        }
        return null;
    }

    private View getNumberTypeView() {
        Resources resources = context.getResources();

        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams((int) ConstantMethods.dpToFloat(resources, 48), (int) ConstantMethods.dpToFloat(resources, 48));

        Button decrement = new Button(context);
        decrement.setLayoutParams(buttonLayoutParams);
        decrement.setText("-");
        decrement.setBackground(resources.getDrawable(R.drawable.drawable_background_button));
        decrement.setTextColor(resources.getColor(android.R.color.white));

        final TextView textView = new TextView(context);
        textView.setLayoutParams(textViewLayoutParams);
        textView.setText(Float.toString(numberValue));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding((int) ConstantMethods.dpToFloat(resources, 16), 0, (int) ConstantMethods.dpToFloat(resources, 16), 0);

        Button increment = new Button(context);
        increment.setLayoutParams(buttonLayoutParams);
        increment.setText("+");
        increment.setBackground(resources.getDrawable(R.drawable.drawable_background_button));
        increment.setTextColor(resources.getColor(android.R.color.white));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            decrement.setElevation(ConstantMethods.dpToFloat(resources, 4));
            increment.setElevation(ConstantMethods.dpToFloat(resources, 4));
        }

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberValue > minValue) {
                    numberValue -= stepValue;
                    textView.setText(Float.toString(numberValue));
                }
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberValue < maxValue) {
                    numberValue += stepValue;
                    textView.setText(Float.toString(numberValue));
                }
            }
        });

        rootLayout.addView(decrement);
        rootLayout.addView(textView);
        rootLayout.addView(increment);

        return rootLayout;
    }

    private View getStringTypeView() {
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams spinnerLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Spinner spinner = new Spinner(context);
        spinner.setLayoutParams(spinnerLayoutParams);
        spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerLabels));
        spinner.setSelection(labelIndex);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                labelIndex = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rootLayout.addView(spinner);
        return rootLayout;
    }

}
