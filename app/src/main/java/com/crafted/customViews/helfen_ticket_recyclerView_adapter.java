package com.crafted.customViews;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.R;
import com.crafted.external.RetrofitClient;
import com.crafted.models.image_model;
import com.crafted.models.tag_model;
import com.crafted.models.ticket_info_model;
import com.crafted.profil;
import com.crafted.retrofit_interfaces.image_interface;
import com.crafted.ticket;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String rating = ticket_info.getUser().getRating()+"/5";


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



        //Ticket Bild
        if ( bild != null)
            Picasso.get().load(bild.getUrl()).into(holder.ivBild);
        else
            Picasso.get().load("https://ualr.edu/elearning/files/2020/10/No-Photo-Available.jpg").into(holder.ivBild);


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


        //Generate call to the RestAPI
        image_interface api = RetrofitClient.getRetrofitInstance().create(image_interface.class);
        Call<image_model> call = api.getImageById(RetrofitClient.getBearerToken(),ticket_info.getUser().getProfilePhotoId());
        call.enqueue(new Callback<image_model>() {


            @Override
            public void onResponse(Call<image_model> call, Response<image_model> response) {

                image_model image = response.body();

                Picasso.get().load(image.getUrl()).into(holder.iVuserpicture);



            }

            @Override
            public void onFailure(Call<image_model> call, Throwable t) {
                //Handle failure
                t.printStackTrace();
            }


        });


        holder.cvBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new intent
                Intent intent = new Intent(view.getContext(), ticket.class);

                intent.putExtra("id",ticket_info.getTicket().getId());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.ticket_info_modelList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTitel, tvRating, tvBeschreibung, tvTags;
        ImageView ivBild, iVuserpicture;
        CardView cvBody;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.helfen_card_Titel);
            tvBeschreibung = itemView.findViewById(R.id.helfen_card_Beschrebung);
            tvTags = itemView.findViewById(R.id.helfen_card_tags);
            tvName = itemView.findViewById(R.id.helfen_card_name);
            tvRating = itemView.findViewById(R.id.helfen_card_rating);

            ivBild = itemView.findViewById(R.id.helfen_card_Bild);
            iVuserpicture = itemView.findViewById(R.id.helfen_card_Profilbild);

            cvBody = itemView.findViewById(R.id.helfen_card_body);
        }
    }
}
