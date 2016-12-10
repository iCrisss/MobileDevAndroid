package com.example.cristiansu.buyselllab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristiansu.buyselllab3.Listing.ListItems.ImageListingImage;
import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

public class ListingItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_item_details);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            final Item listingItem = (Item) bundle.get("item");
            final User user = (User) bundle.get("user");

            final EditText itemName = (EditText) findViewById(R.id.inputItemName);
            final EditText itemPrice = (EditText) findViewById(R.id.inputItemPrice);

            itemName.setText(listingItem.getName());
            itemPrice.setText(String.valueOf(listingItem.getPrice()));

            final Button backToListing = (Button) findViewById(R.id.buttonBackToListing);
            backToListing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemsDataSource itemsDataSource = new ItemsDataSource(backToListing.getContext());
                    try {
                        itemsDataSource.openWriteable();
                        itemsDataSource.updateItem(listingItem, itemName.getText().toString(), Double.parseDouble(itemPrice.getText().toString()));
                        Intent listingIntent = new Intent(ListingItemDetailsActivity.this, MyItemsActivity.class);
                        listingIntent.putExtra("user", user);
                        startActivity(listingIntent);
                    }
                    catch (Exception ex){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListingItemDetailsActivity.this);

                        builder.setMessage("Try again.")
                                .setTitle("Could not update item");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent listingIntent = new Intent(ListingItemDetailsActivity.this, MyItemsActivity.class);
                                listingIntent.putExtra("user", user);
                                startActivity(listingIntent);
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        }

        else {
            Intent listingIntent = new Intent(ListingItemDetailsActivity.this, MyItemsActivity.class);
            startActivity(listingIntent);
        }
    }
}
