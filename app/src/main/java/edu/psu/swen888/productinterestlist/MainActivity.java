package edu.psu.swen888.productinterestlist;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ProductModel> allProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.products_list);
        //fetch all products
        fetchAllProducts();
    }
    public void onShowMyProductsClicked(View view){
        ArrayList<ProductModel> selectedProductsList = RecyclerAdapter.selectedProductsList;
        if(selectedProductsList.size()<3){
            Toast.makeText(MainActivity.this, "Please Select At Least 3 Items", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(MainActivity.this, SelectedProductsActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) selectedProductsList);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }
    }

    private void fetchAllProducts() {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        //populate allProducts with products in the SQLite database

        //hardcode prepopulated database only needs to be done once


/*        ProductModel product1 = new ProductModel(1, "Classic Scissors", "Easy cuts", "Scissors Central", "$8.99", R.drawable.pid1);
        ProductModel product2 = new ProductModel(2, "Running Sneakers", "Run fast", "Shoes Central", "$38.99", R.drawable.pid2);
        ProductModel product3 = new ProductModel(3, "Office Chair", "Sit comfortably", "Chairs Central", "$45.99", R.drawable.pid3);
        ProductModel product4 = new ProductModel(4, "Backpack", "Sylish fit", "Backpacks Central", "$18.99", R.drawable.pid4);
        ProductModel product5 = new ProductModel(5, "Computer", "Fast processing", "Computers Central", "$328.99", R.drawable.pid5);
        dataBaseHelper.addOne(product1);
        dataBaseHelper.addOne(product2);
        dataBaseHelper.addOne(product3);
        dataBaseHelper.addOne(product4);
        dataBaseHelper.addOne(product5);*/



        //get any additional products from the SQLite database
        allProducts = dataBaseHelper.getAllProducts();
        //send allProducts array through the recyclerview adapter
        RecyclerAdapter adapter = new RecyclerAdapter(allProducts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView = findViewById(R.id.products_list);
    }

}