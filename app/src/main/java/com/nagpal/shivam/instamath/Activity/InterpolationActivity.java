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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.TwoColumnList;

import java.util.ArrayList;

public class InterpolationActivity extends AppCompatActivity {
    private static final String INTERPOLATION = "Interpolation";
    private static final String INVERSE_INTERPOLATION = "Inverse Interpolation";
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
    private TwoColumnList<Double> inputTwoColumnList;
    private EditText etHypothesis;
    private Double hypothesis;
    private Spinner interpolationSpinner;
    private TextView hypothesisTV;

    // TODO: Implement Inverse Interpolation, Also change the pair data storage to Subset Storage.
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
        inputTwoColumnList = new TwoColumnList<>();
        llRoot = (LinearLayout) findViewById(R.id.root_layout_main_activity);
        llRoot.setSaveEnabled(true);
        etHypothesis = (EditText) findViewById(R.id.hypothesis_edit_text);
        tvResult = (TextView) findViewById(R.id.result_text_view);
        interpolationSpinner = findViewById(R.id.interpolation_spinner);
        hypothesisTV = findViewById(R.id.hypothesis_text_view);

        interpolationSpinner.setAdapter(new ArrayAdapter<String>(InterpolationActivity.this, android.R.layout.simple_spinner_dropdown_item, new String[]{INTERPOLATION, INVERSE_INTERPOLATION}));

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

        interpolationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getSelectedItem().toString()) {
                    case INTERPOLATION:
                        hypothesisTV.setText("x :");
                        break;
                    case INVERSE_INTERPOLATION:
                        hypothesisTV.setText("y :");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit = (Button) findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fetchData()) {
                    String message = null;
                    switch (interpolationSpinner.getSelectedItem().toString()) {
                        case INTERPOLATION:
                            message = "Interpolation : " + calculateInterpolation(inputTwoColumnList.getXArrayList(), inputTwoColumnList.getYArrayList(), inputTwoColumnList.getSize());
                            break;

                        case INVERSE_INTERPOLATION:
                            message = "Inverse Interpolation : " + calculateInterpolation(inputTwoColumnList.getYArrayList(), inputTwoColumnList.getXArrayList(), inputTwoColumnList.getSize());
                            break;
                    }

                    tvResult.setText(message);
                }
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

        inputTwoColumnList.clear();
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
                inputTwoColumnList.add(valX, valY);
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

    /*private double calculateInterpolation() {

        int inputTwoColumnListSize = inputTwoColumnList.getSize();
        int termIndex;
        double term = 0;


        for (termIndex = 0; termIndex < inputTwoColumnListSize; termIndex++) {

            double numerator = 1;
            double denominator = 1;

            for (int index = 0; index < inputTwoColumnListSize; index++) {
                if (termIndex == index) {
                    continue;
                }
                numerator *= hypothesis - inputTwoColumnList.getX(index);

                denominator *= inputTwoColumnList.getX(termIndex) - inputTwoColumnList.getX(index);
                Log.v("Numerator", "" + numerator);
                Log.v("Denominator", "" + denominator);
            }
            term += (numerator * inputTwoColumnList.getY(termIndex)) / denominator;
        }
        return term;
    }*/

    private double calculateInterpolation(ArrayList<Double> AList, ArrayList<Double> BList, int size) {
        int termIndex;
        double term = 0;


        for (termIndex = 0; termIndex < size; termIndex++) {

            double numerator = 1;
            double denominator = 1;

            for (int index = 0; index < size; index++) {
                if (termIndex == index) {
                    continue;
                }
                numerator *= hypothesis - AList.get(index);

                denominator *= AList.get(termIndex) - AList.get(index);
                Log.v("Numerator", "" + numerator);
                Log.v("Denominator", "" + denominator);
            }
            term += (numerator * BList.get(termIndex)) / denominator;
        }
        return term;
    }

}