/*
 * Copyright 2018 Shivam Nagpal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagpal.shivam.instamath.Activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nagpal.shivam.instamath.Adapter.PreferenceDetailAdapter;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.Constants;
import com.nagpal.shivam.instamath.Utils.PreferenceDetail;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity {
    public static final String PREFERENCES_FIX_KEY = "preferences_fix_key";
    public static final float PREFERENCES_FIX_DEFAULT_VALUE = 9;

    public static final String PREFERENCES_ANGLE_UNIT_KEY = "preferences_angle_unit_key";
    public static final String PREFERENCES_ANGLE_UNIT_DEGREE = "Degree";
    public static final String PREFERENCES_ANGLE_UNIT_RADIAN = "Radian";
    public static final int PREFERENCES_ANGLE_UNIT_DEFAULT_VALUE = 1;
    public static final String[] PREFERENCE_ANGLE_UNITS = {PREFERENCES_ANGLE_UNIT_DEGREE, PREFERENCES_ANGLE_UNIT_RADIAN};
    private ArrayList<PreferenceDetail> preferenceDetailArrayList;
    private SharedPreferences sharedPreferences;

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
        setContentView(R.layout.activity_preferences_old);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ListView listView = findViewById(R.id.list_view_activity_preferences);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, MODE_PRIVATE);

        preferenceDetailArrayList = new ArrayList<>();

        preferenceDetailArrayList.add(new PreferenceDetail(PreferencesActivity.this, "Fix", PREFERENCES_FIX_KEY, PreferenceDetail.TYPE_NUMBER, PREFERENCES_FIX_DEFAULT_VALUE, 1, 14, 1));
        preferenceDetailArrayList.add(new PreferenceDetail(PreferencesActivity.this, "Angle Units", PREFERENCES_ANGLE_UNIT_KEY, PreferenceDetail.TYPE_STRING, PREFERENCES_ANGLE_UNIT_DEFAULT_VALUE, PREFERENCE_ANGLE_UNITS));


        PreferenceDetailAdapter preferenceDetailAdapter = new PreferenceDetailAdapter(PreferencesActivity.this, preferenceDetailArrayList);
        listView.setAdapter(preferenceDetailAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final PreferenceDetail currentPreferenceDetail = preferenceDetailArrayList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesActivity.this);
                Spannable title = new SpannableString(currentPreferenceDetail.getTitle());
                title.setSpan(new ForegroundColorSpan(ContextCompat.getColor(PreferencesActivity.this, R.color.colorPrimary)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setTitle(title);
                builder.setView(currentPreferenceDetail.getView());
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        switch (currentPreferenceDetail.getType()) {
                            case PreferenceDetail.TYPE_NUMBER:
                                editor.putFloat(currentPreferenceDetail.getKey(), currentPreferenceDetail.getNumberValue());
                                break;
                            case PreferenceDetail.TYPE_STRING:
                                editor.putInt(currentPreferenceDetail.getKey(), currentPreferenceDetail.getLabelIndex());
                                break;
                        }
                        editor.apply();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

    }
}
