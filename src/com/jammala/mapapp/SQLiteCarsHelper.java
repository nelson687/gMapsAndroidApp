package com.jammala.mapapp;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SQLiteCarsHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "fuelMeter.db";
    private static final int DATABASE_VERSION = 1;
    
    public SQLiteCarsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public ArrayList<String> getBrands(){

        ArrayList<String> brands = new ArrayList<String>();
        
        SQLiteDatabase db = getReadableDatabase();
        
        String sql = "select name from Brand";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()){
            do{
                    brands.add(c.getString(0));
            }while(c.moveToNext());
        }
        c.close();
        return brands;
  }
    public ArrayList<String> getModels(String brand){

        ArrayList<String> models = new ArrayList<String>();
        
        SQLiteDatabase db = getReadableDatabase();
        
        String sql = "select m.name from Model m, Brand b where b.name = ? and m.brand_id = b.id";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(brand)});
        if (c.moveToFirst()){
            do{
                
                    models.add(c.getString(0));
                
            }while(c.moveToNext());
        }
        c.close();
        return models;
  }
    public ArrayList<String> getEngines(String model){

        ArrayList<String> engines = new ArrayList<String>();
        
        SQLiteDatabase db = getReadableDatabase();
        
        String sql = "SELECT e.name FROM Engine e, Model_x_Engine me WHERE e.name = ? AND e.id = me.id_model AND me.id_engine = e.id";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(model)});
        if (c.moveToFirst()){
            do{
                
                    engines.add(c.getString(0));
                
            }while(c.moveToNext());
        }
        c.close();
        return engines;
  }
    
    public ArrayList<String> getYears(String model, String engine){

        ArrayList<String> years = new ArrayList<String>();
        
        SQLiteDatabase db = getReadableDatabase();
        
        String sql = "SELECT me.year FROM Model_x_Engine me, Engine e, Model m WHERE m.name = ? AND e.name = ? AND e.id = me.id_engine AND m.id = me.id_model";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(model), String.valueOf(engine)});
        if (c.moveToFirst()){
            do{
                
                years.add(c.getString(0));
                
            }while(c.moveToNext());
        }
        c.close();
        return years;
  }
}
