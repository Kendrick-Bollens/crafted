package com.crafted.customViews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.R;
import com.crafted.models.image_model;
import com.crafted.models.tag_model;
import com.crafted.models.ticket_info_model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ticket_image_recyclerView_adapter extends RecyclerView.Adapter<ticket_image_recyclerView_adapter.ImageViewHolder> {

    Context context;
    List<image_model> image_modelList;

    public ticket_image_recyclerView_adapter(Context context, List<image_model> image_modelList) {
        this.context = context;
        this.image_modelList = image_modelList;
    }


    @NonNull
    @Override
    public ticket_image_recyclerView_adapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.ticket_recycler_view_image, parent, false);
        return new ticket_image_recyclerView_adapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ticket_image_recyclerView_adapter.ImageViewHolder holder, int position) {

        image_model image = this.image_modelList.get(position);

        System.out.println("Here"+ image.getUrl());

        if(image != null)
            Picasso.get().load(image.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.image_modelList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ticket_card_Bild);

        }
    }
}
