package com.example.cristiansu.buyselllab3.Listing.ListItems;

import java.io.Serializable;

/**
 * Created by CristianSu on 11/8/2016.
 */

public class ImageListingImage implements IListingItem, Serializable {
    private int _icon;
    private String _itemName;
    private float _price;

    public ImageListingImage(int iconId, String itemName, float price) {
        _icon = iconId;
        _itemName = itemName;
        _price = price;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int _icon) {
        this._icon = _icon;
    }

    public String get_itemName() {
        return _itemName;
    }

    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }
}
