package com.example.stockcryptoalerter;

import android.widget.ImageView;

import java.net.URI;

public class Crypto {

    private String Ticker;
    private String Name;
    private String ImageURL;
    private final String URL_START = "https://www.cryptocompare.com";

    public Crypto() {
    }

    public Crypto(String cTicker, String cName, String cImageURL) {
        this.Ticker = cTicker;
        this.Name = cName;
        this.ImageURL = URL_START+cImageURL;
    }

    //Getter
    public String getTicker() { return Ticker; }
    public String getName() { return Name; }
    public String getImageURL() { return ImageURL; }

    //Setter
    public void setTicker(String ticker) {
        Ticker = ticker;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setImage(String image) {
        ImageURL = image;
    }
}
