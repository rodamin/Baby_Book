package com.example.dsm2016.baby_book.Item;

/**
 * Created by ghdth on 2018-04-18.
 */

public class Item_Mydairies {
    private int image;
    private int postion;
    private String baby_name;
    private String date;

    public String getBaby_name() {
        return baby_name;
    }

    public void setBaby_name(String baby_name) {
        this.baby_name = baby_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
    /*public Item_Mydairies(int image ,String baby_name){
        this.image=image;
        this.baby_name=baby_name;
    }*/
}
