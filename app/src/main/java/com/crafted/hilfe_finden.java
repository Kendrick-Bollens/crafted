package com.crafted;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.customViews.hilfe_finden_profil_recyclerView_adapter;
import com.crafted.customViews.hilfe_finden_tags_recycleView_adapter;
import com.crafted.external.RetrofitClient;
import com.crafted.models.tag_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.user_profile_interface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class hilfe_finden extends AppCompatActivity {

    List<user_profile_model> profilList = new ArrayList<user_profile_model>();

    List<tag_model> unique_tags_list = new ArrayList<tag_model>();

    List<tag_model> active_tags_list = new ArrayList<tag_model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilfe_finden);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.hilfe_finden);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.hilfe_finden:
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


        //Generate Tag List

        loadProfiles();

    }

    private void updateProfilCards(List<user_profile_model> profilList) {
        this.profilList = profilList;
        List<user_profile_model> active_profiles_list = new ArrayList<user_profile_model>();
        if (active_tags_list.size() > 0) {
            for (int i = 0; i < profilList.size(); i++) {
                if (!Collections.disjoint(active_tags_list, profilList.get(i).getTags())) {
                    active_profiles_list.add(profilList.get(i));
                }
            }
            RecyclerView profilRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_profiles);
            hilfe_finden_profil_recyclerView_adapter profile_adapter = new hilfe_finden_profil_recyclerView_adapter(getApplicationContext(), active_profiles_list);
            profilRecyclerView.setAdapter(profile_adapter);
            profilRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }else{
                RecyclerView profilRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_profiles);
                hilfe_finden_profil_recyclerView_adapter profile_adapter = new hilfe_finden_profil_recyclerView_adapter(getApplicationContext(), profilList);
                profilRecyclerView.setAdapter(profile_adapter);
                profilRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }


        private void updateTags (List < tag_model > unique_tags_list) {
            this.unique_tags_list = unique_tags_list;
            RecyclerView tagRecyclerView = findViewById(R.id.hilfe_finden_RecyclerView_tags);
            hilfe_finden_tags_recycleView_adapter tag_adapter = new hilfe_finden_tags_recycleView_adapter(getApplicationContext(), unique_tags_list);
            tagRecyclerView.setAdapter(tag_adapter);
            tagRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        private void loadProfiles () {
            try {

                //Generate call to the RestAPI
                user_profile_interface api = RetrofitClient.getRetrofitInstance().create(user_profile_interface.class);
                Call<List<user_profile_model>> call = api.getUser(RetrofitClient.getBearerToken());
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

                            System.out.println("that " + profilList.size());

                            updateProfilCards(profilList);
                            updateTags(unique_tags_list);


                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            System.out.println("Error: " + e);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<user_profile_model>> call, Throwable t) {
                        //Handle failure
                        System.out.println(t);

                    }


                });
            } catch (Exception e) {
                System.out.println(e.getClass());
                System.out.println(e.getCause());
                System.out.println(e.getMessage());

            }
        }


    }