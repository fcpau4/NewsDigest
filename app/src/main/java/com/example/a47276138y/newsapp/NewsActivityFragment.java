package com.example.a47276138y.newsapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a47276138y.newsapp.databinding.FragmentNewsBinding;
import com.example.a47276138y.newsapp.utilities.APInews;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsActivityFragment extends Fragment {

    private ArrayList<PieceOfNews> data;
    private AdapterPiecesOfNews adapter;

    public NewsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        View view = binding.getRoot();

        data = new ArrayList<>();

        adapter = new AdapterPiecesOfNews(
                getContext(),
                R.layout.lv_piece_of_news_row,
                data
        );

        binding.lvNews.setAdapter(adapter);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        GetNewsTask task = new GetNewsTask();
        Log.w("KKKKKKKKKKK", task.toString());
        task.execute();
    }

    public class GetNewsTask extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... voids) {

            ArrayList<PieceOfNews> piecesOfNews = null;

            String id = "";
            String sortByOption = "";

            Intent intent = getActivity().getIntent();
            DigitalNewspapers dn = (DigitalNewspapers) intent.getSerializableExtra("digitalNewspaper");

            if(dn != null) {

                id = dn.getId();

                if (dn.getSortBysAvailable().size() > 1) {
                    for (int i = 0; i < dn.getSortBysAvailable().size(); i++) {
                        sortByOption = dn.getSortBysAvailable().get(i);
                        break;
                    }
                } else {
                    sortByOption = dn.getSortBysAvailable().get(0);
                }
            }


            piecesOfNews = APInews.getPON(id, sortByOption, getContext());

            UriHelper uriHelper = UriHelper.with(NewsAppContentProvider.AUTHORITY);
            Uri ponUri = uriHelper.getUri(PieceOfNews.class);
            cupboard().withContext(getContext()).put(ponUri, PieceOfNews.class, piecesOfNews);

            return null;
        }

        /*@Override
        protected void onPostExecute(ArrayList<PieceOfNews> piecesOfNews) {

            super.onPostExecute(piecesOfNews);
            adapter.clear();

            for (PieceOfNews v : piecesOfNews) {
                adapter.add(v);
                Log.w("XXXXXX", v.toString());
            }*/

        }
    }

