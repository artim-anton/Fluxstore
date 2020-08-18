package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        TextView tvView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();

        String key = intent.getStringExtra("key");

        tvView.setText(key);
    }
}