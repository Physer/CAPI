package com.valtech.amsterdam.valtechapipoc.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaspervz.www.recyclist.RecyclistViewBinder;
import com.squareup.picasso.Picasso;
import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.Product;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public class ProductViewBinder implements RecyclistViewBinder<Product> {
    private Context mContext;

    public ProductViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void bindView(View view, Product model) {
        TextView titleTextView = (TextView)view.findViewById(R.id.textTitle);
        titleTextView.setText(model.getTitle());

        TextView descriptionTextView = (TextView)view.findViewById(R.id.textDescription);
        descriptionTextView.setText(model.getDescription());

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        if (model.getImageUrl() == null) {
            imageView.setImageResource(R.drawable.image_not_found);
        } else {
            Picasso
                    .with(mContext)
                    .load(model.getImageUrl())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.image_not_found)
                    .into(imageView);
        }
    }
}
