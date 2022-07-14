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

import java.util.ArrayList;
import java.util.List;

public class helfen_ticket_recyclerView_adapter extends RecyclerView.Adapter<helfen_ticket_recyclerView_adapter.TicketViewHolder> {

    Context context;
    List<ticket_info_model> ticket_info_modelList;
    List<tag_model> active_tags_List = new ArrayList<tag_model>();

    public helfen_ticket_recyclerView_adapter(Context context, List<ticket_info_model> ticket_info_modelList) {
        this.context = context;
        this.ticket_info_modelList = ticket_info_modelList;
    }

    public helfen_ticket_recyclerView_adapter(Context context, List<ticket_info_model> ticket_info_modelList, List<tag_model> active_tag_List) {
        this.context = context;
        this.ticket_info_modelList = ticket_info_modelList;
        this.active_tags_List = active_tag_List;
    }

    @NonNull
    @Override
    public helfen_ticket_recyclerView_adapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.helfen_recycler_view_card, parent, false);
        return new helfen_ticket_recyclerView_adapter.TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull helfen_ticket_recyclerView_adapter.TicketViewHolder holder, int position) {

        ticket_info_model ticket_info = this.ticket_info_modelList.get(position);

        List<tag_model> tagList = ticket_info.getTags();

        String title = "";

        String description = "";

        if(ticket_info.getTicket()!= null){
            title = ticket_info.getTicket().getTitle();
            description = ticket_info.getTicket().getDescription();
        }

        String username = "";

        String rating = "";


        if(ticket_info.getUser() != null) {
           username = ticket_info.getUser().getUsername();
        }

        image_model userphoto = null;

        image_model bild = null;


        if(ticket_info.getImages() != null && !ticket_info.getImages().isEmpty())
           bild = ticket_info.getImages().get(0);


        holder.tvTitel.setText(title);

        holder.tvBeschreibung.setText(description);


        holder.tvName.setText(username);


        //Rating
        if (rating == "")
            holder.tvRating.setVisibility(View.GONE);
        else
            holder.tvRating.setText(rating);

        //Beschreibung
        if (description == "")
            holder.tvBeschreibung.setVisibility(View.GONE);
        else
            holder.tvBeschreibung.setText(description);

        //ProfilePhoto
        if (userphoto != null)
            Picasso.get().load(userphoto.getUrl()).into(holder.iVuserpicture);

        //Ticket Bild
        if ( bild != null)
            Picasso.get().load(bild.getUrl()).into(holder.ivBild);


        //Tags
        String taglistString = "";
        for (int i = 0; i < tagList.size(); i++) {
            if (i == 0)
                if (this.active_tags_List.contains(tagList.get(i)))
                    taglistString = "<b>" +tagList.get(i).toString()+"</b>";
                else
                    taglistString = tagList.get(i).toString();

            else
            if (this.active_tags_List.contains(tagList.get(i)))
                taglistString += ", <b>" + tagList.get(i).toString() + "</b>";
            else taglistString += ", " + tagList.get(i).toString();

        }
        if (taglistString == "")
            holder.tvTags.setVisibility(View.GONE);
        else
            holder.tvTags.setText(Html.fromHtml(taglistString));


    }

    @Override
    public int getItemCount() {
        return this.ticket_info_modelList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTitel, tvRating, tvBeschreibung, tvTags;
        ImageView ivBild, iVuserpicture;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.helfen_card_Titel);
            tvBeschreibung = itemView.findViewById(R.id.helfen_card_Beschrebung);
            tvTags = itemView.findViewById(R.id.helfen_card_tags);
            tvName = itemView.findViewById(R.id.helfen_card_name);
            tvRating = itemView.findViewById(R.id.helfen_card_rating);

            ivBild = itemView.findViewById(R.id.helfen_card_Bild);
            iVuserpicture = itemView.findViewById(R.id.helfen_card_Profilbild);
        }
    }
}
