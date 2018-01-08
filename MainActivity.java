package com.adeeb.internify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adeeb.internify.adapter.MyProductAdapter;
import com.adeeb.internify.model.Product;
import com.adeeb.internify.model.ProductList;
import com.adeeb.internify.retrofit.api.ApiService;
import com.adeeb.internify.retrofit.api.RetroClient;
import com.adeeb.internify.utils.InternetConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
********************************************************
*                                                      *
*                Adeeb Ibne Amjad                      *
*   Software Engineering Student - McGill Univeristy   *
*   Finished everything extra challenges included,     *
*   Developed for Android 4.1: Backwards               *
*   Compatible                                         *
*                                                      *
********************************************************
                                                        */
//Thanks to http://www.jsonschema2pojo.org/ - Used it for the model, was a life saver.
public class MainActivity extends AppCompatActivity {

    //Views
    private ListView listView;
    private View parentView;
    private android.support.v7.widget.SearchView searchView;
    //Lists
    private ArrayList<Product> productList;
    //Adapters
    private MyProductAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
         * List where JSON will be stored
         */
        productList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);

        listView = findViewById(R.id.listView);
        //sending only the few required things to ProductActivity.java
        //Covered only a few descriptions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("title", productList.get(position).getTitle());
                intent.putExtra("vendor", productList.get(position).getVendor());
                intent.putExtra("description", productList.get(position).getProductType());
                intent.putExtra("tags", productList.get(position).getTags());
                intent.putExtra("image", productList.get(position).getImage().getSrc());

                startActivity(intent);
            }
        });

        //Checking if the phone is connected to the internet
        if (InternetConnection.checkConnection(getApplicationContext())) {
            final ProgressDialog dialog;
            // Progress Dialog
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle(getString(R.string.string_getting_json_title));
            dialog.setMessage(getString(R.string.string_getting_json_message));
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            //Calling JSON
            Call<ProductList> call = api.getMyJSON();


            //Enqueue Callback will be called when response is received
            call.enqueue(new Callback<ProductList>() {
                @Override
                public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        //Successfully received a response
                        dialog.setMessage(getString(R.string.string_got_the_response_loading_products));
                        dialog.show();
                        productList = response.body().getProducts();

                        //Putting the response into the adapter to put it in the text and image boxes
                        adapter = new MyProductAdapter(MainActivity.this, productList);
                        listView.setAdapter(adapter);
                        //hide dialog after adapter puts everything on the page
                        dialog.hide();
                        //method that queries for products
                        getData("");

                    } else {
                        Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ProductList> call, Throwable t) {
                    dialog.setMessage(getString(R.string.string_failed_no_response));
                    dialog.show();
                }
            });

        } else {
            Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
        }
    }
//        });


    //}

    //Method that queries in the product list
    private void getData(String query) {

        List<Product> output = new ArrayList<>();
        List<Product> filteredOutput = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            output.add(productList.get(i));
        }
        if (searchView != null) {
            for (int j = 0; j < productList.size(); j++) {

                String tag = productList.get(j).getTags().toLowerCase().toString();
                String title = productList.get(j).getTitle().toLowerCase().toString();
                String vendor = productList.get(j).getVendor().toLowerCase().toString();
                String productType = productList.get(j).getProductType().toLowerCase().toString();

                //Tried to cover most cases, if tag/title/etc start with/equals query. Still missing a lot of cases
                //such as when the second word of the string is queried, it won't return a result.

                if (tag.startsWith(query.toLowerCase()) || tag.equalsIgnoreCase(query)) {
                    filteredOutput.add(productList.get(j));
                } else if (title.startsWith(query.toLowerCase()) || title.equalsIgnoreCase(query)) {
                    filteredOutput.add(productList.get(j));
                } else if (vendor.startsWith(query.toLowerCase()) || vendor.equalsIgnoreCase(query)) {
                    filteredOutput.add(productList.get(j));
                } else if (productType.startsWith(query.toLowerCase()) || productType.equalsIgnoreCase(query)) {
                    filteredOutput.add(productList.get(j));
                }


            }
            // if no matches at all, then filteredOutput will be blank
        } else {
            filteredOutput = output;
        }
        MyProductAdapter searchAdapter;
        searchAdapter = new MyProductAdapter(MainActivity.this, filteredOutput);
        listView.setAdapter(searchAdapter);

    }

    // Search Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.search));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);

    }

}