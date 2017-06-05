package com.example.a47276138y.newsapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a47276138y.newsapp.databinding.LvPieceOfNewsRowBinding;

/**
 * Created by Arfera on 21/01/2017.
 */

public class PONCursorAdapter extends CupboardCursorAdapter<PieceOfNews> {

    public PONCursorAdapter(Context context, Class<PieceOfNews> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, PieceOfNews model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LvPieceOfNewsRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.lv_piece_of_news_row,
                parent, false);
        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, PieceOfNews pon) {
        LvPieceOfNewsRowBinding binding = DataBindingUtil.getBinding(view);
        binding.tvPofTitle.setText(pon.getTitle());
        binding.tvPofAuthor.setText(pon.getAuthor());
        Glide.with(context).load(pon.getUrlToImage()).into(binding.imgNewsRow);
    }
}
