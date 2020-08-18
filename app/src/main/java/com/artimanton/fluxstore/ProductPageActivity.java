package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.artimanton.fluxstore.adapter.ProductAdapter;
import com.artimanton.fluxstore.models.ProductModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PKCS12Attribute;
import java.util.ArrayList;
import java.util.List;

public class ProductPageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ProductModel> result;
    private ProductAdapter adapter;
    private ProductAdapter.RecyclerViewClickListner listner;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String title, price, description;
    private TextView tvTitle, tvPrice, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle = (TextView) findViewById(R.id.page_title);
        tvPrice = (TextView) findViewById(R.id.page_price);
        tvDescription = (TextView) findViewById(R.id.page_description);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("description");

        tvTitle.setText(title);
        tvPrice.setText(price);
        tvDescription.setText(description);
    }


    public void addToCart(View view) {
    }
}