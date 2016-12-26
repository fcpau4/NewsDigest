package com.example.a47276138y.newsapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a47276138y.newsapp.databinding.GvNewspapersLogoBinding;

import java.util.List;

/**
 * Created by 47276138y on 20/12/16.
 */

public class AdapterDigitalNewspapers extends ArrayAdapter<DigitalNewspapers>{

    public AdapterDigitalNewspapers(Context context, int resource, List<DigitalNewspapers> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DigitalNewspapers digitalNewspapers = getItem(position);
        Log.w("AdapterGetView", "Come in Adapter" + "\t\t" + digitalNewspapers.getName() + "\t\t" + digitalNewspapers.getUrlToLogos());

        GvNewspapersLogoBinding binding = null;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.gv_newspapers_logo, parent, false);
        }else{
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.gvNewspapersName.setText(digitalNewspapers.getName());
        Glide.with(getContext()).load(digitalNewspapers.getUrlToLogos()).into(binding.gvLogoNewspapers);

        return binding.getRoot();
    }
}
