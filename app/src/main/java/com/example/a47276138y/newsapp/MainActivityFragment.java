package com.example.a47276138y.newsapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.a47276138y.newsapp.utilities.APInews;
import com.example.a47276138y.newsapp.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private AdapterDigitalNewspapers adapter;
    private ArrayList<DigitalNewspapers> data;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gv_newspapers);

        /*adapter = new AdapterDigitalNewspapers(
                getContext(),
                R.layout.gv_newspapers_logo,
                data
        );

        gridView.setAdapter(adapter);*/

        return view;
    }

    private void callAPI(){
        URL newsApiSourcesUrl = NetworkUtils.buildSourcesUrl();
        new GetSourcesTask().execute(newsApiSourcesUrl);
    }


    public class GetSourcesTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected String doInBackground(URL... urls) {
            String newsSources=null;
            APInews api = new APInews();

            newsSources = api.getDigitalNewsSources();
            System.out.println("\n************\n" + newsSources + "\n************\n" );

            return null;
        }

    }
}
