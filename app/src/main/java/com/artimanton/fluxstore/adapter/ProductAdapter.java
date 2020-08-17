package com.artimanton.fluxstore.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artimanton.fluxstore.R;
import com.artimanton.fluxstore.models.ProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecordViewHolder> {

    private List<ProductModel> list;

    public ProductAdapter(List<ProductModel> list) {
        this.list = list;
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
        holder.etPrice.setText(record.price);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecordViewHolder extends RecyclerView.ViewHolder{
        private TextView etTitle, etPrice, etSize, etColor, etDescription, etProduct_code, etMaterial, etCountry, etKey;

        private RecordViewHolder(View itemView) {
            super(itemView);
            etTitle = itemView.findViewById(R.id.tv_title_item);
            etPrice = itemView.findViewById(R.id.tv_price_item);
        }
    }
}
