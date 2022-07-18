package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crafted.customViews.profil_project_recyclerView_adapter;
import com.crafted.customViews.profil_ticket_recyclerView_adapter;
import com.crafted.customViews.ticket_image_recyclerView_adapter;
import com.crafted.external.RetrofitClient;
import com.crafted.models.image_model;
import com.crafted.models.ticket_info_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.image_interface;
import com.crafted.retrofit_interfaces.ticket_info_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ticket extends AppCompatActivity {

    ticket_info_model ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.TItel);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.mein_profil:
                        startActivity(new Intent(getApplicationContext(), mein_profil.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hilfe_finden_tag_MONTAGE:
                        startActivity(new Intent(getApplicationContext(), hilfe_finden.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.TItel:
                        startActivity(new Intent(getApplicationContext(),helfen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(),chats.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        int id = getIntent().getExtras().getInt("id");

        //Lade die Daten
        loadTicket(id);


    }

    private void updateTicket(ticket_info_model ticket){
        this.ticket = ticket;


        //Titel
        TextView ticket_titel = findViewById(R.id.ticket_titel);

        ticket_titel.setText(ticket.getTicket().getTitle());

        //Images
        RecyclerView images_recyclerview = findViewById(R.id.ticket_images);
        ticket_image_recyclerView_adapter ticket_image_adapter = new ticket_image_recyclerView_adapter(getApplicationContext(), ticket.getImages());
        images_recyclerview.setAdapter(ticket_image_adapter);
        images_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        //Tags
        TextView ticket_tags = findViewById(R.id.ticket_tags);

        String ticket_tags_string = "";

        for (int i = 0; i < ticket.getTags().size(); i++) {
            if (i == 0) ticket_tags_string = ticket.getTags().get(i).toString();
            else ticket_tags_string += ", " + ticket.getTags().get(i).toString();
        }
        ticket_tags.setText(ticket_tags_string);

        //User Profile
        TextView user_name = findViewById(R.id.ticket_user_name);
        TextView user_verified = findViewById(R.id.ticket_user_verified);
        TextView user_rating = findViewById(R.id.ticket_user_rating);
        Button kontaktiere = findViewById(R.id.ticket_user_kontaktieren);

        user_name.setText(ticket.getUser().getUsername());
        if(!ticket.getUser().isVerified()) user_verified.setVisibility(View.GONE);
        user_rating.setText(ticket.getUser().getRating()+"/5");
        kontaktiere.setText(ticket.getUser().getUsername()+" kontaktieren");

        ImageView user_pic = findViewById(R.id.ticket_user_photo);




        //beschreibung
        TextView ticket_beschreibung = findViewById(R.id.ticket_beschreibung);
        ticket_beschreibung.setText(ticket.getTicket().getDescription());



            //Generate call to the RestAPI
            image_interface api = RetrofitClient.getRetrofitInstance().create(image_interface.class);
            Call<image_model> call = api.getImageById(RetrofitClient.getBearerToken(),ticket.getUser().getProfilePhotoId());
            call.enqueue(new Callback<image_model>() {


                @Override
                public void onResponse(Call<image_model> call, Response<image_model> response) {

                        image_model image = response.body();

                        Picasso.get().load(image.getUrl()).into(user_pic);



                }

                @Override
                public void onFailure(Call<image_model> call, Throwable t) {
                    //Handle failure#
                    t.printStackTrace();

                }


            });







    }


    private void loadTicket (int id) {
        try {

            //Generate call to the RestAPI
            ticket_info_interface api = RetrofitClient.getRetrofitInstance().create(ticket_info_interface.class);
            Call<ticket_info_model> call = api.getTicketById(RetrofitClient.getBearerToken(),id);
            call.enqueue(new Callback<ticket_info_model>() {

                private final ProgressDialog dialog = new ProgressDialog(ticket.this);


                @Override
                public void onResponse(Call<ticket_info_model> call, Response<ticket_info_model> response) {
                    this.dialog.setMessage("Please wait");
                    this.dialog.show();
                    try {
                        //Get all Profiles
                        ticket = response.body();

                        updateTicket(ticket);

                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ticket_info_model> call, Throwable t) {
                    //Handle failure
                    t.printStackTrace();

                }


            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}