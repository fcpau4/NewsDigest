package com.example.a47276138y.newsapp.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.a47276138y.newsapp.DigitalNewspapers;
import com.example.a47276138y.newsapp.PieceOfNews;
import com.example.a47276138y.newsapp.R;

import org.antlr.v4.codegen.SourceGenTriggers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Arfera on 24/12/2016.
 */

public class APInews {

    /**
     * This properties have the path to API to search for SOURCES/ARTICLES including
     * the parameters used to do that.
     */
    public final static String BASE_NEWSAPI_SOURCES_EP = "https://newsapi.org/v1/sources";
    public final static String COUNTRY_SOU_PAR = "country";

    public final static String BASE_NEWSAPI_ARTICLES_EP = "https://newsapi.org/v1/articles";
    public final static String SOURCE_ART_PAR = "source";
    public final static String APIKEY_ART_PAR = "apiKey";
    public final static String SORTBY_ART_PAR = "sortBy";


    public static ArrayList<PieceOfNews> getPON(String source, String sortBy, Context context) {

        Uri builtUri = Uri.parse(BASE_NEWSAPI_ARTICLES_EP).buildUpon()
                .appendQueryParameter(SOURCE_ART_PAR, source)
                .appendQueryParameter(SORTBY_ART_PAR, sortBy)
                .appendQueryParameter(APIKEY_ART_PAR, context.getResources().getString(R.string.api_key))
                .build();

        Log.w("HAS BUILT URI", builtUri.toString());
        URL url = null;

        try {
            url = new URL(builtUri.toString());
            return doCallForPON(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * It does call to API News and gets raw json of all pieces of news from a source.
     * @param url
     * @return json response from API.
     */
    @Nullable
    public static ArrayList<PieceOfNews> doCallForPON(URL url){

        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
            return convertPONJson(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * This method gets pieces of news' data from a raw json and fill PON's objects with this data.
     * @param jsonNews
     * @return ArrayList<PieceOfNews>
     */
    public static ArrayList<PieceOfNews> convertPONJson(String jsonNews){

        ArrayList<PieceOfNews> allPon = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonNews);
            JSONArray jsonAllNews = data.getJSONArray("articles");


            for (int i = 0; i < jsonAllNews.length(); i++) {

                JSONObject jsonPieceOfNews = jsonAllNews.getJSONObject(i);

                PieceOfNews pon = new PieceOfNews();

                if(jsonPieceOfNews.has("author")){
                    pon.setAuthor(jsonPieceOfNews.getString("author"));
                }
                pon.setTitle(jsonPieceOfNews.getString("title"));
                pon.setUrlToExtendedPOF(jsonPieceOfNews.getString("url"));

                if(jsonPieceOfNews.has("urlToImage")){
                    pon.setUrlToImage(jsonPieceOfNews.getString("urlToImage"));
                }

                allPon.add(pon);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allPon;

    }






    /*************** DOWN HERE API Methods For Getting Digital Newspapers info **************************/



    /**
     * This method calls to the API news in order to get all the data of all digital newspapers sources available.
     * @return data's news sources in json format.
     */
    public static ArrayList<DigitalNewspapers> getDigitalNewsSources(){

        Uri builtUri = Uri.parse(BASE_NEWSAPI_SOURCES_EP).buildUpon()
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
            return doCall(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * This method calls to the API news in order to get all digital newspapers sources available filtered
     * by the country passed by parameter.
     * @param country
     * @return data's sources filtered by country
     */
    public static ArrayList<DigitalNewspapers> getDigitalNewsSourcesByCountry(String country){
        Uri builtUri = Uri.parse(BASE_NEWSAPI_SOURCES_EP)
                .buildUpon()
                .appendQueryParameter(COUNTRY_SOU_PAR, country)
                .build();
        URL url = null;


        try {
            url = new URL(builtUri.toString());

            return doCall(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * It does call to API News and gets raw json to handle.
     * @param url
     * @return json response from API.
     */
    @Nullable
    public static ArrayList<DigitalNewspapers> doCall(URL url){

        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
            return convertJson(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * It converts raw json to digital newspapers objects.
     * @param jsonSources
     * @return ArrayList<DigitalNewspapers>
     */
    public static ArrayList<DigitalNewspapers> convertJson(String jsonSources){

        ArrayList<DigitalNewspapers> sources = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonSources);
            JSONArray jsonDigitalNewspapers = data.getJSONArray("sources");


            for (int i = 0; i < jsonDigitalNewspapers.length(); i++) {

                JSONObject jsonDigitalNewspaper = jsonDigitalNewspapers.getJSONObject(i);

                    DigitalNewspapers dn = new DigitalNewspapers();

                    dn.setId(jsonDigitalNewspaper.getString("id"));

                    dn.setName(jsonDigitalNewspaper.getString("name"));

                if(jsonDigitalNewspaper.has("urlsToLogos")){
                    dn.setUrlToLogos(jsonDigitalNewspaper.getJSONObject("urlsToLogos").getString("small"));
                }

                JSONArray jsonArraySortBys = jsonDigitalNewspaper.getJSONArray("sortBysAvailable");

                for (int j = 0; j <jsonArraySortBys.length() ; j++) {

                    if(jsonArraySortBys.getString(j).equals("top")){
                        dn.setTop(true);
                    }else if(jsonArraySortBys.getString(j).equals("latest")){
                        dn.setLatest(true);
                    }else if(jsonArraySortBys.getString(j).equals("popular")){
                        dn.setPopular(true);
                    }
                }


                sources.add(dn);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sources;

    }

}
