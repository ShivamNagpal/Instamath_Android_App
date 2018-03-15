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
        Button btn = (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_number_conversion_grid_item, parent, false);
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
