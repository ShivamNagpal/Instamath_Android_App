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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nagpal.shivam.instamath.R;
import com.nagpal.shivam.instamath.Utils.ExpressionToken;

import java.util.ArrayList;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.BottomSheetAdapterViewHolder> {

    private ArrayList<ExpressionToken> buttonValues;
    private ItemClickHandler itemClickHandler;

    public BottomSheetAdapter(ArrayList<ExpressionToken> buttonValues) {
        this.buttonValues = buttonValues;
        notifyDataSetChanged();
    }

    public void setItemClickHandler(ItemClickHandler itemClickHandler) {
        this.itemClickHandler = itemClickHandler;
    }

    @NonNull
    @Override
    public BottomSheetAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_scientific_bottom_sheet_button, parent, false);
        return new BottomSheetAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapterViewHolder holder, int position) {
        holder.button.setText(buttonValues.get(position).getToken());
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        if (buttonValues == null) {
            return 0;
        }
        return buttonValues.size();
    }

    public interface ItemClickHandler {
        void onBottomSheetAdapterItemClick(String token, boolean isFunction);
    }

    class BottomSheetAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;
        int position;

        BottomSheetAdapterViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView;
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickHandler != null) {
                itemClickHandler.onBottomSheetAdapterItemClick(((Button) view).getText().toString(), buttonValues.get(position).isFunction());
            }
        }
    }
}
