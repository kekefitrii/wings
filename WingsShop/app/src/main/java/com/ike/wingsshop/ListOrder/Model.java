package com.ike.wingsshop.ListOrder;

public class Model {

    String name;
    String dimention;
    int price;

    public Model(String name, String dimention, int price, int price2, int image) {
        this.name = name;
        this.dimention = dimention;
        this.price = price;
        this.price2 = price2;
        this.image = image;
    }

    int price2;
    int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimention() {
        return dimention;
    }

    public void setDimention(String dimention) {
        this.dimention = dimention;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
