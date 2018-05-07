package com.example.mac.democustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_Laptop extends BaseAdapter
{
    private ArrayList<Laptop> mLaptopList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public Adapter_Laptop(Context context, ArrayList<Laptop> laptops) {
        this.mContext = context;
        this.mLaptopList = laptops;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mLaptopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.rowlaptop, null);

        TextView ImgView = (TextView) row.findViewById(R.id.img_laptop);
        TextView txtName = (TextView) row.findViewById(R.id.tv_name);
        TextView txtPrice = (TextView) row.findViewById(R.id.tv_price);

        final Laptop lap = mLaptopList.get(position);
        txtName.setText(lap.getName() + "");
        txtPrice.setText(lap.getPrice() + "");
        ImgView.setText(lap.getImage());
        return row;
    }

    public void addList(Laptop p){
        mLaptopList.add(p);
        this.notifyDataSetChanged();
    }
}
