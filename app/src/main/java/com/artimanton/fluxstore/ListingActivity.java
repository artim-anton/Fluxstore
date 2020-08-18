package com.artimanton.fluxstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.artimanton.fluxstore.adapter.ProductAdapter;
import com.artimanton.fluxstore.models.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ProductModel> result;
    private ProductAdapter adapter;
    private ProductAdapter.RecyclerViewClickListner listner;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white_24dp);
        load_product();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Toast.makeText(getApplicationContext(), "select home", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_search:
                                Toast.makeText(getApplicationContext(), "select search", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_bay:
                                Toast.makeText(getApplicationContext(), "select bay", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_account:
                                Toast.makeText(getApplicationContext(), "select account", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(ProductModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ProductModel record = dataSnapshot.getValue(ProductModel.class);
                int index = getItemIndex(record);
                result.set(index, record);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ProductModel record = dataSnapshot.getValue(ProductModel.class);
                int index = getItemIndex(record);
                result.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(ProductModel record){
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).key.equals(record.key)){
                index = i;
                break;
            }

        }
        return index;
    }

    public void load_product(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("test");

        setOnClickListner();
        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.record_list);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(result, listner);
        recyclerView.setAdapter(adapter);

        updateList();

        // set up the RecyclerView
        /*RecyclerView recyclerView = findViewById(R.id.record_list);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new ProductAdapter(this, data);
        recyclerView.setAdapter(adapter);*/

    }

    private void setOnClickListner() {
        listner = new ProductAdapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ProductPageActivity.class);
                intent.putExtra("title", result.get(position).title);
                intent.putExtra("price", result.get(position).price);
                intent.putExtra("description", result.get(position).description);

                startActivity(intent);
            }
        };
    }

    public void add_record(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }

}