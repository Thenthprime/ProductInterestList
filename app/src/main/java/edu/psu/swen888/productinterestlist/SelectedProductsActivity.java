package edu.psu.swen888.productinterestlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public ArrayList<ProductModel> selectedProductsList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_products_activity);
        recyclerView=findViewById(R.id.selected_products_list);
        setAdapter();
    }
    private void setAdapter() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        selectedProductsList = (ArrayList<ProductModel>) args.getSerializable("ARRAYLIST");
        RecyclerAdapter adapter = new RecyclerAdapter(selectedProductsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
