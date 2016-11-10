package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristiansu.buyselllab3.Listing.ListItems.ImageListingImage;

public class ListingItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_item_details);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            final int position = bundle.getInt("Position");
            ImageListingImage listingItem = (ImageListingImage) bundle.get("ListingItem");

            final EditText itemName = (EditText) findViewById(R.id.inputItemName);
            final EditText itemPrice = (EditText) findViewById(R.id.inputItemPrice);

            itemName.setText(listingItem.get_itemName());
            itemPrice.setText(String.valueOf(listingItem.get_price()));

            Button backToListing = (Button) findViewById(R.id.buttonBackToListing);
            backToListing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent listingIntent = new Intent(ListingItemDetailsActivity.this, ListingActivity.class);
                    listingIntent.putExtra("ListingItem", new ImageListingImage(R.drawable.printer_img,
                            String.valueOf(itemName.getText()), Float.parseFloat(String.valueOf(itemPrice.getText()))));
                    listingIntent.putExtra("Position", position);
                    startActivity(listingIntent);
                }
            });
        }

        else {
            Intent listingIntent = new Intent(ListingItemDetailsActivity.this, ListingActivity.class);
            startActivity(listingIntent);
        }
    }
}
