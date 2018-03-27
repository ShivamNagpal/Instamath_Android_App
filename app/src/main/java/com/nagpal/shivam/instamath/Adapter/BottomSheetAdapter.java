package com.nagpal.shivam.instamath.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nagpal.shivam.instamath.R;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.BottomSheetAdapterViewHolder> {

    private String[] buttonValues;
    private ClickHandler clickHandler;

    public BottomSheetAdapter(String[] buttonValues, ClickHandler clickHandler) {
        this.buttonValues = buttonValues;
        notifyDataSetChanged();
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public BottomSheetAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_scientific_bottom_sheet_button, parent, false);
        return new BottomSheetAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapterViewHolder holder, int position) {
        holder.button.setText(buttonValues[position]);
    }

    @Override
    public int getItemCount() {
        if (buttonValues == null) {
            return 0;
        }
        return buttonValues.length;
    }

    public interface ClickHandler {
        void onBottomSheetAdapterItemClick(String s);
    }

    class BottomSheetAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;

        BottomSheetAdapterViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView;
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickHandler.onBottomSheetAdapterItemClick(((Button) view).getText().toString());
        }
    }
}
