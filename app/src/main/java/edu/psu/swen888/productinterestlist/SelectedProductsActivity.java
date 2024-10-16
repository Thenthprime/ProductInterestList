package edu.psu.swen888.productinterestlist;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.ACTION_SENDTO;
import static android.content.Intent.createChooser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public ArrayList<ProductModel> selectedProductsList;
    String email;
    String subject;
    String body;
    Button sendEmailButton;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState){

       super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_products_activity);
        recyclerView=findViewById(R.id.selected_products_list);
        sendEmailButton = findViewById(R.id.send_email_button);
        handler = new Handler();

        //receive intent from main activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        selectedProductsList = (ArrayList<ProductModel>) args.getSerializable("ARRAYLIST");

        //send array recieved through the intent to the recyclerview adapter
        SelectedItemRecyclerAdapter adapter = new SelectedItemRecyclerAdapter(selectedProductsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        email = "sweng888mobileapps@gmail.com";
        subject = "My Wish List";
        body = "";
        for(int i = 0; i < selectedProductsList.toArray().length; i++){
            if(i == 0){
                body = "Here are the products I'm interested in." + selectedProductsList.get(i).getName();
            }
            else {
                body = body + ", " + selectedProductsList.get(i).getName();
            }
        }

        //send the email
        sendEmailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    ///the setType("message/rfc822") is used to invoke google mail
                    intent.setType("message/rfc822");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, 1001);
                        selectedProductsList.clear();
                        SelectedItemRecyclerAdapter adapter = new SelectedItemRecyclerAdapter(selectedProductsList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(SelectedProductsActivity.this, "Messaging app not found", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            Toast.makeText(SelectedProductsActivity.this, "Email Sent!", Toast.LENGTH_SHORT).show();

        }
    }
}
