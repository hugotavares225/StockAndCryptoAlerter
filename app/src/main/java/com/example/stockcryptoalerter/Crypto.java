package com.example.stockcryptoalerter;

public class Crypto {

    private String Name;
    private String Ticker;
    private String Price;
    private int Image;

    public Crypto() {

    }

    public Crypto(String cName, String cTicker, String cPrice, int cImage) {
        Name = cName;
        Ticker = cTicker;
        Price = cPrice;
        Image = cImage;
    }

    public Crypto(String cName){
        Name = cName;
    }

    //Getter
    public String getName() {
        return Name;
    }

    public String getTicker() { return Ticker; }

    public String getPrice() {
        return Price;
    }

    public int getImage() { return Image; }

    //Setter
    public void setName(String name) {
        Name = name;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setImage(int image) {Image = image;}
}
