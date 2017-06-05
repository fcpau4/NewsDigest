package com.example.a47276138y.newsapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a47276138y.newsapp.databinding.GvNewspapersLogoBinding;

/**
 * Created by Arfera on 21/01/2017.
 */

public class DNCursorAdapter extends CupboardCursorAdapter<DigitalNewspapers> {

    public DNCursorAdapter(Context context, Class<DigitalNewspapers> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, DigitalNewspapers model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        GvNewspapersLogoBinding binding = DataBindingUtil.inflate(inflater, R.layout.gv_newspapers_logo,
                parent, false);
        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, DigitalNewspapers model) {
        GvNewspapersLogoBinding binding = DataBindingUtil.getBinding(view);
        binding.gvNewspapersName.setText(model.getName());
        Glide.with(context).load(model.getUrlToLogos()).placeholder(R.drawable.not_found).into(binding.gvLogoNewspapers);
    }
}
