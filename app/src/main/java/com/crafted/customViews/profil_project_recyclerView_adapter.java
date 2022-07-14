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
import com.crafted.models.ticket_info_model;
import com.crafted.models.user_model;

import java.util.List;

public class profil_project_recyclerView_adapter extends RecyclerView.Adapter<profil_project_recyclerView_adapter.ProjectViewHolder> {

    Context context;
    List<ticket_info_model> ticket_info_modelList;

    public profil_project_recyclerView_adapter(Context context, List<ticket_info_model> ticket_info_modelList) {
        this.context = context;
        this.ticket_info_modelList = ticket_info_modelList;
    }


    @NonNull
    @Override
    public profil_project_recyclerView_adapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.profil_recycler_view_projekt, parent, false);
        return new profil_project_recyclerView_adapter.ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull profil_project_recyclerView_adapter.ProjectViewHolder holder, int position) {

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


    }

    @Override
    public int getItemCount() {
        return this.ticket_info_modelList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitel, tvBeschreibung, tvTags;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.project_card_Titel);
            tvBeschreibung = itemView.findViewById(R.id.project_card_Beschreibung);
            tvTags = itemView.findViewById(R.id.project_card_tags);

        }
    }
}
