package com.bfcai.meals;

public class MealClass {
    private String title;
    private String price;
    private String coverImage;

    public MealClass(){}
    public MealClass(String title, String price, String coverImage, String songURL){
        this.title = title;
        this.price = price;
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

}