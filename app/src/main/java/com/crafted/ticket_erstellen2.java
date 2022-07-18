package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        image_model i1 = new image_model("https://images.pexels.com/photos/1583656/pexels-photo-1583656.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Geöffneter Weißer Kanal Mit Drähten Im Inneren");
        image_model i2 = new image_model("https://images.pexels.com/photos/175709/pexels-photo-175709.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Brown Wood Shred");
        image_model i3 = new image_model("https://images.pexels.com/photos/1669754/pexels-photo-1669754.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Person, Die Farbroller An Der Wand Hält");
        image_model i4 = new image_model("https://images.pexels.com/photos/5805494/pexels-photo-5805494.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Athena");
        image_model i5 = new image_model("https://images.unsplash.com/photo-1561563239-a6d2bd5dda20?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1546&q=80","flower in a field during daytime photo");
        image_model i6 = new image_model("https://images.unsplash.com/photo-1529926542502-77aceca00aa3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1160&q=80","red C-lamp photo");
        image_model i7 = new image_model("https://images.pexels.com/photos/3990359/pexels-photo-3990359.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Hausrenovierung");
        image_model i8 = new image_model("https://cdn.pixabay.com/photo/2017/09/26/11/10/plumber-2788329_960_720.jpg","Klempner Reparatur Tippen");


        bsp_images.add(i1);bsp_images.add(i2);bsp_images.add(i3);
        bsp_images.add(i4);bsp_images.add(i5);bsp_images.add(i6);
        bsp_images.add(i7);bsp_images.add(i8);


        ImageView image1 = findViewById(R.id.ticket_erstellen2_bild_A);
        Picasso.get().load(i1.getUrl()).into(image1);
        ImageView image2 = findViewById(R.id.ticket_erstellen2_bild_B);
        Picasso.get().load(i2.getUrl()).into(image2);
        ImageView image3 = findViewById(R.id.ticket_erstellen2_bild_C);
        Picasso.get().load(i3.getUrl()).into(image3);
        ImageView image4 = findViewById(R.id.ticket_erstellen2_bild_D);
        Picasso.get().load(i4.getUrl()).into(image4);
        ImageView image5 = findViewById(R.id.ticket_erstellen2_bild_E);
        Picasso.get().load(i5.getUrl()).into(image5);
        ImageView image6 = findViewById(R.id.ticket_erstellen2_bild_F);
        Picasso.get().load(i6.getUrl()).into(image6);
        ImageView image7 = findViewById(R.id.ticket_erstellen2_bild_G);
        Picasso.get().load(i7.getUrl()).into(image7);
        ImageView image8 = findViewById(R.id.ticket_erstellen2_bild_H);
        Picasso.get().load(i8.getUrl()).into(image8);


        //Image View on Clicks
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

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i3.getUrl());
                bundle.putString("alt_text",i3.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i4.getUrl());
                bundle.putString("alt_text",i4.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i5.getUrl());
                bundle.putString("alt_text",i5.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i6.getUrl());
                bundle.putString("alt_text",i6.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i7.getUrl());
                bundle.putString("alt_text",i7.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();


                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);



                //add new ticket name
                bundle.putString("image_url", i8.getUrl());
                bundle.putString("alt_text",i8.getAltText());

                //update bundle
                intent.putExtras(bundle);

                //start next activity
                view.getContext().startActivity(intent);
            }
        });


        TextView bild_hinzufuegen = findViewById(R.id.ticket_erstellen2_button_bild_hunzufuegen);
        bild_hinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ticket_erstellen2.this);
                builder.setMessage("Noch nicht implementiert");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        //back button
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