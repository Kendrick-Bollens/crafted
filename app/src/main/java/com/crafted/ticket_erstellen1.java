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

public class ticket_erstellen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_erstellen1);

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
                TextInputLayout textInputLayout = findViewById(R.id.ticket_erstellen1_input_field_name);
                String ticket_name = getIntent().getExtras().getString("ticket_name");
                textInputLayout.getEditText().setText(ticket_name);


            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Button button1 = (Button) findViewById(R.id.ticket_erstellen1_button_weiter);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //get the text Input Layout
                    TextInputLayout textInputLayout = findViewById(R.id.ticket_erstellen1_input_field_name);
                    //Retrieve Ticket name
                    String ticket_name = textInputLayout.getEditText().getText().toString();
                    if (ticket_name.isEmpty()) {
                        textInputLayout.setError("You need to enter a name");
                    } else {

                        //Retrieve bundle
                        Bundle bundle = getIntent().getExtras();
                        if(bundle == null)
                            bundle = new Bundle();

                        //new intent
                        Intent intent = new Intent(view.getContext(), ticket_erstellen2.class);

                        //add new ticket name
                        bundle.putString("ticket_name", ticket_name);

                        //update bundle
                        intent.putExtras(bundle);

                        //start next activity
                        view.getContext().startActivity(intent);

                    }

            }
        });


    }
}