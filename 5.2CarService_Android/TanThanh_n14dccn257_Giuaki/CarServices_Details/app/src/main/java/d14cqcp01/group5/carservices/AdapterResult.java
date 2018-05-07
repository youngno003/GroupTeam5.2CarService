package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterResult extends ArrayAdapter {

    private Activity activity;
    private int layoutId;
    private ArrayList<XeKhach> list;

    public AdapterResult(@NonNull Activity context, int resource, @NonNull ArrayList<XeKhach> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.layoutId = resource;
        this.list = objects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(layoutId, parent,false);

        TextView txtCompanyName = view.findViewById(R.id.txtCompanyName);
        TextView txtFromTo = view.findViewById(R.id.txtFromTo);
        TextView txtTime = view.findViewById(R.id.txtTime);
        TextView txtDay = view.findViewById(R.id.txtDay);
        TextView txtStars = view.findViewById(R.id.txtStars);

        final XeKhach xe = list.get(position);
        txtCompanyName.setText(xe.getCompanyId());
        txtFromTo.setText(xe.getFrom() + " " + xe.getTo());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String startTime = sdf.format(new Date(xe.getTimeStart()));
        String endTime = sdf.format(new Date(xe.getTimeEnd()));
        txtTime.setText("Thời gian: " + startTime + "-" + endTime);

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        txtDay.setText("Ngày: " + sdf1.format(new Date(xe.getTimeStart())));
        txtStars.setText(String.valueOf(xe.getStars()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Xem chi tiết xe khách
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(activity.getString(R.string.currentCarID),xe.getId());
                activity.startActivity(intent);
                activity.finish();
            }
        });

        return  view;
    }
}
