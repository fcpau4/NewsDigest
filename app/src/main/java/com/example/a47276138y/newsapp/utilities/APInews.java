package com.example.a47276138y.newsapp.utilities;

import android.net.Uri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arfera on 24/12/2016.
 */

public class APInews {

    final static private String NEWSAPI_SOURCES_URL = "https://newsapi.org/v1/sources";

    public String getDigitalNewsSources(){

        Uri builtUri = Uri.parse(NEWSAPI_SOURCES_URL).buildUpon()
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
            String jsonSources = NetworkUtils.getResponseFromHttpUrl(url);

            return jsonSources;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
