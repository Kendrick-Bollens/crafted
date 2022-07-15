package com.crafted.customViews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.mein_profil;
import com.crafted.profil;

import com.crafted.R;
import com.crafted.hilfe_finden;
import com.crafted.models.image_model;
import com.crafted.models.tag_model;
import com.crafted.models.user_profile_model;
import com.crafted.ticket_erstellen2;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class hilfe_finden_profil_recyclerView_adapter extends RecyclerView.Adapter<hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder> {

    Context context;
    List<user_profile_model> profilList;
    List<tag_model> active_tags_List = new ArrayList<tag_model>();

    public hilfe_finden_profil_recyclerView_adapter(Context context, List<user_profile_model> profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    public hilfe_finden_profil_recyclerView_adapter(Context context, List<user_profile_model> profilList, List<tag_model> active_tag_List) {
        this.context = context;
        this.profilList = profilList;
        this.active_tags_List = active_tag_List;
    }

    @NonNull
    @Override
    public hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.hilfe_finden_recycler_view_card, parent, false);
        return new hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder holder, int position) {
        user_profile_model profile = this.profilList.get(position);

        List<tag_model> tagList = profile.getTags();

        String username = profile.getUser().getUsername();

        String description = profile.getUser().getDescription();

        Integer rating = profile.getUser().getRating();

        image_model profilePhoto = profile.getProfilePhoto();



        holder.tvName.setText(username);

        //Verified
        if (!profile.getUser().isVerified()) {
            holder.tvVerified.setVisibility(View.GONE);
        }

        //Rating
        if (rating == null)
            holder.tvRating.setVisibility(View.GONE);
        else
            holder.tvRating.setText(rating + "/5");

        //Beschreibung
        if (description == "")
            holder.tvBeschreibung.setVisibility(View.GONE);
        else
            holder.tvBeschreibung.setText(description);

        //Image
        if (profilePhoto != null)
            Picasso.get().load(profilePhoto.getUrl()).into(holder.iVuserpicture);

        //Tags
        String taglistString = "";
        for (int i = 0; i < tagList.size(); i++) {
            if (i == 0)
                if (this.active_tags_List.contains(tagList.get(i)))
                    taglistString = "<b>" + tagList.get(i).toString() + "</b>";
                else
                    taglistString = tagList.get(i).toString();
            else if (this.active_tags_List.contains(tagList.get(i)))
                taglistString += ", <b>" + tagList.get(i).toString() + "</b>";
            else
                taglistString += ", " + tagList.get(i).toString();

        }
        if (taglistString == "")
            holder.tvTags.setVisibility(View.GONE);
        else
            holder.tvTags.setText(Html.fromHtml(taglistString));

        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new intent
                Intent intent = new Intent(view.getContext(), profil.class);

                intent.putExtra("id",profile.getUser().getId());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return this.profilList.size();
    }

    public static class ProfilViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvVerified, tvRating, tvBeschreibung, tvTags;
        ImageView iVuserpicture;
        CardView body;

        public ProfilViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.hilfe_finden_profil_name);
            tvVerified = itemView.findViewById(R.id.hilfe_finden_profil_verified);
            tvRating = itemView.findViewById(R.id.hilfe_finden_profil_rating);
            tvBeschreibung = itemView.findViewById(R.id.hilfe_finden_profil_beschreibung);
            tvTags = itemView.findViewById(R.id.hilfe_finden_profil_tags);
            iVuserpicture = itemView.findViewById(R.id.mein_profil_photo_circle);

            body = itemView.findViewById(R.id.card_body);
        }
    }


    private class GetImageTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
