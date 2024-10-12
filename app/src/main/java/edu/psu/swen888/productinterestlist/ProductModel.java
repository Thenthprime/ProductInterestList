package edu.psu.swen888.productinterestlist;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private int id;
    private String name;
    private String description;
    private String seller;
    private String price;
    private int image;
    private boolean isSelected;

    //constructor
    public ProductModel(int id, String name, String description, String seller, String price, int image, boolean isSelected){
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.image = image;
        this.isSelected = isSelected;
    }

    //getters
    public int getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getSeller(){return seller;}
    public String getPrice(){return price;}
    public int getImage(){return image;}
    public boolean getIsSelected(){return isSelected;}

    //setters
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setSeller(String seller) {this.seller = seller;}
    public void setPrice(String price) {this.price = price;}
    public void setImage(int image) {this.image = image;}
    public void setIsSelected(boolean isSelected){this.isSelected = isSelected;}
}
