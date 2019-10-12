package com.sunny.cardpracticeone;

public class Item_Pojo {
    private String Text;
    private int Image;
    private String CountryName;

    public Item_Pojo(int image, String countryName) {
        Image = image;
        CountryName = countryName;
    }



    public Item_Pojo(String Text){
        this.Text = Text;
    }
    public Item_Pojo(int Image){
        this.Image = Image;
    }

    public String getText() {
        return Text;
    }

    public int getImage() {
        return Image;
    }
    public String getCountryName() {
        return CountryName;
    }
}
