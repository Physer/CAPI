package com.valtech.amsterdam.valtechapipoc.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.Product;

import java.util.List;

/**
 * Created by jasper.van.zijp on 7-4-2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProducts;

    public ProductAdapter(List<Product> products, Context context) {
        mProducts = products;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);
        return new ProductAdapter.ViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);

        if (product == null) return;

        TextView titleTextView = holder.getTitleTextView();
        titleTextView.setText(product.getTitle());

        TextView descriptionTextView = holder.getDescriptionTextView();
        descriptionTextView.setText(product.getDescription());

        ImageView imageView = holder.getImageView();
        if (product.getImageUrl() == null) {
            imageView.setImageResource(R.drawable.image_not_found);
        } else {
            Picasso
                .with(mContext)
                .load(product.getImageUrl())
                .placeholder(R.drawable.loading)
                .error(R.drawable.image_not_found)
                .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mProductView;

        public ViewHolder(View itemView) {
            super(itemView);
            mProductView = itemView;
        }

        public TextView getTitleTextView() {
            return (TextView)mProductView.findViewById(R.id.textTitle);
        }

        public TextView getDescriptionTextView() {
            return (TextView)mProductView.findViewById(R.id.textDescription);
        }

        public ImageView getImageView() {
            return (ImageView)mProductView.findViewById(R.id.imageView);
        }
    }
}
