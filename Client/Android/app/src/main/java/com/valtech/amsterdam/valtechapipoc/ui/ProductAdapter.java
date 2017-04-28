package com.valtech.amsterdam.valtechapipoc.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.Product;

import java.util.List;

/**
 * Created by jasper.van.zijp on 7-4-2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;

    public ProductAdapter(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.product, parent, false);
        }

        if (product == null) return convertView;

        ((TextView)convertView.findViewById(R.id.textTitle)).setText(product.getTitle());

        if (product.getDescription() != null) {
            ((TextView)convertView.findViewById(R.id.textDescription)).setText(product.getDescription().toString());
        }

        if (product.getImageUrl() != null) {
            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            Picasso
                    .with(mContext)
                    .load(product.getImageUrl())
                    .into(imageView);
        }

        return convertView;
    }
}
