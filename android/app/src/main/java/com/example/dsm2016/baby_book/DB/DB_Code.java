package com.example.dsm2016.baby_book.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-05-01.
 */

public class DB_Code extends RealmObject {
    private int code;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
