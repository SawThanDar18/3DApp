package com.gracemyanmar.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gracemyanmar.myapplication.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int images[];
    String[]  paymentNames;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context applicationContext, int images[], String[] paymentNames) {
        this.context = applicationContext;
        this.images = images;
        this.paymentNames = paymentNames;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return paymentNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.custom_spinner_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.payment_iv);
        TextView names = (TextView) view.findViewById(R.id.payment_txt);
        icon.setImageResource(images[i]);
        names.setText(paymentNames[i]);
        return view;
    }
}


/*
public class CustomAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.payment_iv);
        TextView names = (TextView) view.findViewById(R.id.payment_txt);
        icon.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        return view;
    }
}
*/
