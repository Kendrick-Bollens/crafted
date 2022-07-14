package com.crafted;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ticket_erstellen4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen4);

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

        Bundle bundle = getIntent().getExtras();


        ToggleButton ELECTRIC = findViewById(R.id.ticket_erstellen4_tag_ELECTRIC);
        ELECTRIC.setActivated(bundle.getBoolean("ELECTRIC"));

        ToggleButton GARDENING = findViewById(R.id.ticket_erstellen4_tag_GARDENING);
        GARDENING.setActivated(bundle.getBoolean("GARDENING"));

        ToggleButton METAL = findViewById(R.id.ticket_erstellen4_tag_METAL);
        METAL.setActivated(bundle.getBoolean("METAL"));

        ToggleButton MONTAGE = findViewById(R.id.ticket_erstellen4_tag_MONTAGE);
        MONTAGE.setActivated(bundle.getBoolean("MONTAGE"));

        ToggleButton MOVING = findViewById(R.id.ticket_erstellen4_tag_MOVING);
        MOVING.setActivated(bundle.getBoolean("MOVING"));

        ToggleButton PAINTER = findViewById(R.id.ticket_erstellen4_tag_PAINTER);
        PAINTER.setActivated(bundle.getBoolean("PAINTER"));

        ToggleButton RENOVATION = findViewById(R.id.ticket_erstellen4_tag_RENOVATION);
        RENOVATION.setActivated(bundle.getBoolean("RENOVATION"));

        ToggleButton WOOD = findViewById(R.id.ticket_erstellen4_tag_WOOD);
        WOOD.setActivated(bundle.getBoolean("WOOD"));

        ToggleButton SANITARY = findViewById(R.id.ticket_erstellen4_tag_SANITARY);
        SANITARY.setActivated(bundle.getBoolean("SANITARY"));



        Button button_weiter = findViewById(R.id.ticket_erstellen4_button_weiter);
        button_weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the text Input Layout


                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();

                ToggleButton ELECTRIC = findViewById(R.id.ticket_erstellen4_tag_ELECTRIC);
                bundle.putBoolean("ELECTRIC",ELECTRIC.isChecked());

                ToggleButton GARDENING = findViewById(R.id.ticket_erstellen4_tag_GARDENING);
                bundle.putBoolean("GARDENING",GARDENING.isChecked());

                ToggleButton METAL = findViewById(R.id.ticket_erstellen4_tag_METAL);
                bundle.putBoolean("METAL",METAL.isChecked());

                ToggleButton MONTAGE = findViewById(R.id.ticket_erstellen4_tag_MONTAGE);
                bundle.putBoolean("MONTAGE",MONTAGE.isChecked());

                ToggleButton MOVING = findViewById(R.id.ticket_erstellen4_tag_MOVING);
                bundle.putBoolean("MOVING",MOVING.isChecked());

                ToggleButton PAINTER = findViewById(R.id.ticket_erstellen4_tag_PAINTER);
                bundle.putBoolean("PAINTER",PAINTER.isChecked());

                ToggleButton RENOVATION = findViewById(R.id.ticket_erstellen4_tag_RENOVATION);
                bundle.putBoolean("RENOVATION",RENOVATION.isChecked());

                ToggleButton WOOD = findViewById(R.id.ticket_erstellen4_tag_WOOD);
                bundle.putBoolean("WOOD",WOOD.isChecked());

                ToggleButton SANITARY = findViewById(R.id.ticket_erstellen4_tag_SANITARY);
                bundle.putBoolean("SANITARY",SANITARY.isChecked());




                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen5.class);

                //update bundle
                intent.putExtras(bundle);


                //start next activity
                view.getContext().startActivity(intent);



            }
        });

        Button button_zurueck = findViewById(R.id.ticket_erstellen4_button_zurueck);
        button_zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);

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