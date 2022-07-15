package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.crafted.external.RetrofitClient;
import com.crafted.models.image_model;
import com.crafted.models.tag_model;
import com.crafted.models.ticket_post_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.ticket_info_interface;
import com.crafted.retrofit_interfaces.user_profile_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ticket_erstellen5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen5);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

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

        //make text
        String text = "<b>Super!</b> \nDein Ticket wurde \nerfolgreich erstellt.";
        TextView textView =findViewById(R.id.ticket_erstellen5_text);
        textView.setText(Html.fromHtml(text));

        Bundle bundle = getIntent().getExtras();

        String url = bundle.getString("image_url");
        String altText = bundle.getString("alt_text");

        List<image_model> images = new ArrayList<image_model>();

        images.add(new image_model(url,altText));

        List<tag_model> tags = new ArrayList<tag_model>();

        if(bundle.getBoolean("ELECTRIC"))
            tags.add(tag_model.ELECTRIC);

        if(bundle.getBoolean("GARDENING"))
            tags.add(tag_model.GARDENING);


        if(bundle.getBoolean("METAL"))
            tags.add(tag_model.METAL);


        if(bundle.getBoolean("MONTAGE"))
            tags.add(tag_model.MONTAGE);


        if( bundle.getBoolean("MOVING"))
            tags.add(tag_model.MOVING);


        if(bundle.getBoolean("PAINTER"))
            tags.add(tag_model.PAINTER);


        if(bundle.getBoolean("RENOVATION"))
            tags.add(tag_model.RENOVATION);


        if(bundle.getBoolean("WOOD"))
            tags.add(tag_model.WOOD);


        if( bundle.getBoolean("SANITARY"))
            tags.add(tag_model.SANITARY);



        ticket_post_model new_ticket = new ticket_post_model(bundle.getString("ticket_name"), bundle.getString("ticket_beschreibung"),tags,images);

        ticket_info_interface api = RetrofitClient.getRetrofitInstance().create(ticket_info_interface.class);
        api.createTicket(RetrofitClient.getBearerToken(),new_ticket);


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

    }
}