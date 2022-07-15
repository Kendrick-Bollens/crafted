package com.crafted;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crafted.customViews.helfen_ticket_recyclerView_adapter;
import com.crafted.external.RetrofitClient;
import com.crafted.models.tag_model;
import com.crafted.models.ticket_info_model;
import com.crafted.retrofit_interfaces.ticket_info_interface;
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

public class helfen extends AppCompatActivity {

    List<ticket_info_model> ticketList = new ArrayList<ticket_info_model>();

    List<tag_model> unique_tags_list = Arrays.asList(tag_model.values());

    Set<tag_model> active_tags = new HashSet<tag_model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helfen);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.TItel);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.TItel:
                        return true;
                    case R.id.hilfe_finden_tag_MONTAGE:
                        startActivity(new Intent(getApplicationContext(), hilfe_finden.class));
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

        //generate Toggle listener
        addToggleListener(R.id.helfen_tag_MONTAGE);
        addToggleListener(R.id.helfen_tag_ELECTRIC);
        addToggleListener(R.id.helfen_tag_WOOD);
        addToggleListener(R.id.helfen_tag_GARDENING);
        addToggleListener(R.id.helfen_tag_METAL);
        addToggleListener(R.id.helfen_tag_MOVING);
        addToggleListener(R.id.helfen_tag_RENOVATION);
        addToggleListener(R.id.helfen_tag_PAINTER);
        addToggleListener(R.id.helfen_tag_SANITARY);

        //generate Tickets
        loadTickets();

        SearchView searchbar = findViewById(R.id.helfen_searchbar);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                loadTickets(query);
                return false;
            }

        });


    }

    private void updateTicketCards(List<ticket_info_model> ticketList) {
        this.ticketList = ticketList;

        List<ticket_info_model> active_ticket_List = new ArrayList<ticket_info_model>();
        if (active_tags.size() > 0) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (!Collections.disjoint(active_tags, ticketList.get(i).getTags())) {
                    active_ticket_List.add(ticketList.get(i));
                }
            }
            RecyclerView ticket_recyclerView = findViewById(R.id.helfen_RecyclerView_tickets);
            helfen_ticket_recyclerView_adapter ticket_adapter = new helfen_ticket_recyclerView_adapter(getApplicationContext(), active_ticket_List, new ArrayList<>(active_tags));
            ticket_recyclerView.setAdapter(ticket_adapter);
            ticket_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        } else {
            RecyclerView ticket_recyclerView = findViewById(R.id.helfen_RecyclerView_tickets);
            helfen_ticket_recyclerView_adapter ticket_adapter = new helfen_ticket_recyclerView_adapter(getApplicationContext(), ticketList, new ArrayList<>(active_tags));
            ticket_recyclerView.setAdapter(ticket_adapter);
            ticket_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        }
    }

    private void addToggleListener(int id) {
        ToggleButton toggleButton = findViewById(id);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    active_tags.add(tag_model.getEnumOf(toggleButton.getTextOff().toString()));
                    SearchView searchbar = findViewById(R.id.helfen_searchbar);

                    loadTickets(searchbar.getQuery().toString());
                } else {
                    active_tags.remove(tag_model.getEnumOf(toggleButton.getTextOff().toString()));
                    SearchView searchbar = findViewById(R.id.helfen_searchbar);
                    loadTickets(searchbar.getQuery().toString());
                }

            }
        });
    }

    private void loadTickets() {
        loadTickets("");
    }

    private void loadTickets(String search) {
        try {

            Call<List<ticket_info_model>> call;
            //Generate call to the RestAPI
            ticket_info_interface api = RetrofitClient.getRetrofitInstance().create(ticket_info_interface.class);
            if (search == "")
                call = api.getTickets(RetrofitClient.getBearerToken());
            else
                call = api.getTickets(RetrofitClient.getBearerToken(), search);
            call.enqueue(new Callback<List<ticket_info_model>>() {

                private final ProgressDialog dialog = new ProgressDialog(helfen.this);


                @Override
                public void onResponse(Call<List<ticket_info_model>> call, Response<List<ticket_info_model>> response) {
                    this.dialog.setMessage("Please wait");
                    this.dialog.show();
                    try {
                        //Get all Profiles
                        ticketList = response.body();
                        System.out.println(ticketList);
                        List<tag_model> all_tags_list = new ArrayList<tag_model>();
                        //get list of individual Tags
                        for (int i = 0; i < ticketList.size(); i++) {
                            for (int j = 0; j < ticketList.get(i).getTags().size(); j++) {
                                all_tags_list.add(ticketList.get(i).getTags().get(j));
                            }
                        }
                        //Get Distinct Tags
                        Set<tag_model> unique_tags_set = new HashSet<tag_model>(all_tags_list);
                        unique_tags_list = new ArrayList<>(unique_tags_set);


                        List<ticket_info_model> notdoneticketList = new ArrayList<ticket_info_model>();
                        for (int i = 0; i < ticketList.size(); i++) {
                            if(!ticketList.get(i).getTicket().getStatus().equals("DONE"))
                                notdoneticketList.add(ticketList.get(i));
                        }


                        updateTicketCards(notdoneticketList);


                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e);
                    }

                }

                @Override
                public void onFailure(Call<List<ticket_info_model>> call, Throwable t) {
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