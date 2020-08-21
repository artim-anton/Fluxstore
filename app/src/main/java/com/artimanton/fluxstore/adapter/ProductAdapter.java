package com.artimanton.fluxstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artimanton.fluxstore.R;
import com.artimanton.fluxstore.models.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecordViewHolder> implements Filterable {

    private List<ProductModel> list;
    private List<ProductModel> listFull;
    private RecyclerViewClickListner listner;
    private Context context;
    private ImageView imageView;

    public ProductAdapter(List<ProductModel> list, RecyclerViewClickListner listner, Context context) {
        this.list = list;
        listFull = list;
        //listFull = new ArrayList<>(list);
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
        ProductModel record = list.get(position);
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

    @Override
    public Filter getFilter() {
        return adapterFilter;
    }
    private Filter adapterFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProductModel item : listFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
