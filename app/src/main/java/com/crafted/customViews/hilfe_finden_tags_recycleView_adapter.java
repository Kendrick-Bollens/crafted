package com.crafted.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.crafted.R;
import com.crafted.models.tag_model;

import java.util.List;


public class hilfe_finden_tags_recycleView_adapter extends RecyclerView.Adapter<hilfe_finden_tags_recycleView_adapter.TagViewHolder> {

    Context context;
    List<tag_model> tagList;

    public hilfe_finden_tags_recycleView_adapter(Context context, List<tag_model> tagList) {
        this.context = context;
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.hilfe_finden_recycler_view_tags,parent,false);
        return new hilfe_finden_tags_recycleView_adapter.TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.tgTag.setText(this.tagList.get(position).toString());
        holder.tgTag.setTextOn(this.tagList.get(position).toString());
        holder.tgTag.setTextOff(this.tagList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return this.tagList.size();
    }

    public static class TagViewHolder extends RecyclerView.ViewHolder{

        ToggleButton tgTag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);

            tgTag = itemView.findViewById(R.id.hilfe_finden_tag_name);

        }
    }
}
