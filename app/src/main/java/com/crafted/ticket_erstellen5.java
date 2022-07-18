package com.crafted;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crafted.external.RetrofitClient;
import com.crafted.models.image_model;
import com.crafted.models.tag_model;
import com.crafted.models.ticket_info_model;
import com.crafted.models.ticket_model;
import com.crafted.models.ticket_post_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.ticket_info_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ticket_erstellen5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen5);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.TItel);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mein_profil:
                        startActivity(new Intent(getApplicationContext(), mein_profil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.hilfe_finden_tag_MONTAGE:
                        startActivity(new Intent(getApplicationContext(), hilfe_finden.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.TItel:
                        startActivity(new Intent(getApplicationContext(), helfen.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(), chats.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        Button button_home = findViewById(R.id.ticket_erstellen5_button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new intent
                Intent intent = new Intent(view.getContext(), hilfe_finden.class);

                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);


            }
        });


        //make text
        String text = "<b>Super!</b> \nDein Ticket wurde \nerfolgreich erstellt.";
        TextView textView = findViewById(R.id.ticket_erstellen5_text);
        textView.setText(Html.fromHtml(text));



        //Write Ticket into cloud
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("ticket_name");

        String beschreibung = bundle.getString("ticket_beschreibung");

        String url = bundle.getString("image_url");
        String altText = bundle.getString("alt_text");

        List<image_model> images = new ArrayList<image_model>();

        images.add(new image_model(url, altText));

        List<tag_model> tags = new ArrayList<tag_model>();

        if (bundle.getBoolean("ELECTRIC"))
            tags.add(tag_model.ELECTRIC);

        if (bundle.getBoolean("GARDENING"))
            tags.add(tag_model.GARDENING);


        if (bundle.getBoolean("METAL"))
            tags.add(tag_model.METAL);


        if (bundle.getBoolean("MONTAGE"))
            tags.add(tag_model.MONTAGE);


        if (bundle.getBoolean("MOVING"))
            tags.add(tag_model.MOVING);


        if (bundle.getBoolean("PAINTER"))
            tags.add(tag_model.PAINTER);


        if (bundle.getBoolean("RENOVATION"))
            tags.add(tag_model.RENOVATION);


        if (bundle.getBoolean("WOOD"))
            tags.add(tag_model.WOOD);


        if (bundle.getBoolean("SANITARY"))
            tags.add(tag_model.SANITARY);

        //icket_model ticket_base = new ticket_model(name,beschreibung);

        //ticket_info_model ticket_full = new ticket_info_model(ticket_base,tags,images);

        ticket_post_model ticket_full = new ticket_post_model(name, beschreibung, tags, images);


        try {

            ticket_info_interface api = RetrofitClient.getRetrofitInstance().create(ticket_info_interface.class);
            Call<ticket_info_model> call = api.createTicket(RetrofitClient.getBearerToken(),ticket_full);
            call.enqueue(new Callback<ticket_info_model>() {
                @Override
                public void onResponse(Call<ticket_info_model> call, Response<ticket_info_model> response) {
                    if(response.isSuccessful()){
                        ticket_info_model new_ticket = response.body();

                    }else{
                        System.out.println(response.code());
                        System.out.println(response.raw());
                    }



                }

                @Override
                public void onFailure(Call<ticket_info_model> call, Throwable t) {

                }
            });

        }catch (Exception e){
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }






    }
}