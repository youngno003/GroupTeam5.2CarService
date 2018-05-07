package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DanhGiaApdapter extends ArrayAdapter {

    private Activity activity;
    private int layoutId;
    private ArrayList<PhanHoi> phanHoiList;

    public DanhGiaApdapter(@NonNull Activity context, int resource, @NonNull ArrayList<PhanHoi> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.layoutId = resource;
        this.phanHoiList = objects;
    }

    @Override
    public int getCount() {
        return phanHoiList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.layout_comment, null);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(layoutId, parent,false);

        // Ánh xạ view
        TextView txtUser = (TextView) view.findViewById(R.id.textviewTen);
        TextView txtPhanHoi = (TextView) view.findViewById(R.id.textviewPhanhoi);

        //Gán giá trị
        PhanHoi phanHoi = phanHoiList.get(position);
//        System.out.println("day ne baaaaaaaaa:" + phanHoi.getUserName());
        txtUser.setText(phanHoi.getUserName());
        txtPhanHoi.setText(phanHoi.getDanhGia());


        return view;
    }
}
