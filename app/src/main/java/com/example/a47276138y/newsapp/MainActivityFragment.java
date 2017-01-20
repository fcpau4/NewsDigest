package com.example.a47276138y.newsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.a47276138y.newsapp.databinding.FragmentMainBinding;
import com.example.a47276138y.newsapp.utilities.APInews;
import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

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

        binding.gvNewspapers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                DigitalNewspapers digitalNewspaper = (DigitalNewspapers) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), NewsActivity.class);
                intent.putExtra("digitalNewspaper", digitalNewspaper);

                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        GetSourcesTask task = new GetSourcesTask();
        task.execute();
    }


    public class GetSourcesTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String country = sharedPreferences.getString("countries_list", "-1");


            
            ArrayList<DigitalNewspapers> digitalNewspapers;

            if(!(country.equals("-1"))){
                digitalNewspapers = APInews.getDigitalNewsSourcesByCountry(country);
            }else{
                digitalNewspapers = APInews.getDigitalNewsSources();
            }

            UriHelper helper = UriHelper.with(NewsAppContentProvider.AUTHORITY);
            Uri newspaperUri = helper.getUri(DigitalNewspapers.class);
            //inserting newspapers into database
            cupboard().withContext(getContext()).put(newspaperUri, DigitalNewspapers.class, digitalNewspapers);

            return null;
        }

        /*@Override
        protected void onPostExecute(ArrayList<DigitalNewspapers> newspapers) {
            super.onPostExecute(newspapers);
            adapter.clear();

            for (DigitalNewspapers dn : newspapers) {
                Log.w("AsyncTask", "\nURL Logos: " +  dn.getUrlToLogos() + "\nName: " + dn.getName() + "\n\n");
                adapter.add(dn);
            }
        }*/
    }
}
