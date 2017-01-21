package com.example.a47276138y.newsapp;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Arfera on 20/01/2017.
 */

public class DataManager {

    private static UriHelper URI_HELPER= UriHelper.with(NewsAppContentProvider.AUTHORITY);
    private static Uri NEWSPAPERS_URI = URI_HELPER.getUri(DigitalNewspapers.class);
    private static Uri PON_URI = URI_HELPER.getUri(PieceOfNews.class);

    static void saveDigitalNewspapers(ArrayList<DigitalNewspapers> digitalNewspapers, Context context){
        cupboard().withContext(context).put(NEWSPAPERS_URI, DigitalNewspapers.class, digitalNewspapers);
    }

    static void savePiecesOfNews(ArrayList<PieceOfNews> piecesOfNews, Context context){
        cupboard().withContext(context).put(PON_URI, PieceOfNews.class, piecesOfNews);
    }

    static void deleteDigitalNewspapers (Context context){
        cupboard().withContext(context).delete(NEWSPAPERS_URI, "_id > ?", "0");
    }


    static void deletePiecesOfNews(Context context){
        cupboard().withContext(context).delete(PON_URI, "_id > ?", "0");
    }


}
