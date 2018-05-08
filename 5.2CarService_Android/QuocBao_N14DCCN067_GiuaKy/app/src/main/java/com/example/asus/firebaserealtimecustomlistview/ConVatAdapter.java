package com.example.asus.firebaserealtimecustomlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 15/04/2018.
 */

public class ConVatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ConVat> conVatList;

    public ConVatAdapter(Context context, int layout, List<ConVat> conVatList) {
        this.context = context;
        this.layout = layout;
        this.conVatList = conVatList;
    }

    @Override
    public int getCount() {
        return conVatList.size();
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


            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            TextView txtTen = view.findViewById(R.id.textViewTen);
            TextView txtSoChan = view.findViewById(R.id.textViewSoChan);

            ConVat conVat = conVatList.get(i);
            txtTen.setText(conVat.getTen());
            txtSoChan.setText("Số chân: "+conVat.getSoChan()+" ");


        return view;
    }
}
