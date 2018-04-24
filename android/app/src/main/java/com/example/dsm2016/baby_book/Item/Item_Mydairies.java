package com.example.dsm2016.baby_book.Item;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Item_Mydairies {
   private int image;
   private int postion;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public Item_Mydairies(int image){
        this.image=image;
    }
}
