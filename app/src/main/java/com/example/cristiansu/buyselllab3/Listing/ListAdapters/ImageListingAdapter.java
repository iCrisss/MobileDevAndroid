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

/**
 * Created by CristianSu on 11/8/2016.
 */

public class ImageListingAdapter extends ArrayAdapter<IListingItem> {

    private Context _context;
    private int _layoutResourceId;
    private ImageListingImage[] _items;

    static class ListingItemHolder{
        ImageView image;
        TextView itemName;
        TextView price;
    }

    public ImageListingAdapter(Context context, int resource, ImageListingImage[] objects) {
        super(context, resource, objects);
        _context = context;
        _layoutResourceId = resource;
        _items = objects;
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
            holder.image = (ImageView)row.findViewById(R.id.imgIcon);
            holder.itemName = (TextView)row.findViewById(R.id.txtTitle);
            holder.price = (TextView)row.findViewById(R.id.textPriceValue);

            row.setTag(holder);
        }
        else
        {
            holder = (ListingItemHolder)row.getTag();
        }

        ImageListingImage listingItem = _items[position];
        holder.itemName.setText(listingItem.get_itemName());
        holder.image.setImageResource(listingItem.get_icon());
        holder.price.setText(String.valueOf(listingItem.get_price()));

        return row;
    }
}
