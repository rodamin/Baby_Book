package com.example.dsm2016.baby_book.Item;

import android.net.Uri;

/**
 * Created by ghdth on 2018-05-28.
 */

public class Item_Write_diary_Image {
    private Uri uri;

    public Item_Write_diary_Image(Uri uri){
        this.uri=uri;
    }
    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
