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

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nagpal.shivam.instamath.Adapter.ActivityDetailAdapter;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.ActivityDetail;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ActivityDetail> mActivityDetailArrayList;
    private AutoCompleteTextView actvSearch;
    private Toolbar toolbar;
    private Boolean isSearchOpen = false;
    private Float defaultToolbarElevation;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isSearchOpen) {
            getMenuInflater().inflate(R.menu.main_search_open, menu);
        } else {
            getMenuInflater().inflate(R.menu.main_search_close, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.search_open_menu_item:
                openSearch();
                return true;

            case R.id.search_clear_menu_item:
                actvSearch.setText(null);
                return true;

            case R.id.preferences_menu_item:
                startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_activity_toolbar_default);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            defaultToolbarElevation = toolbar.getElevation();
        }
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view_activity);

        mActivityDetailArrayList = new ArrayList<>();

        mActivityDetailArrayList.add(new ActivityDetail("Basic Calculator", BasicCalculatorActivity.class));
        mActivityDetailArrayList.add(new ActivityDetail("Scientific Calculator", ScientificCalculatorActivity.class));
        mActivityDetailArrayList.add(new ActivityDetail("Interest", InterestActivity.class));
        mActivityDetailArrayList.add(new ActivityDetail("Interpolation", InterpolationActivity.class));
        mActivityDetailArrayList.add(new ActivityDetail("Factorization", FactorizationActivity.class));
        mActivityDetailArrayList.add(new ActivityDetail("Number Conversion", NumberConversionActivity.class));

        ActivityDetailAdapter activityDetailAdapter = new ActivityDetailAdapter(this, mActivityDetailArrayList);

        ArrayList<String> activityNamesArrayList = new ArrayList<>();

        for (ActivityDetail activityDetail : mActivityDetailArrayList) {
            activityNamesArrayList.add(activityDetail.getName());
        }

        actvSearch = new AutoCompleteTextView(this);
        actvSearch.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        actvSearch.setLayoutParams(new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        actvSearch.setSingleLine();
        actvSearch.setHintTextColor(ContextCompat.getColor(this, android.R.color.white));
        actvSearch.setHint("Search");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, activityNamesArrayList);
        actvSearch.setAdapter(arrayAdapter);
        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = parent.getAdapter().getItem(position).toString();

                for (ActivityDetail aI : mActivityDetailArrayList) {
                    if (aI.getName().equals(selectedItem)) {
                        startActivity(new Intent(MainActivity.this, aI.getActivityClass()));
                    }
                }
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(activityDetailAdapter);

    }

    private void openSearch() {
        isSearchOpen = true;
        toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.searchOpenedToolbar));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(5 * defaultToolbarElevation);
        }
        actvSearch.requestFocus();
        invalidateOptionsMenu();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.addView(actvSearch);
    }

    private void closeSearch() {
        isSearchOpen = false;
        toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        actvSearch.setText(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(defaultToolbarElevation);
        }
        toolbar.removeView(actvSearch);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        if (isSearchOpen) {
            closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
