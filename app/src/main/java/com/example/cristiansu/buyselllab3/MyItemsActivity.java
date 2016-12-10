package com.example.cristiansu.buyselllab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class MyItemsActivity extends AppCompatActivity {

    private User user;
    private ListView _itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = (User) bundle.get("user");
        }

        Button backToMyAccountButton = (Button) findViewById(R.id.buttonBackToMyAccount);
        backToMyAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyItemsActivity.this, MyAccountActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        Button addItem = (Button) findViewById(R.id.buttonAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddItemActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        _itemList = (ListView) findViewById(R.id.listViewMyItems);

        ItemsDataSource itemsDataSource = new ItemsDataSource(this);
        List<Item> itemList = null;
        try{
            itemsDataSource.openReadable();
            itemList = itemsDataSource.getItemsForUser(user);
            itemsDataSource.close();
        }
        catch (Exception ex){

        }

        final ImageListingAdapter listingAdapter = new ImageListingAdapter(this,
                R.layout.my_items_item_layout,itemList);
        _itemList.setAdapter(listingAdapter);

        _itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(MyItemsActivity.this, ListingItemDetailsActivity.class);
                detailsIntent.putExtra("user", user);
                detailsIntent.putExtra("item",listingAdapter.getItem(position));
                startActivity(detailsIntent);
            }
        });

        _itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyItemsActivity.this);

                builder.setMessage("Are you sure you want to delete this item?")
                        .setTitle("Delete item");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ItemsDataSource itemsDataSource = new ItemsDataSource(_itemList.getContext());
                        try{
                            itemsDataSource.openWriteable();
                            itemsDataSource.deleteItem(listingAdapter.getItem(position));
                            itemsDataSource.close();
                        }
                        catch (Exception ex){

                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }
}
