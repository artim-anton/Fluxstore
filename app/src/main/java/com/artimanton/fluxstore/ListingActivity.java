package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.artimanton.fluxstore.adapter.ProductAdapter;
import com.artimanton.fluxstore.models.ProductModel;
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

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        load_product();


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

        result = new ArrayList<>();
        recyclerView =  findViewById(R.id.record_list);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();

        // set up the RecyclerView
        /*RecyclerView recyclerView = findViewById(R.id.record_list);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new ProductAdapter(this, data);
        recyclerView.setAdapter(adapter);*/

    }

    public void add_record(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }
}