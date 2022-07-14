package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ticket_erstellen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen3);

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
                    case R.id.hilfe_finden:
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

        Button button_weiter = (Button) findViewById(R.id.ticket_erstellen3_button_weiter);
        button_weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle
                Bundle bundle = getIntent().getExtras();

                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen3.class);

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

        Button button_zurueck = (Button) findViewById(R.id.ticket_erstellen3_button_zurueck);
        button_weiter.setOnClickListener(new View.OnClickListener() {
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