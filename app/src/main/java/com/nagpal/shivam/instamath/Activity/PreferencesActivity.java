package com.nagpal.shivam.instamath.Activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nagpal.shivam.instamath.Adapter.PreferenceDetailAdapter;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.PreferenceDetail;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity {
    private ArrayList<PreferenceDetail> preferenceDetailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ListView listView = findViewById(R.id.list_view_activity_preferences);

        preferenceDetailArrayList = new ArrayList<>();

        preferenceDetailArrayList.add(new PreferenceDetail(PreferencesActivity.this, "Test", PreferenceDetail.EDIT_TEXT_TYPE, 5, 1, 9, 1));


        PreferenceDetailAdapter preferenceDetailAdapter = new PreferenceDetailAdapter(PreferencesActivity.this, preferenceDetailArrayList);
        listView.setAdapter(preferenceDetailAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final PreferenceDetail preferenceDetail = preferenceDetailArrayList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesActivity.this);
                builder.setTitle(preferenceDetail.getName());
                builder.setView(preferenceDetail.getView());
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putFloat(preferenceDetail.getName(), preferenceDetail.getValue());
                        editor.apply();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

    }
}
