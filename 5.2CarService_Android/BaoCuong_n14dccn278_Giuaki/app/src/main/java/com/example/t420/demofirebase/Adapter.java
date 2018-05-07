package com.example.t420.demofirebase;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by T420 on 4/16/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<SinhVien> items;
    private OnListener CallBack;

    public Adapter(Context context, List<SinhVien> items, OnListener callback) {
        this.context = context;
        this.items = items;
        this.CallBack = callback;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        try {
            SinhVien sv = items.get(position);
            holder.tv3.setText(sv.getId().toString());
            holder.tv4.setText(sv.getName());
        }catch (Exception err){
            System.out.println(err);
        }
    }

    @Override
    public int getItemCount() {
        return items.isEmpty()? 0: items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv3;
        TextView tv4;
        Button btDel;

        public ViewHolder(View itemView) {
            super(itemView);
            tv3 = itemView.findViewById(R.id.textView3);
            tv4 = itemView.findViewById(R.id.textView4);
            btDel = itemView.findViewById(R.id.button3);
            btDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBack.onDelete(getAdapterPosition());

                }
            });
        }
    }
    public interface OnListener{
        void onDelete(int position);
    }
}