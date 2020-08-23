package com.artimanton.fluxstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.artimanton.fluxstore.adapter.ProductAdapter;
import com.artimanton.fluxstore.models.ProductModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductPageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ProductModel> result;
    private ProductAdapter adapter;
    private ProductAdapter.RecyclerViewClickListner listner;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String title, price, description, image, product_code, material, country;
    private TextView tvTitle, tvPrice, tvDescription, tvProductCode, tvMaterial, tvCountry;
    private ImageView imageView;
    public Spinner spinner_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView) findViewById(R.id.image_product_page);
        tvTitle = (TextView) findViewById(R.id.page_title);
        tvPrice = (TextView) findViewById(R.id.page_price);
        tvDescription = (TextView) findViewById(R.id.page_description);
        tvProductCode = (TextView) findViewById(R.id.page_product_code);
        tvMaterial = (TextView) findViewById(R.id.page_material);
        tvCountry = (TextView) findViewById(R.id.page_country);


        Intent intent = getIntent();

        image = intent.getStringExtra("image");
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("description");
        product_code = intent.getStringExtra("product_code");
        material = intent.getStringExtra("material");
        country = intent.getStringExtra("country");


        tvTitle.setText(title);
        tvPrice.setText("$" + price);
        tvDescription.setText(description);
        tvProductCode.setText(tvProductCode.getText()+product_code);
        tvMaterial.setText(tvMaterial.getText()+material);
        tvCountry.setText(tvCountry.getText()+country);


        Picasso.with(this)
                .load(image)
                .error(R.drawable.image)
                .into(imageView);

            SpinnerCurrency();

    }

    public void addToCart(View view) {
    }

    private void SpinnerCurrency() {
        String[] data = {"1","2","3","4","5","6","7","8","9","10"};


        // адаптер
        ArrayAdapter<String> adapter_currency = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter_currency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(R.layout.spiner_item);

        spinner_number = (Spinner) findViewById(R.id.spinner_number);
        spinner_number.setAdapter(adapter_currency);
        spinner_number.setPrompt("Title");
        spinner_number.setSelection(0);
        spinner_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

}