package edu.psu.swen888.productinterestlist;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private String seller;
    private String price;
    private int image;

    public Product(int id, String name, String description, String seller, String price, int image){
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.image = image;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getSeller(){return seller;}
    public String getPrice(){return price;}
    public int getImage(){return image;}
}
