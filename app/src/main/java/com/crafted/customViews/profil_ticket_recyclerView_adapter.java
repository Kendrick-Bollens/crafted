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
import com.crafted.models.tag_model;
import com.crafted.models.ticket_info_model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class profil_ticket_recyclerView_adapter extends RecyclerView.Adapter<profil_ticket_recyclerView_adapter.TicketViewHolder> {

    Context context;
    List<ticket_info_model> ticket_info_modelList;

    public profil_ticket_recyclerView_adapter(Context context, List<ticket_info_model> ticket_info_modelList) {
        this.context = context;
        this.ticket_info_modelList = ticket_info_modelList;
    }


    @NonNull
    @Override
    public profil_ticket_recyclerView_adapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.profil_recycler_view_ticket, parent, false);
        return new profil_ticket_recyclerView_adapter.TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull profil_ticket_recyclerView_adapter.TicketViewHolder holder, int position) {

        ticket_info_model ticket_info = this.ticket_info_modelList.get(position);

        //Set Title
        String title = ticket_info.getTicket().getTitle();

        holder.tvTitel.setText(title);


        //set Beschreibung
        String beschreibung = ticket_info.getTicket().getDescription();

        holder.tvBeschreibung.setText(beschreibung);


        //set Tags
        List<tag_model> tags = ticket_info.getTags();

        String taglistString = "";
        for (int i = 0; i < tags.size(); i++) {
            if (i == 0)
                taglistString = tags.get(i).toString();
            else
                taglistString += ", " + tags.get(i).toString();

        }
        if (taglistString == "")
            holder.tvTags.setVisibility(View.GONE);
        else
            holder.tvTags.setText(Html.fromHtml(taglistString));



        if(ticket_info.getImages() != null && !ticket_info.getImages().isEmpty())
            Picasso.get().load(ticket_info.getImages().get(0).getUrl()).into(holder.bild);
        else
            Picasso.get().load("https://ualr.edu/elearning/files/2020/10/No-Photo-Available.jpg").into(holder.bild);
    }

    @Override
    public int getItemCount() {
        return this.ticket_info_modelList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitel, tvBeschreibung, tvTags;
        ImageView bild;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.ticket_card_Titel);
            tvBeschreibung = itemView.findViewById(R.id.ticket_card_Beschreibung);
            tvTags = itemView.findViewById(R.id.ticket_card_tags);
            bild = itemView.findViewById(R.id.ticket_card_Bild);

        }
    }
}
