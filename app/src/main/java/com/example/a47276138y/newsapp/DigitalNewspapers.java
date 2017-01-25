package com.example.a47276138y.newsapp;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 47276138y on 20/12/16.
 */

public class DigitalNewspapers implements Serializable{

    private String id;
    private String name;
    private String category;
    private String urlToLogos;
    private String country;

    private boolean top=false;
    private boolean latest=false;
    private boolean popular=false;

    private ArrayList<String> sortBysAvailable;

    public DigitalNewspapers(){}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getUrlToLogos() { return urlToLogos;}
    public void setUrlToLogos(String urlToLogos) {this.urlToLogos = urlToLogos;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    //public ArrayList<String> getSortBysAvailable() {return sortBysAvailable;}
    //public void setSortBysAvailable(ArrayList<String> sortBysAvailable) {this.sortBysAvailable = sortBysAvailable;}
    public boolean isTop() { return top; }
    public void setTop(boolean top) { this.top = top; }
    public boolean isLatest() { return latest; }
    public void setLatest(boolean latest) {this.latest = latest;}
    public boolean isPopular() {return popular;}
    public void setPopular(boolean popular) {this.popular = popular;}

}
