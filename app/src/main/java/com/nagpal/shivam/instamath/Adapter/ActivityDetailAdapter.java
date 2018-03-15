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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.ActivityDetail;

import java.util.ArrayList;

public class ActivityDetailAdapter extends ArrayAdapter<ActivityDetail> {
    public ActivityDetailAdapter(@NonNull Context context, ArrayList<ActivityDetail> activityDetailArrayList) {
        super(context, 0, activityDetailArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_main_list_item, parent, false);
        }
        ActivityDetail currentActivityDetail = getItem(position);
        TextView txtViewActivityName = (TextView) listViewItem.findViewById(R.id.text_view_list_activity_name);
        if (currentActivityDetail != null) {
            txtViewActivityName.setText(currentActivityDetail.getName());
        }
        return listViewItem;
    }
}