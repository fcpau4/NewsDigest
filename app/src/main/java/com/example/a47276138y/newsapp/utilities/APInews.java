package com.example.a47276138y.newsapp.utilities;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.a47276138y.newsapp.DigitalNewspapers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Arfera on 24/12/2016.
 */

public class APInews {

    final static private String NEWSAPI_SOURCES_URL = "https://newsapi.org/v1/sources";


    /**
     * This method calls to the API news in order to get all the data of all digital newspapers sources available.
     * @return data' news sources in json format.
     */
    public String getDigitalNewsSources(){

        Uri builtUri = Uri.parse(NEWSAPI_SOURCES_URL).buildUpon()
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
    private String doCall(URL url){

        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
            return jsonResponse;
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
    public ArrayList<DigitalNewspapers> convertJson(String jsonSources){

        ArrayList<DigitalNewspapers> sources = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonSources);
            JSONArray jsonDigitalNewspapers = data.getJSONArray("sources");


            for (int i = 0; i < jsonDigitalNewspapers.length(); i++) {

                JSONObject jsonDigitalNewspaper = jsonDigitalNewspapers.getJSONObject(i);

                    DigitalNewspapers dn = new DigitalNewspapers();
                    dn.setName(jsonDigitalNewspaper.getString("name"));
                    dn.setUrlToLogos(jsonDigitalNewspaper.getJSONObject("urlsToLogos").getString("medium"));

                    sources.add(dn);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sources;

    }


}
