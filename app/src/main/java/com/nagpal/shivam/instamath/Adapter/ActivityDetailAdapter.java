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