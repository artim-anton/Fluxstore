package com.artimanton.fluxstore.adapter;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artimanton.fluxstore.R;
import com.artimanton.fluxstore.models.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecordViewHolder> {

    private List<ProductModel> list;
    private RecyclerViewClickListner listner;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Context context;
    private ImageView imageView;

    public ProductAdapter(List<ProductModel> list, RecyclerViewClickListner listner, Context context) {
        this.list = list;
        this.listner = listner;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_product, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        final ProductModel record = list.get(position);
        holder.etTitle.setText(record.title);
        holder.etPrice.setText("$" + record.price);


        Picasso.with(context)
                .load(record.image_url)
                .error(R.drawable.image)
                .into(imageView);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView etTitle, etPrice, etSize, etColor, etDescription, etProduct_code, etMaterial, etCountry, etKey;

        private RecordViewHolder(View itemView) {
            super(itemView);
            etTitle = itemView.findViewById(R.id.tv_title_item);
            etPrice = itemView.findViewById(R.id.tv_price_item);
            imageView = itemView.findViewById(R.id.image_product);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listner.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }
}
