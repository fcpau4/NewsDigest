package com.example.a47276138y.newsapp;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Arfera on 20/01/2017.
 */

public class NewsAppContentProvider extends CupboardContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static{
        cupboard().register(DigitalNewspapers.class);
        cupboard().register(PieceOfNews.class);
    }

    public NewsAppContentProvider() {
        super(AUTHORITY, 1);
    }
}
