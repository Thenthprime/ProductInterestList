package edu.psu.swen888.productinterestlist;



import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.Manifest;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static CheckBox checkBox;
    public static ArrayList<ProductModel> mySelectedProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.products_list);
        //check permissions
        //fetch all products
        fetchAllProducts();
        //fetch selected products
        fetchMySelectedProducts();
        //send mySelectedProducts to second activity with explicit intent
    }

    private void fetchAllProducts(){
        //populate allProducts with products in the SQLite database
        ProductModel product1 = new ProductModel(1, "Classic Scissors", "Easy cuts", "Scissors Central", "$8.99", 1);
        ProductModel product2 = new ProductModel(2, "Running Sneakers", "Run fast", "Shoes Central", "$38.99", 2);
        ProductModel product3 = new ProductModel(3, "Office Chair", "Sit comfortably", "Chairs Central", "$45.99", 3);
        ProductModel product4 = new ProductModel(4, "Backpack", "Sylish fit", "Backpacks Central", "$18.99", 4);
        ProductModel product5 = new ProductModel(5, "Computer", "Fast processing", "Computers Central", "$328.99", 5);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.addOne(product1);
        dataBaseHelper.addOne(product2);
        dataBaseHelper.addOne(product3);
        dataBaseHelper.addOne(product4);
        dataBaseHelper.addOne(product5);
        ArrayList<ProductModel> allProducts = dataBaseHelper.getAllProducts();
        RecyclerAdapter adapter = new RecyclerAdapter(allProducts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView = findViewById(R.id.products_list);
    }

    public ArrayList<ProductModel> fetchMySelectedProducts(){
            //if true (checked), add to mySelectedProducts ArrayList
        return mySelectedProducts;

    }


}