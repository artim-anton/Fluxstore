package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.artimanton.fluxstore.models.ProductModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddRecordActivity extends AppCompatActivity {
    public EditText etTitle, etPrice, etSize, etColor, etDescription, etProduct_code, etMaterial, etCountry, etKey;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        database = FirebaseDatabase.getInstance();
        etTitle = findViewById(R.id.tv_title);
        etPrice = findViewById(R.id.tv_price);
        etSize = findViewById(R.id.tv_size);
        etColor = findViewById(R.id.tv_color);
        etDescription = findViewById(R.id.tv_description);
        etProduct_code = findViewById(R.id.tv_product_code);
        etMaterial = findViewById(R.id.tv_material);
        etCountry = findViewById(R.id.tv_country);
    }

    public void add_to_firebase(View view) {

        reference = database.getReference("test");

        //String mUserId = FirebaseAuth.getInstance().getUid();
        String id = reference.push().getKey();
        ProductModel newAdvert = new ProductModel(
                etTitle.getText().toString(),
                etPrice.getText().toString(),
                etSize.getText().toString(),
                etColor.getText().toString(),
                etDescription.getText().toString(),
                etProduct_code.getText().toString(),
                etMaterial.getText().toString(),
                etCountry.getText().toString(),
                id);

        Map<String, Object> advertValue = newAdvert.toMap();
        Map<String, Object> record = new HashMap<>();
        record.put(id, advertValue);
        reference.updateChildren(record);
        this.finish();
    }
}