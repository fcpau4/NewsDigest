package com.example.a47276138y.newsapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.example.a47276138y.newsapp.databinding.GvNewspapersLogoBinding;
import com.example.a47276138y.newsapp.databinding.LvPieceOfNewsRowBinding;

import java.util.List;

/**
 * Created by Arfera on 14/01/2017.
 */

public class AdapterPiecesOfNews extends ArrayAdapter<PieceOfNews> {

    public AdapterPiecesOfNews(Context context, int resource, List<PieceOfNews> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PieceOfNews pieceOfNews = getItem(position);

        LvPieceOfNewsRowBinding binding = null;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_piece_of_news_row, parent, false);
        }else{
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.tvPofAuthor.setText(pieceOfNews.getAuthor());
        binding.tvPofTitle.setText(pieceOfNews.getTitle());
        Glide.with(getContext()).load(pieceOfNews.getUrlToImage()).into(binding.imgNewsRow);

        return binding.getRoot();
    }
}
