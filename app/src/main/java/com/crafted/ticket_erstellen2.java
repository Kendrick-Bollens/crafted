package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crafted.customViews.ticket_image_recyclerView_adapter;
import com.crafted.models.image_model;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ticket_erstellen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen2);

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

        List<image_model> bsp_images = new ArrayList<image_model>();

        image_model i1 = new image_model("https://www.pexels.com/de-de/foto/geoffneter-weisser-kanal-mit-drahten-im-inneren-1583656/","Geöffneter Weißer Kanal Mit Drähten Im Inneren");
        image_model i2 = new image_model("https://www.pexels.com/de-de/foto/brown-wood-shred-175709/","Brown Wood Shred");
        image_model i3 = new image_model("https://www.pexels.com/de-de/foto/person-die-farbroller-an-der-wand-halt-1669754/","Person, Die Farbroller An Der Wand Hält");
        image_model i4 = new image_model("https://www.pexels.com/de-de/foto/werkzeuge-hammer-dokument-papier-5805494/","Athena");
        image_model i5 = new image_model("https://unsplash.com/photos/wfqsRJZjXP0","flower in a field during daytime photo");
        image_model i6 = new image_model("https://unsplash.com/photos/uIT40l9Z4Ug","red C-lamp photo");
        image_model i7 = new image_model("https://www.pexels.com/de-de/foto/hausrenovierung-3990359/","Hausrenovierung");
        image_model i8 = new image_model("https://pixabay.com/images/id-2788329/","Klempner Reparatur Tippen");
        image_model i9 = new image_model("https://www.pexels.com/de-de/foto/fenster-wohnung-innere-box-4569338/","cottonbro");

        bsp_images.add(i1);bsp_images.add(i2);bsp_images.add(i3);
        bsp_images.add(i4);bsp_images.add(i5);bsp_images.add(i6);
        bsp_images.add(i7);bsp_images.add(i8);bsp_images.add(i9);

        ImageView image1 = findViewById(R.id.ticket_erstellen2_bild_A);
        Picasso.get().load(i1.getUrl()).into(image1);
        ImageView image2 = findViewById(R.id.ticket_erstellen2_bild_B);
        Picasso.get().load(i2.getUrl()).into(image2);




        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i1.getUrl());
                bundle.putString("alt_text",i1.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i2.getUrl());
                bundle.putString("alt_text",i2.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        Button button_zurueck = (Button) findViewById(R.id.ticket_erstellen2_button_zurueck);
        button_zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();

                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen1.class);

                //Retrieve Ticket name
                String ticket_bild_url = "";

                //add new ticket name
                bundle.putString("ticket_bild_url", ticket_bild_url);

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });
    }
}