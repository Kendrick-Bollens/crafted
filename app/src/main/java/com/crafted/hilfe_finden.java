package com.crafted;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.customViews.hilfe_finden_profil_recyclerView_adapter;
import com.crafted.external.RetrofitClient;
import com.crafted.models.tag_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.user_profile_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class hilfe_finden extends AppCompatActivity {

    List<user_profile_model> profilList = new ArrayList<user_profile_model>();

    List<tag_model> unique_tags_list = Arrays.asList(tag_model.values());

    Set<tag_model> active_tags = new HashSet<tag_model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilfe_finden);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.hilfe_finden_tag_MONTAGE);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.hilfe_finden_tag_MONTAGE:
                        return true;
                    case R.id.TItel:
                        startActivity(new Intent(getApplicationContext(), helfen.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(), chats.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.mein_profil:
                        startActivity(new Intent(getApplicationContext(), mein_profil.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }


        });

        ImageView ticket_erstellen = findViewById(R.id.hilfe_finden_ticket_erstellen);
        ticket_erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve bundle

                //new intent
                Intent intent = new Intent(view.getContext(), ticket_erstellen1.class);

                //start next activity
                view.getContext().startActivity(intent);

            }
        });

        ImageView sort = findViewById(R.id.hilfe_finden_searchfilter);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(hilfe_finden.this);
                builder.setMessage("Noch nicht implementiert");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        //Generate Tag List

        loadProfiles();

        //generate Toggle listener
        addToggleListener(R.id.hilfe_finden_tag_MONTAGE);
        addToggleListener(R.id.hilfe_finden_tag_ELECTRIC);
        addToggleListener(R.id.hilfe_finden_tag_WOOD);
        addToggleListener(R.id.hilfe_finden_tag_GARDENING);
        addToggleListener(R.id.hilfe_finden_tag_METAL);
        addToggleListener(R.id.hilfe_finden_tag_MOVING);
        addToggleListener(R.id.hilfe_finden_tag_RENOVATION);
        addToggleListener(R.id.hilfe_finden_tag_PAINTER);
        addToggleListener(R.id.hilfe_finden_tag_SANITARY);

        SearchView searchbar = (SearchView) findViewById(R.id.hilfe_finden_searchbar);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                loadProfiles(query);
                return false;
            }

        });

    }

    private void addToggleListener(int id){
        ToggleButton toggleButton =(ToggleButton) findViewById(id);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    SearchView searchbar = (SearchView) findViewById(R.id.hilfe_finden_searchbar);

                    active_tags.add(tag_model.getEnumOf(toggleButton.getTextOff().toString()));
                    loadProfiles(searchbar.getQuery().toString());
                }
                else
                {
                    SearchView searchbar = (SearchView) findViewById(R.id.hilfe_finden_searchbar);
                    active_tags.remove(tag_model.getEnumOf(toggleButton.getTextOff().toString()));
                    loadProfiles(searchbar.getQuery().toString());
                }

            }
        });
    }

    private void updateProfilCards(List<user_profile_model> profilList) {
        this.profilList = profilList;
        List<user_profile_model> active_profiles_list = new ArrayList<user_profile_model>();
        if (active_tags.size() > 0) {
            for (int i = 0; i < profilList.size(); i++) {
                if (!Collections.disjoint(active_tags, profilList.get(i).getTags())) {
                    active_profiles_list.add(profilList.get(i));
                }
            }
            RecyclerView profilRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_profiles);
            hilfe_finden_profil_recyclerView_adapter profile_adapter = new hilfe_finden_profil_recyclerView_adapter(getApplicationContext(), active_profiles_list, new ArrayList<>(active_tags));
            profilRecyclerView.setAdapter(profile_adapter);
            profilRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



            }else{
                RecyclerView profilRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_profiles);
                hilfe_finden_profil_recyclerView_adapter profile_adapter = new hilfe_finden_profil_recyclerView_adapter(getApplicationContext(), profilList, new ArrayList<>(active_tags));
                profilRecyclerView.setAdapter(profile_adapter);
                profilRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }

        private void loadProfiles (){
        loadProfiles("");
        }


        private void loadProfiles (String search) {
            try {
                Call<List<user_profile_model>> call = null;
                //Generate call to the RestAPI
                user_profile_interface api = RetrofitClient.getRetrofitInstance().create(user_profile_interface.class);

                //differentiate between with and without search
                if(search=="")
                    call = api.getUsers(RetrofitClient.getBearerToken());
                else
                    call = api.getUsers(RetrofitClient.getBearerToken(),search);


                call.enqueue(new Callback<List<user_profile_model>>() {

                    private final ProgressDialog dialog = new ProgressDialog(hilfe_finden.this);


                    @Override
                    public void onResponse(Call<List<user_profile_model>> call, Response<List<user_profile_model>> response) {
                        this.dialog.setMessage("Please wait");
                        this.dialog.show();
                        try {
                            //Get all Profiles
                            profilList = response.body();

                            List<tag_model> all_tags_list = new ArrayList<tag_model>();
                            //get list of individual Tags
                            for (int i = 0; i < profilList.size(); i++) {
                                for (int j = 0; j < profilList.get(i).getTags().size(); j++) {
                                    all_tags_list.add(profilList.get(i).getTags().get(j));
                                }
                            }

                            //Get Distinct Tags
                            Set<tag_model> unique_tags_set = new HashSet<tag_model>(all_tags_list);
                            unique_tags_list = new ArrayList<>(unique_tags_set);

                            updateProfilCards(profilList);


                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<user_profile_model>> call, Throwable t) {
                        //Handle failure
                        t.printStackTrace();

                    }


                });
            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }