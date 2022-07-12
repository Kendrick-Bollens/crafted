package com.example.crafted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.crafted.models.user_model;
import com.example.crafted.models.tag_model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class hilfe_finden extends AppCompatActivity {

    List<user_model>  profilList = new ArrayList<>();
    List<tag_model> tagList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilfe_finden);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.hilfe_finden);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.hilfe_finden:
                        return true;
                    case R.id.helfen:
                        startActivity(new Intent(getApplicationContext(),helfen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(),chats.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.mein_profil:
                        startActivity(new Intent(getApplicationContext(),mein_profil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //Generate Tag List
        //RecyclerView profilRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_profiles);

        //setUpProfilList();

        //ilfe_finden_profil_recyclerView_adapter profile_adapter = new hilfe_finden_profil_recyclerView_adapter(this,profilList);
       // profilRecyclerView.setAdapter(profile_adapter);
        //profilRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Generate Tag List
        //RecyclerView tagRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_tags);

        //setUpTagList();

       // hilfe_finden_tags_recycleView_adapter tag_adapter = new hilfe_finden_tags_recycleView_adapter(this, tagModelList);
        //tagRecyclerView.setAdapter(tag_adapter);
        //tagRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    private void setUpProfilList(){
        String[] namen = getResources().getStringArray(R.array.hilfe_finden_profil_name);
        String[] verified = getResources().getStringArray(R.array.hilfe_finden_profil_verified);
        int[] rating = getResources().getIntArray(R.array.hilfe_finden_profil_rating);
        String[] beschreibung = getResources().getStringArray(R.array.hilfe_finden_profil_beschreibung);


    }

    private void setUpTagList(){
        String[] tags = getResources().getStringArray(R.array.hilfe_finden_profil_tag);

    }

}