package com.crafted.customViews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.R;
import com.crafted.models.tag_model;
import com.crafted.models.user_profile_model;

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
        List<tag_model> tagList = this.profilList.get(position).getTags();

        holder.tvName.setText(this.profilList.get(position).getUser().getUsername());
        holder.tvRating.setText("?/5");
        holder.tvBeschreibung.setText(this.profilList.get(position).getUser().getDescription());

        String taglistString = "";
        for (int i = 0; i < tagList.size(); i++) {
            if (i == 0) taglistString = tagList.get(i).toString();

            if (this.active_tags_List.contains(tagList.get(i)))
                taglistString += ", <b>" + tagList.get(i).toString() + "</b>";
            else taglistString += ", " + tagList.get(i).toString();

        }
        holder.tvTags.setText(Html.fromHtml(taglistString));


    }

    @Override
    public int getItemCount() {
        return this.profilList.size();
    }

    public static class ProfilViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvVerified, tvRating, tvBeschreibung, tvTags;

        public ProfilViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.hilfe_finden_profil_name);
            tvVerified = itemView.findViewById(R.id.hilfe_finden_profil_verified);
            tvRating = itemView.findViewById(R.id.hilfe_finden_profil_rating);
            tvBeschreibung = itemView.findViewById(R.id.hilfe_finden_profil_beschreibung);
            tvTags = itemView.findViewById(R.id.hilfe_finden_profil_tags);
        }
    }
}
