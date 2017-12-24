package com.nagpal.shivam.instamath.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.EntryValue;

import java.util.ArrayList;

public class InterpolationActivity extends AppCompatActivity {
    private int firstEntryIndex = 1;
    private int nextEntryIndex = firstEntryIndex;
    private View.OnClickListener removeViewClickListener;
    private TextView.OnEditorActionListener et1ActionListener;
    private TextView.OnEditorActionListener et2ActionListener;
    private Button btnAddNewEntry;
    private Button btnSubmit;
    private TextView tvResult;
    private Resources resources;
    private LinearLayout llRoot;
    private ArrayList<EntryValue> alEntryValue;
    private EditText etHypothesis;
    private Double hypothesis;

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
        setContentView(R.layout.activity_interpolation);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        resources = getResources();
        alEntryValue = new ArrayList<>();
        llRoot = (LinearLayout) findViewById(R.id.root_layout_main_activity);
        llRoot.setSaveEnabled(true);
        etHypothesis = (EditText) findViewById(R.id.hypothesis_edit_text);
        tvResult = (TextView) findViewById(R.id.result_text_view);

        removeViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextEntryIndex--;
                View parent = (View) view.getParent();
                ((ViewManager) parent.getParent()).removeView(parent);
            }
        };

        et1ActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    LinearLayout entryParent = (LinearLayout) v.getParent();
                    int index = entryParent.indexOfChild(v);
                    entryParent.getChildAt(index + 2).requestFocus();
                    return true;
                }
                return false;
            }
        };

        et2ActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    LinearLayout entryParent = (LinearLayout) v.getParent();
                    LinearLayout rootView = (LinearLayout) entryParent.getParent();
                    int index = rootView.indexOfChild(entryParent);
                    index++;
                    Log.v("sagfasdf", "" + index + getEntryCount());
                    if (index <= getEntryCount()) {
                        ((LinearLayout) rootView.getChildAt(index)).getChildAt(0).requestFocus();
                        return true;
                    }
                }
                return false;
            }
        };

        btnAddNewEntry = (Button) findViewById(R.id.add_new_entry_button);
        btnAddNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEntry();
            }
        });

        btnSubmit = (Button) findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fetchData()) {
                    String message = "Interpolation : " + calculateInterpolation();
                    tvResult.setText(message);
                }
                Log.d("Debug", Integer.toString(alEntryValue.size()));
            }
        });

        btnAddNewEntry.performClick();
        edtTextRequestFocus();

    }

    private void edtTextRequestFocus() {
        LinearLayout llFirstEntry = (LinearLayout) llRoot.getChildAt(firstEntryIndex);
        ((EditText) llFirstEntry.getChildAt(0)).requestFocus();
    }

    private void addNewEntry() {
        LinearLayout llEntry = new LinearLayout(InterpolationActivity.this);
        llEntry.setOrientation(LinearLayout.HORIZONTAL);
        llEntry.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) resources.getDimension(R.dimen.ENTRY_LAYOUT_HEIGHT)));
        llEntry.setGravity(Gravity.CENTER_VERTICAL);
        llEntry.setSaveEnabled(true);
        EditText et1 = new EditText(InterpolationActivity.this);
        EditText et2 = new EditText(InterpolationActivity.this);
        LinearLayout.LayoutParams editTextLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        et1.setLayoutParams(editTextLayoutParams);
        et1.setSaveEnabled(true);
        et1.setGravity(Gravity.CENTER);
//        et1.requestFocus();
        et2.setLayoutParams(editTextLayoutParams);
        et2.setSaveEnabled(true);
        et2.setGravity(Gravity.CENTER);
        et1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        et2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        et1.setOnEditorActionListener(et1ActionListener);
        et2.setOnEditorActionListener(et2ActionListener);
        View view = new View(InterpolationActivity.this);
        view.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, this.getResources().getDisplayMetrics()), ViewGroup.LayoutParams.MATCH_PARENT));
        view.setBackgroundResource(android.R.color.black);
        Button btnRemove = new Button(InterpolationActivity.this);
        btnRemove.setText("-");
        btnRemove.setTextColor(resources.getColor(android.R.color.white));
        btnRemove.setBackground(resources.getDrawable(R.drawable.drawable_button_shape));
        btnRemove.setOnClickListener(removeViewClickListener);
        btnRemove.setLayoutParams(new LinearLayout.LayoutParams((int) resources.getDimension(R.dimen.ENTRY_BUTTON_WIDTH), (int) resources.getDimension(R.dimen.ENTRY_BUTTON_HEIGHT)));
        llEntry.addView(et1);
        llEntry.addView(view);
        llEntry.addView(et2);

        llEntry.addView(btnRemove);
        llRoot.addView(llEntry, nextEntryIndex);
        nextEntryIndex++;
    }

    private int getEntryCount() {
        return nextEntryIndex - firstEntryIndex;
    }

    private boolean fetchData() {

        alEntryValue.clear();
        tvResult.setText(null);

        int entryCount = getEntryCount();
        if (entryCount == 0) {
            Toast.makeText(InterpolationActivity.this, "No entry Cell", Toast.LENGTH_SHORT).show();
            return false;
        }

        double valX;
        double valY;
        for (int i = 0; i < entryCount; i++) {
            LinearLayout entry = (LinearLayout) llRoot.getChildAt(i + firstEntryIndex);
            EditText editText1 = (EditText) entry.getChildAt(0);
            EditText editText2 = (EditText) entry.getChildAt(2);

            try {
                valX = Double.parseDouble(editText1.getText().toString());
                valY = Double.parseDouble(editText2.getText().toString());
                alEntryValue.add(new EntryValue(valX, valY));
            } catch (NumberFormatException e) {
                Log.e("Interpolation Activity", e.toString());
                Toast.makeText(InterpolationActivity.this, "Enter value in all cells", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        try {
            hypothesis = Double.parseDouble(etHypothesis.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("Interpolation Activity", e.toString());
            Toast.makeText(InterpolationActivity.this, "Enter hypothesis", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private double calculateInterpolation() {

        int alEntryValueSize = alEntryValue.size();
        Log.v("Size", "" + alEntryValue);
        int termIndex;
        double term = 0;


        for (termIndex = 0; termIndex < alEntryValueSize; termIndex++) {

            double numerator = 1;
            double denominator = 1;

            for (int index = 0; index < alEntryValueSize; index++) {
                if (termIndex == index) {
                    continue;
                }
                numerator *= hypothesis - alEntryValue.get(index).getX();
                denominator *= alEntryValue.get(termIndex).getX() - alEntryValue.get(index).getX();
                Log.v("Numerator", "" + numerator);
                Log.v("Denominator", "" + denominator);
            }
            term += (numerator * alEntryValue.get(termIndex).getY()) / denominator;
        }
        return term;
    }

}