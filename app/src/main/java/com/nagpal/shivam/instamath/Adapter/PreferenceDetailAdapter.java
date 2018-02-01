package com.nagpal.shivam.instamath.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.Constants;
import com.nagpal.shivam.instamath.Utils.PreferenceDetail;

import java.util.ArrayList;

public class PreferenceDetailAdapter extends ArrayAdapter<PreferenceDetail> {
    public PreferenceDetailAdapter(@NonNull Context context, ArrayList<PreferenceDetail> preferenceDetailArrayList) {
        super(context, 0, preferenceDetailArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_preferences_list_item, parent, false);
        }
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.PREFERENCES_ACTIVITY_KEY, Context.MODE_PRIVATE);
        final PreferenceDetail currentPreferenceDetail = getItem(position);
//        ConstraintLayout rootLayout = listItemView.findViewById(R.id.root_view_preference_list_item);
        TextView nameTextView = listItemView.findViewById(R.id.text_view_preference_name);
        final TextView valueTextView = listItemView.findViewById(R.id.text_view_preference_value);

        if (currentPreferenceDetail != null) {
//            rootLayout.setOnClickListener(currentOldPreferenceDetail.getOnClickListener());
            nameTextView.setText(currentPreferenceDetail.getTitle());
            valueTextView.setText(currentPreferenceDetail.getValue());
            sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    valueTextView.setText(currentPreferenceDetail.getValue());
                }
            });
        }

        return listItemView;
    }
}
