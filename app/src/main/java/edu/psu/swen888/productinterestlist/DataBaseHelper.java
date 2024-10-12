package edu.psu.swen888.productinterestlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final String COLUMN_SELLER = "SELLER";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "product db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT, " + COLUMN_SELLER + " TEXT," + COLUMN_PRICE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //method reequried to add some initial data to the empty database
    public boolean addOne(ProductModel productModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, productModel.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, productModel.getDescription());
        cv.put(COLUMN_SELLER, productModel.getSeller());
        cv.put(COLUMN_PRICE, productModel.getPrice());

        long insert = db.insert(PRODUCT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //this method will return all of the products in the database

    public ArrayList<ProductModel> getAllProducts() {
        ArrayList<ProductModel> returnList = new ArrayList();
        //get data from database
        String queryString = "SELECT * FROM " + PRODUCT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through results and create a new customer object for each row
            do {
                int productID = cursor.getInt(0);
                String productName = cursor.getString(1);
                String productDescription = cursor.getString(2);
                String productSeller = cursor.getString(3);
                String productPrice = cursor.getString(4);

                ProductModel newProduct = new ProductModel(productID, productName, productDescription, productSeller, productPrice, 0);
                returnList.add(newProduct);
            }
            while (cursor.moveToNext());

        }
        else {
            //failure do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
