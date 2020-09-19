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

package com.nagpal.shivam.instamath.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
