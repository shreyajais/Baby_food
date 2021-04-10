package com.codewithshreya.babyfoodrecipes;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private  String Location;

    public FoodData()
    {

    }

    public FoodData(String itemName, String itemDescription, String itemImage, String location) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        Location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getLocation() {
        return Location;
    }
}
