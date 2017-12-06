package com.nagpal.shivam.instamath.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreferenceDetail {
    public static final int EDIT_TEXT_TYPE = 1;

    private String name;
    private int type;
    private Context context;
    private float value;
    private float minVal;
    private float maxVal;
    private float stepVal;

    public PreferenceDetail(Context context, String name, int type, float currentVal, float minVal, float maxVal, float stepVal) {
        this.context = context;
        this.name = name;
        this.type = type;
        this.value = currentVal;
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

    public float getValue() {
        return value;
    }

    View getEditTextView() {
        LinearLayout rootLayout = new LinearLayout(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);

        Button decrement = new Button(context);
        decrement.setLayoutParams(layoutParams);
        decrement.setText("-");

        final TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(Float.toString(value));


        Button increment = new Button(context);
        increment.setLayoutParams(layoutParams);
        increment.setText("+");

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
