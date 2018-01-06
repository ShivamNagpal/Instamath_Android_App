package com.nagpal.shivam.instamath.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nagpal.shivam.instamath.R;

import java.util.ArrayList;

public class HexadecimalValueAdapter extends RecyclerView.Adapter<HexadecimalValueAdapter.ViewHolder> {

    private ArrayList<String> stringArrayList;
    private Context context;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                ((EditText) (((Activity) context).getCurrentFocus())).append(((Button) view).getText());

            } catch (ClassCastException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    };

    public HexadecimalValueAdapter(Context context, ArrayList<String> stringArrayList) {
        this.context = context;
        this.stringArrayList = stringArrayList;
    }

    @Override
    public HexadecimalValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button btn = (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item_activity_numberconversion, parent, false);
        ViewHolder vh = new ViewHolder(btn);
        return vh;
    }

    @Override
    public void onBindViewHolder(HexadecimalValueAdapter.ViewHolder holder, int position) {
        holder.mButton.setText(stringArrayList.get(position));
        holder.mButton.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mButton;

        public ViewHolder(Button itemView) {
            super(itemView);
            mButton = itemView;
        }
    }


}
