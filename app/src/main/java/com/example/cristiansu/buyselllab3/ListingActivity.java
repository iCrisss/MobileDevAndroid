package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cristiansu.buyselllab3.Listing.ListAdapters.ImageListingAdapter;
import com.example.cristiansu.buyselllab3.Listing.ListItems.ImageListingImage;

import java.io.Serializable;

public class ListingActivity extends AppCompatActivity {

    private ListView _itemList;
    private ImageListingImage[] listingImages = new ImageListingImage[]{
            new ImageListingImage(R.drawable.printer_img, "Item1", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item2", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item3", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item4", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item5", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item6", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item7", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item8", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item9", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item10", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item11", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item12", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item13", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item14", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item15", (float) 5.9),
            new ImageListingImage(R.drawable.printer_img, "Item16", (float) 5.9)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        _itemList = (ListView) findViewById(R.id.listItems);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int position = bundle.getInt("Position");
            ImageListingImage listingItem = (ImageListingImage) bundle.get("ListingItem");
            listingImages[position] = listingItem;
        }

        ImageListingAdapter listingAdapter = new ImageListingAdapter(this,
                R.layout.listview_item_layout, listingImages);
        _itemList.setAdapter(listingAdapter);


        _itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(ListingActivity.this, ListingItemDetailsActivity.class);
                detailsIntent.putExtra("ListingItem", listingImages[position]);
                detailsIntent.putExtra("Position", position);
                startActivity(detailsIntent);
            }
        });
    }
}
