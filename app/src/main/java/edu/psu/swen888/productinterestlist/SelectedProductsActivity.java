package edu.psu.swen888.productinterestlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ProductModel> selectedProductsList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_products_activity);
        recyclerView=findViewById(R.id.selected_products_list);
        selectedProductsList= RecyclerAdapter.selectedProductsList;
        setAdapter();


    }
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(selectedProductsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
