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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nagpal.shivam.instamath.Adapter.PreferenceDetailAdapter;
import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.Constants;
import com.nagpal.shivam.instamath.Utils.PreferenceDetail;

import java.util.ArrayList;

public class OldPreferencesActivity extends AppCompatActivity {
    public static final String PREFERENCES_FIX_KEY = "preferences_fix_key";
    public static final float PREFERENCES_FIX_DEFAULT_VALUE = 9;
    private ArrayList<PreferenceDetail> preferenceDetailArrayList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_old);

        ListView listView = findViewById(R.id.list_view_activity_preferences);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, MODE_PRIVATE);

        preferenceDetailArrayList = new ArrayList<>();

        preferenceDetailArrayList.add(new PreferenceDetail(OldPreferencesActivity.this, "Fix", PREFERENCES_FIX_KEY, PreferenceDetail.EDIT_TEXT_TYPE, PREFERENCES_FIX_DEFAULT_VALUE, 1, 9, 1));


        PreferenceDetailAdapter preferenceDetailAdapter = new PreferenceDetailAdapter(OldPreferencesActivity.this, preferenceDetailArrayList);
        listView.setAdapter(preferenceDetailAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final PreferenceDetail currentPreferenceDetail = preferenceDetailArrayList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(OldPreferencesActivity.this);
                Spannable title = new SpannableString(currentPreferenceDetail.getName());
                title.setSpan(new ForegroundColorSpan(ContextCompat.getColor(OldPreferencesActivity.this, R.color.colorPrimary)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setTitle(title);
                builder.setMessage("");
                builder.setView(currentPreferenceDetail.getView());
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putFloat(currentPreferenceDetail.getKey(), currentPreferenceDetail.getValue());
                        editor.apply();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

    }
}
