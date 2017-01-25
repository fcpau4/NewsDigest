package com.example.a47276138y.newsapp;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
public class NewsActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ArrayList<PieceOfNews> data;
    private PONCursorAdapter adapter;

    public NewsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        View view = binding.getRoot();

        data = new ArrayList<>();

        /*adapter = new AdapterPiecesOfNews(
                getContext(),
                R.layout.lv_piece_of_news_row,
                data
        );*/

        adapter = new PONCursorAdapter(getContext(), PieceOfNews.class);
        binding.lvNews.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        GetNewsTask task = new GetNewsTask();
        Log.w("KKKKKKKKKKK", task.toString());
        task.execute();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getPONCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
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

                if(dn.isLatest()){
                    sortByOption = "latest";
                }else if(dn.isTop()){
                    sortByOption = "top";
                }else if(dn.isPopular()){
                    sortByOption = "popular";
                }
            }

            piecesOfNews = APInews.getPON(id, sortByOption, getContext());

            DataManager.deletePiecesOfNews(getContext());
            DataManager.savePiecesOfNews(piecesOfNews, getContext());

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

