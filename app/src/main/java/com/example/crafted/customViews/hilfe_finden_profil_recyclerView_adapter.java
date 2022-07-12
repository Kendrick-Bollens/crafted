package com.example.crafted.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crafted.R;
import com.example.crafted.models.user_model;

import java.util.List;

public class hilfe_finden_profil_recyclerView_adapter extends RecyclerView.Adapter<hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder> {

    Context context;
    List<user_model> profilList;

    public hilfe_finden_profil_recyclerView_adapter(Context context, List<user_model> profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    @NonNull
    @Override
    public hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.hilfe_finden_recycler_view_card,parent,false);
        return new hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hilfe_finden_profil_recyclerView_adapter.ProfilViewHolder holder, int position) {
        holder.tvName.setText(this.profilList.get(position).getUsername());
        holder.tvRating.setText("?/5");
        holder.tvBeschreibung.setText(this.profilList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return this.profilList.size();
    }

    public static class ProfilViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvVerified, tvRating, tvBeschreibung;

        public ProfilViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.hilfe_finden_profil_name);
            tvVerified = itemView.findViewById(R.id.hilfe_finden_profil_verified);
            tvRating = itemView.findViewById(R.id.hilfe_finden_profil_rating);
            tvBeschreibung = itemView.findViewById(R.id.hilfe_finden_profil_beschreibung);

        }
    }
}
