package com.example.a47276138y.newsapp;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.a47276138y.newsapp.databinding.FragmentMainBinding;
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

        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        View view = binding.getRoot();

        data = new ArrayList<>();

        adapter = new AdapterDigitalNewspapers(
                getContext(),
                R.layout.gv_newspapers_logo,
                data
        );

        binding.gvNewspapers.setAdapter(adapter);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        GetSourcesTask task = new GetSourcesTask();
        task.execute();
    }


    public class GetSourcesTask extends AsyncTask<Void, Void, ArrayList<DigitalNewspapers>>{

        @Override
        protected ArrayList<DigitalNewspapers> doInBackground(Void... voids) {

            APInews api = new APInews();
            ArrayList<DigitalNewspapers> digitalNewspapers = api.getDigitalNewsSources();

            return digitalNewspapers;
        }

        @Override
        protected void onPostExecute(ArrayList<DigitalNewspapers> newspapers) {
            super.onPostExecute(newspapers);
            adapter.clear();

            for (DigitalNewspapers dn : newspapers) {
                Log.w("AsyncTask", "\nURL Logos: " +  dn.getUrlToLogos() + "\nName: " + dn.getName() + "\n\n");
                adapter.add(dn);
            }
        }
    }
}
