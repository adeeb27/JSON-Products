package com.adeeb.internify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adeeb.internify.R;
import com.adeeb.internify.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

//custom adapter
public class MyProductAdapter extends ArrayAdapter<Product> {

    List<Product> productList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyProductAdapter(Context context, List<Product> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        productList = objects;
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Product item = getItem(position);

        vh.textViewTitle.setText(item.getTitle());
        vh.textViewVendor.setText(item.getVendor());
        vh.textViewDescription.setText(item.getProductType());
        Picasso.with(context).load(item.getImage().getSrc().toString()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewTitle;
        public final TextView textViewVendor;
        public final TextView textViewDescription;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewTitle, TextView textViewVendor, TextView textViewDescription) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewTitle = textViewTitle;
            this.textViewVendor = textViewVendor;
            this.textViewDescription = textViewDescription;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewTitle = (TextView) rootView.findViewById(R.id.textViewTitle);
            TextView textViewVendor = (TextView) rootView.findViewById(R.id.textViewVendor);
            TextView textViewDescription = (TextView) rootView.findViewById(R.id.textViewDescription);
            return new ViewHolder(rootView, imageView, textViewTitle, textViewVendor, textViewDescription);
        }
    }
}
