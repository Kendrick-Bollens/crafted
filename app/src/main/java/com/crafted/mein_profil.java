package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crafted.external.RetrofitClient;
import com.crafted.models.tag_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.user_profile_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mein_profil extends AppCompatActivity {

    user_profile_model profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mein_profil);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.mein_profil);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.mein_profil:
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

        //Lade die Daten
        loadProfile();



    }

    private void updateProfil(user_profile_model profil){
        this.profil = profil;
        ImageView profilphoto = findViewById(R.id.mein_profil_photo);

        //set Profile Photo
        if(profil.getProfilePhoto() != null)
            Picasso.get().load(profil.getProfilePhoto().getUrl()).into(profilphoto);

        //set Name
        TextView name = findViewById(R.id.mein_profil_name);
        name.setText(profil.getUser().getUsername());

        //set verified
        TextView verified = findViewById(R.id.mein_profil_verified);
        if (!profil.getUser().isVerified())
            verified.setVisibility(View.GONE);

        //set Mitglied seit Yahr
        TextView mitgliedseit = findViewById(R.id.mein_profil_mitglieddauer);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(profil.getUser().getUserCreateDate());
        mitgliedseit.setText("Mitglied seit "+cal.get(Calendar.YEAR));

        //set Rating
        TextView rating = findViewById(R.id.mein_profil_rating);
        rating.setText(profil.getUser().getRating()+"/5");


        //set Beschreibung
        TextView beschreibung = findViewById(R.id.mein_profil_description);
        beschreibung.setText(profil.getUser().getDescription());






    }


    private void loadProfile () {
        try {

            //Generate call to the RestAPI
            user_profile_interface api = RetrofitClient.getRetrofitInstance().create(user_profile_interface.class);
            Call<user_profile_model> call = api.getMyUser(RetrofitClient.getBearerToken());
            call.enqueue(new Callback<user_profile_model>() {

                private final ProgressDialog dialog = new ProgressDialog(mein_profil.this);


                @Override
                public void onResponse(Call<user_profile_model> call, Response<user_profile_model> response) {
                    this.dialog.setMessage("Please wait");
                    this.dialog.show();
                    try {
                        //Get all Profiles
                        profil = response.body();

                        updateProfil(profil);

                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }

                }

                @Override
                public void onFailure(Call<user_profile_model> call, Throwable t) {
                    //Handle failure
                    System.out.println(t);

                }


            });
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());

        }
    }

}