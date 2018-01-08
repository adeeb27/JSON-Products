package com.adeeb.internify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
//Product page
public class ProductActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title;
    private TextView vendor;
    private TextView description;
    private TextView tag;

    String titleIntent = "";
    String vendorInt = "";
    String descriptionIntent = "";
    String imageIntent = "";
    String tagIntent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page_activity);
        Intent intent = getIntent();
        titleIntent = intent.getExtras().getString("title");
        vendorInt = intent.getExtras().getString("vendor");
        descriptionIntent = intent.getExtras().getString("description");
        imageIntent = intent.getExtras().getString("image");
        tagIntent = intent.getExtras().getString("tags");

        title = (TextView) findViewById(R.id.titleprod);
        description = (TextView) findViewById(R.id.prodDescription);
        vendor = (TextView) findViewById(R.id.vendor);
        tag = (TextView) findViewById(R.id.tags);
        imageView = (ImageView) findViewById(R.id.prodDescriptionPageImage);

        title.setText(titleIntent);
        description.setText(descriptionIntent);
        vendor.setText(vendorInt);
        tag.setText("Tags: " + tagIntent);
        Picasso.with(getApplicationContext()).load(imageIntent).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);


    }
}
