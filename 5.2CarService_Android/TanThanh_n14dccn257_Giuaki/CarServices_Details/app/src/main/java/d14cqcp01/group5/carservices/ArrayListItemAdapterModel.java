package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by T420 on 3/15/2018.
 */

public class ArrayListItemAdapterModel extends ArrayAdapter<TicketOfHistoryLayoutModel>
{
    Activity context=null;
    ArrayList<TicketOfHistoryLayoutModel> myArray=null;
    int layoutId;
    private OnListener CallBack;
    public ArrayListItemAdapterModel(Activity context,
                                     int layoutId,
                                     ArrayList<TicketOfHistoryLayoutModel>arr, OnListener callback) {
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
        this.CallBack = callback;
    }
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        try {
            LayoutInflater inflater =
                    context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, null);
            if (myArray.size() > 0 && position >= 0) {
                final ImageView i = (ImageView)
                        convertView.findViewById(R.id.iconBrand);
                final TextView b = (TextView)
                        convertView.findViewById(R.id.brand);
                final TextView r = (TextView)
                        convertView.findViewById(R.id.rating);
                final TextView f = (TextView)
                        convertView.findViewById(R.id.from);
                final TextView t = (TextView)
                        convertView.findViewById(R.id.to);
                final TextView s = (TextView)
                        convertView.findViewById(R.id.start);
                final TextView e = (TextView)
                        convertView.findViewById(R.id.end);
                final TextView m1 = (TextView)
                        convertView.findViewById(R.id.moreinfo);
                final TextView m2 = (TextView)
                        convertView.findViewById(R.id.moreinfosupport);
                final Button btn1 = (Button)
                        convertView.findViewById(R.id.buttonPrime);
                final Button btn2 = (Button)
                        convertView.findViewById(R.id.buttonOption);

                final TicketOfHistoryLayoutModel tick = myArray.get(position);
                b.setText(tick.Coach.getCompanyId());
                r.setText(Float.toString(tick.Coach.getStars()) + "/5.0");
                f.setText(tick.Coach.getFrom());
                t.setText(tick.Coach.getTo());
                Date a = new Date(tick.Coach.getTimeStart());
                SimpleDateFormat ba = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                s.setText(ba.format(a));
                a = new Date(tick.Coach.getTimeEnd());
                e.setText(ba.format(a));
                m2.setText("Ghế: " + tick.Ticket.getSeatNumber());
                if (tick.Ticket.getStatus().equals("Chờ thanh toán")) {
                    btn1.setText("THANH TOÁN");
                    btn2.setVisibility(View.VISIBLE);
                    btn2.setText("HỦY VÉ");
                    m1.setText("Giá: " + tick.Coach.getPrice());
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CallBack.onPay(position, context);
                        }
                    });
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CallBack.onCancel(position, context);
                        }
                    });
                } else if (tick.Ticket.getStatus().equals("Đã thanh toán")) {
                    btn2.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    m1.setText("Đã thanh toán");
                } else if (tick.Ticket.getStatus().equals("Đã hoàn thành")) {
                    m1.setText("Đã hoàn thành");
                    btn1.setText("ĐÁNH GIÁ");
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CallBack.onRating(position, context);
                        }
                    });
                    btn2.setVisibility(View.INVISIBLE);
                }

            }
        }
        catch(Exception ex){

        }
        return convertView;
    }

    public interface OnListener{
        void onCancel(int position, Activity context);
        void onPay(int position, Activity context);
        void onRating(int position, Activity context);
    }
}
