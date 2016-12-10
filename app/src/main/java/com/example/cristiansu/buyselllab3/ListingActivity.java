package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.cristiansu.buyselllab3.Listing.ListAdapters.ImageListingAdapter;
import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

import java.util.List;

public class ListingActivity extends AppCompatActivity {

    private User user;

    private ListView _itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        _itemList = (ListView) findViewById(R.id.listItems);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = (User) bundle.get("user");
        }

        ItemsDataSource itemsDataSource = new ItemsDataSource(this);
        List<Item> itemList = null;
        try{
            itemsDataSource.openReadable();
            itemList = itemsDataSource.getAllItems();
            itemsDataSource.close();
        }
        catch (Exception ex){

        }

        final ImageListingAdapter listingAdapter = new ImageListingAdapter(this,
                R.layout.my_items_item_layout, itemList);
        _itemList.setAdapter(listingAdapter);


        _itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(ListingActivity.this, ItemDetailsActivity.class);
                detailsIntent.putExtra("user", user);
                detailsIntent.putExtra("item",listingAdapter.getItem(position));
                startActivity(detailsIntent);
            }
        });


        Button backToMyAccountButton = (Button) findViewById(R.id.buttonListingBackToMyAccount);
        backToMyAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(ListingActivity.this, MyAccountActivity.class);
                detailsIntent.putExtra("user", user);
                startActivity(detailsIntent);
            }
        });

    }
}
