package com.example.a47276138y.newsapp;

import java.io.Serializable;

/**
 * Created by Arfera on 12/01/2017.
 */

public class PieceOfNews implements Serializable {

    private String author;
    private String title;
    private String urlToImage;
    private String urlToExtendedPOF;

    public PieceOfNews(){}

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getUrlToExtendedPOF() { return urlToExtendedPOF; }
    public void setUrlToExtendedPOF(String urlToExtendedPOF) { this.urlToExtendedPOF = urlToExtendedPOF; }
    public String getUrlToImage() {return urlToImage;}
    public void setUrlToImage(String urlToImage) {this.urlToImage = urlToImage;}
    public String getTitle() {return title;}
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "\n\t\t" + getTitle() + "\n\t\t" + getAuthor() + "\n\t\t" + getUrlToImage();
    }
}
