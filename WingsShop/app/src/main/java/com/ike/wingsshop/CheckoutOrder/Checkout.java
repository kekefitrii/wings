package com.ike.wingsshop.CheckoutOrder;

public class Checkout {

    String id, name;
    int qty, price, grandprice;

    public Checkout(String id, String name, int qty, int price, int grandprice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.grandprice = grandprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGrandprice() {
        return grandprice;
    }

    public void setGrandprice(int grandprice) {
        this.grandprice = grandprice;
    }
}
