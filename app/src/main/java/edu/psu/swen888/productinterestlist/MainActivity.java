package edu.psu.swen888.productinterestlist;



import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ProductModel> allProducts = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArrayList<ProductModel> mySelectedProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check permissions
        //fetch all products
        fetchAllProducts();
        //display allProducts through RecyclerAdapter
        RecyclerAdapter adapter = new RecyclerAdapter(allProducts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView = findViewById(R.id.products_list);
        //fetch selected products
        fetchMySelectedProducts();
        //send mySelectedProducts to second activity with explicit intent
    }

    private void fetchAllProducts(){

        //populate allProducts with products in the SQLite database
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //create content resolver
        //iterate with cursor
        //update adapter

    }

    private void fetchMySelectedProducts(){
            //if true (checked), add to mySelectedProducts ArrayList
    }


}