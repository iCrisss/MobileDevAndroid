package com.example.cristiansu.buyselllab3.Listing.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cristiansu.buyselllab3.R;
import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;

import java.util.List;


/**
 * Created by CristianSu on 12/6/2016.
 */

public class ListingItemAdapter extends ArrayAdapter<Item> {

    private Context _context;
    private int _layoutResourceId;
    private List<Item> _listingItems;

    static class ListingItemHolder{
        TextView itemName;
        TextView price;
        Button deleteItem;
    }
    
    public ListingItemAdapter(Context context, int resource, List<Item> listingItems) {
        super(context, resource, listingItems);
        _context = context;
        _layoutResourceId = resource;
        _listingItems = listingItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListingItemAdapter.ListingItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
            row = inflater.inflate(_layoutResourceId, parent, false);

            holder = new ListingItemAdapter.ListingItemHolder();
            holder.itemName = (TextView)row.findViewById(R.id.textItemName);
            holder.price = (TextView)row.findViewById(R.id.textItemPrice);

            row.setTag(holder);
        }
        else
        {
            holder = (ListingItemAdapter.ListingItemHolder)row.getTag();
        }

        final Item listingItem = _listingItems.get(position);
        holder.itemName.setText(listingItem.getName());
        holder.price.setText(String.valueOf(listingItem.getPrice()));

        return row;
    }

    protected void onClickDeleteItemButton(Item item) {
        //TODO Add dialog
        ItemsDataSource itemsDataSource = new ItemsDataSource(_context);
        try {
            itemsDataSource.openWriteable();
            itemsDataSource.deleteItem(item);
            itemsDataSource.close();
        }
        catch (Exception ex){

        }
    }

    
}
