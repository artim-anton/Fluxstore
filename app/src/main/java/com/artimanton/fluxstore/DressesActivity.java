package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DressesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dresses);
    }

    public void add_record(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }
}