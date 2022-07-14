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

import com.google.android.material.bottomnavigation.BottomNavigationView;

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