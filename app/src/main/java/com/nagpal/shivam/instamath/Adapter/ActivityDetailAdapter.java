package com.nagpal.shivam.instamath.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.ActivityDetail;

import java.util.ArrayList;

public class ActivityDetailAdapter extends RecyclerView.Adapter<ActivityDetailAdapter.ActivityDetailViewHolder> {

    private final Context mContext;
    private final ArrayList<ActivityDetail> mActivityDetailArrayList;

    public ActivityDetailAdapter(@NonNull Context context, @NonNull ArrayList<ActivityDetail> activityDetailArrayList) {
        this.mContext = context;
        this.mActivityDetailArrayList = activityDetailArrayList;
    }

    @NonNull
    @Override
    public ActivityDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_main_list_item, viewGroup, false);
        return new ActivityDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityDetailViewHolder activityDetailViewHolder, int i) {
        activityDetailViewHolder.activityName.setText(mActivityDetailArrayList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mActivityDetailArrayList.size();
    }

    class ActivityDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView activityName;

        public ActivityDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.text_view_list_activity_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, mActivityDetailArrayList.get(getAdapterPosition()).getActivityClass());
            mContext.startActivity(intent);
        }
    }
}
