package com.jammala.mapapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqLiteHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "fuelMeter";
    private String CREATE_BRAND_TABLE = "CREATE TABLE brand (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
    private String CREATE_MODEL_TABLE = "CREATE TABLE model (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, brand_id INTEGER, FOREIGN KEY(brand_id) REFERENCES brand(id))";
    private String CREATE_YEAR_TABLE = "CREATE TABLE year (id INTEGER PRIMARY KEY AUTOINCREMENT,year TEXT)";
    private String CREATE_YEARMODEL_TABLE = "CREATE TABLE year_model (id INTEGER PRIMARY KEY AUTOINCREMENT,year_id INTEGER, model_id INTEGER, FOREIGN KEY(model_id) REFERENCES model(id), FOREIGN KEY(year_id) REFERENCES year(id))";
    private String CREATE_ENGINEMODEL_TABLE = "CREATE TABLE engine_model (id INTEGER PRIMARY KEY AUTOINCREMENT,engine_id INTEGER, model_id INTEGER, FOREIGN KEY(model_id) REFERENCES model(id), FOREIGN KEY(engine_id) REFERENCES engine(id))";
    private String CREATE_ENGINE_TABLE = "CREATE TABLE engine (id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT)";
    private String CREATE_FUEL_TABLE = "CREATE TABLE fuel (id INTEGER PRIMARY KEY AUTOINCREMENT,fuel_per_km INTEGER, brand_id INTEGER, model_id INTEGER, year_id INTEGER, engine_id INTEGER" +
    		"FOREIGN KEY(brand_id) REFERENCES brand(id), FOREIGN KEY(model_id) REFERENCES model(id), FOREIGN KEY(year_id) REFERENCES year(id),FOREIGN KEY(engine_id) REFERENCES engine(id))";
    
    
    public SqLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BRAND_TABLE);
        db.execSQL(CREATE_MODEL_TABLE);
        db.execSQL(CREATE_YEAR_TABLE);
        db.execSQL(CREATE_YEARMODEL_TABLE);
        db.execSQL(CREATE_ENGINEMODEL_TABLE);
        db.execSQL(CREATE_ENGINE_TABLE);
        db.execSQL(CREATE_FUEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS brand");
        db.execSQL("DROP TABLE IF EXISTS model");
        db.execSQL("DROP TABLE IF EXISTS year");
        db.execSQL("DROP TABLE IF EXISTS year_model");
        db.execSQL("DROP TABLE IF EXISTS engine_model");
        db.execSQL("DROP TABLE IF EXISTS engine");
        db.execSQL("DROP TABLE IF EXISTS fuel");
        
        // create fresh books table
        this.onCreate(db);
        
    }

}
