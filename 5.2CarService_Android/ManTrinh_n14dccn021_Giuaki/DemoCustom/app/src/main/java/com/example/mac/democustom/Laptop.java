package com.example.mac.democustom;

public class Laptop
{
    private String name;
    private Double price;
    private String image;

    public Laptop(){
        
    }
    public Laptop(String name, Double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name + "   " + price + "    " + image;
    }
}
