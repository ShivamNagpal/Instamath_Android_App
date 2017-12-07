package com.nagpal.shivam.instamath.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nagpal.shivam.instamath.R;

public class PreferenceDetail {
    public static final int EDIT_TEXT_TYPE = 1;

    private Context context;
    private String name;
    private String key;
    private int type;
    private float value;
    private float minVal;
    private float maxVal;
    private float stepVal;

    public PreferenceDetail(@NonNull Context context, String name, String key, int type, float defaultVal, float minVal, float maxVal, float stepVal) {
        this.context = context;
        this.name = name;
        this.key = key;
        this.type = type;
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, Context.MODE_PRIVATE);
        this.value = sharedPreferences.getFloat(key, defaultVal);
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.stepVal = stepVal;
    }

    public String getName() {
        return name;
    }

    public View getView() {
        switch (type) {
            case EDIT_TEXT_TYPE:
                return getEditTextView();
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public float getValue() {
        return value;
    }

    View getEditTextView() {
        Resources resources = context.getResources();

        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams((int) ConstantMethods.dpToFloat(resources, 48), (int) ConstantMethods.dpToFloat(resources, 48));

        Button decrement = new Button(context);
        decrement.setLayoutParams(buttonLayoutParams);
        decrement.setText("-");
        decrement.setBackground(resources.getDrawable(R.drawable.drawable_button_shape));
        decrement.setTextColor(resources.getColor(android.R.color.white));

        final TextView textView = new TextView(context);
        textView.setLayoutParams(textViewLayoutParams);
        textView.setText(Float.toString(value));
        textView.setPadding(25, 0, 25, 0);

        Button increment = new Button(context);
        increment.setLayoutParams(buttonLayoutParams);
        increment.setText("+");
        increment.setBackground(resources.getDrawable(R.drawable.drawable_button_shape));
        increment.setTextColor(resources.getColor(android.R.color.white));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            decrement.setElevation(ConstantMethods.dpToFloat(resources, 4));
            increment.setElevation(ConstantMethods.dpToFloat(resources, 4));
        }

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value > minVal) {
                    value -= stepVal;
                    textView.setText(Float.toString(value));
                }
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value < maxVal) {
                    value += stepVal;
                    textView.setText(Float.toString(value));
                }
            }
        });

        rootLayout.addView(decrement);
        rootLayout.addView(textView);
        rootLayout.addView(increment);

        return rootLayout;
    }
    /*View getEditTextView() {
        View view = View.inflate(context, R.layout.layout_edit_preference_view, null);
        return view;
    }*/

}
