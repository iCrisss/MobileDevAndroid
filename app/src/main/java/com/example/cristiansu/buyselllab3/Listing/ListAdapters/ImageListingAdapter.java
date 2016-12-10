package com.example.cristiansu.buyselllab3.Listing.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cristiansu.buyselllab3.Listing.ListItems.IListingItem;
import com.example.cristiansu.buyselllab3.Listing.ListItems.ImageListingImage;
import com.example.cristiansu.buyselllab3.R;
import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

import java.util.List;

/**
 * Created by CristianSu on 11/8/2016.
 */

public class ImageListingAdapter extends ArrayAdapter<Item> {

    private Context _context;
    private int _layoutResourceId;
    private List<Item> _items;

    static class ListingItemHolder{
        TextView itemName;
        TextView price;
    }

    public ImageListingAdapter(Context context, int resource, List<Item> items) {
        super(context, resource);
        _context = context;
        _layoutResourceId = resource;
        _items = items;
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public Item getItem(int position) {
        return _items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListingItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
            row = inflater.inflate(_layoutResourceId, parent, false);

            holder = new ListingItemHolder();
            holder.itemName = (TextView)row.findViewById(R.id.textItemName);
            holder.price = (TextView)row.findViewById(R.id.textItemPrice);

            row.setTag(holder);
        }
        else
        {
            holder = (ListingItemHolder)row.getTag();
        }

        Item listingItem = _items.get(position);
        holder.itemName.setText(listingItem.getName());
        holder.price.setText(String.valueOf(listingItem.getPrice()));

        return row;
    }
}
