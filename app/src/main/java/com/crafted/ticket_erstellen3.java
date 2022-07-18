package com.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

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

        try {
            if (getIntent().getExtras() != null) {
                TextInputLayout textInputLayout = findViewById(R.id.ticket_erstellen3_input_field_beschreibung);
                String ticket_beschreibung = getIntent().getExtras().getString("ticket_beschreibung");
                textInputLayout.getEditText().setText(ticket_beschreibung);


            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Button button_weiter = (Button) findViewById(R.id.ticket_erstellen3_button_weiter);
        button_weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //get the text Input Layout
                TextInputLayout textInputLayout = findViewById(R.id.ticket_erstellen3_input_field_beschreibung);
                //Retrieve Ticket name
                String ticket_beschreibung = textInputLayout.getEditText().getText().toString();
                if (ticket_beschreibung.isEmpty()) {
                    textInputLayout.setError("You need to enter a description");
                } else {

                    //Retrieve bundle
                    Bundle bundle = getIntent().getExtras();

                    //new intent
                    Intent intent = new Intent(view.getContext(), ticket_erstellen4.class);

                    //add new ticket name
                    bundle.putString("ticket_beschreibung", ticket_beschreibung);

                    //update bundle
                    intent.putExtras(bundle);

                    //start next activity
                    view.getContext().startActivity(intent);

                }
            }
        });

        Button button_zurueck = (Button) findViewById(R.id.ticket_erstellen3_button_zurueck);
        button_zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen2.class);

                //get the text Input Layout
                TextInputLayout textInputLayout = findViewById(R.id.ticket_erstellen3_input_field_beschreibung);
                //Retrieve Ticket name
                String ticket_beschreibung = textInputLayout.getEditText().getText().toString();
                if (ticket_beschreibung.isEmpty()) {

                    //Retrieve bundle
                    Bundle bundle = getIntent().getExtras();

                    //update bundle
                    intent.putExtras(bundle);

                    //start next activity
                    view.getContext().startActivity(intent);

                } else {
                    Bundle bundle = new Bundle();

                    //add new ticket name
                    bundle.putString("ticket_beschreibung", ticket_beschreibung);

                    //update bundle
                    intent.putExtras(bundle);

                    //start next activity
                    view.getContext().startActivity(intent);

                }

            }
        });
    }
}