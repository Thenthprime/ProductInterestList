package edu.psu.swen888.productinterestlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {
    Button button_add_product, button_view_all;
    EditText et_productName, et_productDescription, et_seller, et_price, et_imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_products_to_db);

        button_add_product = findViewById(R.id.button_add_product);
        button_view_all = findViewById(R.id.button_view_all);
        et_productName = findViewById(R.id.et_productName);
        et_productDescription = findViewById(R.id.et_productDescription);
        et_seller = findViewById(R.id.et_seller);
        et_price = findViewById(R.id.et_price);
        et_imagePath = findViewById(R.id.et_imagePath);

        //button listeners
        button_add_product.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //convert text input to string
                String stringPath = et_imagePath.getText().toString();
                //get resource package associated with string
                int imagePath = getResources().getIdentifier(stringPath, null, getPackageName());
                ProductModel productModel = new ProductModel(-1, et_productName.getText().toString(), et_productDescription.getText().toString(), et_seller.getText().toString(), et_price.getText().toString(), imagePath);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddProductActivity.this);
                dataBaseHelper.addOne(productModel);
                ViewGroup group = (ViewGroup)findViewById(R.id.add_products_page);
                Toast.makeText(AddProductActivity.this, "Product Added to the Database.", Toast.LENGTH_SHORT).show();
                //clear the fields
                for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                    View view = group.getChildAt(i);
                    if (view instanceof EditText) {
                        ((EditText)view).setText("");
                    }
                }
            }
        });

        button_view_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
