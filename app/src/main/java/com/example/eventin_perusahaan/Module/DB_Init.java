package com.example.eventin_perusahaan.Module;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DB_Init {
    public static final  String DB_BASE_URL = "http://192.168.10.12/";
    EventInDB db;

    public void dbInit()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        db = retrofit.create(EventInDB.class);
    }

    public EventInDB getDb() {
        dbInit();
        return db;
    }
}
