package edu.psu.swen888.productinterestlist;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<ProductModel> productsList;
    public static ArrayList<ProductModel> selectedProductsList;
    private CheckBox checkBox;

    public RecyclerAdapter(ArrayList<ProductModel> productsList){
        this.productsList = productsList;
        this.selectedProductsList = selectedProductsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView productID;
        private TextView productName;
        private TextView productDescription;
        private TextView productSeller;
        private TextView productPrice;
        private ImageView productImage;

        public MyViewHolder(final View view){
            super(view);
            productID = view.findViewById(R.id.textview_product_id);
            productName = view.findViewById(R.id.textview_product_name);
            productDescription = view.findViewById(R.id.textview_product_description);
            productSeller = view.findViewById(R.id.textview_product_seller);
            productPrice = view.findViewById(R.id.textview_product_price);
            productImage = view.findViewById(R.id.product_image);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String productId = Integer.toString(productsList.get(position).getId());
        holder.productID.setText(productId);
        holder.productName.setText(productsList.get(position).getName());
        holder.productDescription.setText(productsList.get(position).getDescription());
        holder.productSeller.setText(productsList.get(position).getSeller());
        holder.productPrice.setText(productsList.get(position).getPrice());
        //holder.productImage.setImageResource(productsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        if(productsList != null){
            return productsList.size();
        }
        return 0;
    }

}
