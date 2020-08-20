package com.artimanton.fluxstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artimanton.fluxstore.models.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddRecordActivity extends AppCompatActivity {
    private EditText etTitle, etPrice, etSize, etColor, etDescription, etProduct_code, etMaterial, etCountry, etKey;
    private EditText etUrl;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Uri imageURL;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String id;
    private String generatedFilePath;

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

        etUrl = findViewById(R.id.tv_Url);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    public void add_to_firebase(View view) {

        reference = database.getReference("test");

        //String mUserId = FirebaseAuth.getInstance().getUid();
        id = reference.push().getKey();
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
        if (imageURL != null) {uploadPicture();} else {this.finish();}
    }

    public void chooseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imageURL = data.getData();
            etUrl.setText(imageURL.toString());
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        //final String randomKey = UUID.randomUUID().toString();

        storageReference.child("images/" + id).putFile(imageURL)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image uploaded", Snackbar.LENGTH_LONG).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Faled to Upload", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getBytesTransferred());
                pd.setMessage("Percentage: "+ (int) progressPercent + "%");
            }
        });
    }

}