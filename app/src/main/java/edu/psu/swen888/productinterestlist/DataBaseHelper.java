package edu.psu.swen888.productinterestlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final String COLUMN_SELLER = "SELLER";
    public static final String COLUMN_PRICE= "PRICE";
    public static final String COLUMN_ID = "ID";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "product db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT, " + COLUMN_SELLER + " TEXT," + COLUMN_PRICE + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //i actually don't think this practice activity requires the addOne method the database is prepopulated
    //TODO: figure out how to prepopulate the database
    public boolean addOne(ProductModel productModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, productModel.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, productModel.getDescription());
        cv.put(COLUMN_SELLER, productModel.getSeller());
        cv.put(COLUMN_PRICE, productModel.getPrice());

        long insert = db.insert(PRODUCT_TABLE, null , cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
