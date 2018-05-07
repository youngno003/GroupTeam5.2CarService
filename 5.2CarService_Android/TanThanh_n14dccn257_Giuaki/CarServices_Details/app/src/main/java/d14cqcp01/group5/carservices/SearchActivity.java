package d14cqcp01.group5.carservices;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class SearchActivity extends AppCompatActivity {

    private final static String TAG = SearchActivity.class.getSimpleName();
    private DatabaseReference databaseReference;

    private AutoCompleteTextView autoEdtDiemDi,autoEdtDiemDen;
    private TextView txtPickedDate;
    private ListView lvPassengerCars;
    private Button btnDatePicker,btnSearch;

    private AdapterResult adapterResult;
    private ArrayList<XeKhach> xeKhachArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //makeDummyData();
        addContols();
        addEvents();

    }

    private void makeDummyData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        XeKhach xe1 = new XeKhach();
        xe1.setCompanyId("Hoàng Long");
        xe1.setFrom("TP HCM");
        xe1.setTo("Tiền Giang");
        xe1.setPrice(60000);
        xe1.setStars(0.0f);
        xe1.setType(XeKhach.XE_GIUONG_NAM);
        xe1.setJourney("50 Nguyễn Chí Thanh, P.2, Q.10, Hồ Chí Minh - 357 Nguyễn Thị Thập, Phường 6, Mỹ Tho,Tiền Giang");

        xe1.setTimeStart(new Date(2018-1900,4,8,6,0).getTime());
        xe1.setTimeEnd(new Date(2018-1900,4,8,7,45).getTime());
        xe1.setVacantSeats(39);

        String id1 = xe1.getFrom() + "-" + xe1.getTo() +"-"+ sdf.format(xe1.getTimeStart()) + "-" + xe1.getCompanyId();
        xe1.setId(id1);


        XeKhach xe2 = new XeKhach();
        xe2.setCompanyId("Đức Phát");
        xe2.setFrom("Bình Dương");
        xe2.setTo("Đồng Nai");
        xe2.setPrice(50000);
        xe2.setStars(0.0f);
        xe2.setType(XeKhach.XE_16_CHO);
        //Date(int year, int month, int date, int hrs, int min)
        xe2.setJourney("Số ô 11 Lô NP4, Khu phố 2, P. Phú Hòa Đại lộ Bình Dương,Thủ Dầu Một, Bình Dương - Long Thành, Đồng Nai");
        xe2.setTimeStart(new Date(2018-1900,4,8,8,20).getTime());
        xe2.setTimeEnd(new Date(2018-1900,4,8,9,30).getTime());
        xe2.setVacantSeats(42);

        String id2 = xe2.getFrom() + "-" + xe2.getTo() +"-"+ sdf.format(xe2.getTimeStart()) + "-" + xe2.getCompanyId();
        xe2.setId(id2);

        XeKhach xe3 = new XeKhach();
        xe3.setCompanyId("Thịnh Phát");
        xe3.setFrom("Quảng Ninh");
        xe3.setTo("Hà Nội");
        xe3.setPrice(114000);
        xe3.setStars(0.0f);
        xe3.setType(XeKhach.XE_25_CHO);
        //Date(int year, int month, int date, int hrs, int min)
        xe3.setJourney("Bến Xe Bãi Cháy,504 Trần Phú,Cẩm Phả,Quảng Ninh - Bến Xe Mỹ Đình,Quầy vé 9, 10 - Bến xe Mỹ Đình - Nam Từ Liêm - Hà Nội.");
        xe3.setTimeStart(new Date(2018-1900,4,8,5,0).getTime());
        xe3.setTimeEnd(new Date(2018-1900,4,8,7,30).getTime());
        xe3.setVacantSeats(16);
        String id3 = xe3.getFrom() + "-" + xe3.getTo() +"-"+ sdf.format(xe3.getTimeStart()) + "-" + xe3.getCompanyId();
        xe3.setId(id3);


        XeKhach xe4 = new XeKhach();
        xe4.setCompanyId("Hoàng Long");
        xe4.setFrom("TP HCM");
        xe4.setTo("Tiền Giang");
        xe4.setPrice(60000);
        xe4.setStars(0.0f);
        xe4.setType(XeKhach.XE_GIUONG_NAM);
        xe4.setJourney("50 Nguyễn Chí Thanh, P.2, Q.10, Hồ Chí Minh - 357 Nguyễn Thị Thập, Phường 6, Mỹ Tho,Tiền Giang");
        xe4.setTimeStart(new Date(2018-1900,4,8,8,0).getTime());
        xe4.setTimeEnd(new Date(2018-1900,4,8,9,45).getTime());
        xe4.setVacantSeats(39);
        String id4 = xe4.getFrom() + "-" + xe4.getTo() +"-"+ sdf.format(xe4.getTimeStart()) + "-" + xe4.getCompanyId();
        xe4.setId(id4);

//        xeKhachArrayList.add(xe1);
//        xeKhachArrayList.add(xe2);
//        xeKhachArrayList.add(xe3);
//        xeKhachArrayList.add(xe4);
//        adapterResult.notifyDataSetChanged();
        databaseReference.child("CoachList").child(xe1.getId()).setValue(xe1);
        databaseReference.child("CoachList").child(xe2.getId()).setValue(xe2);
        databaseReference.child("CoachList").child(xe3.getId()).setValue(xe3);
        databaseReference.child("CoachList").child(xe4.getId()).setValue(xe4);
//        NhaXe thinhPhat = new NhaXe("Thịnh Phát");
//        NhaXe ducPhat = new NhaXe("Đức Phát");
//        NhaXe hoangLong = new NhaXe("Hoàng Long");
//
//        databaseReference.child("Company").child(thinhPhat.getTenNhaXe()).setValue(thinhPhat);
//        databaseReference.child("Company").child(ducPhat.getTenNhaXe()).setValue(ducPhat);
//        databaseReference.child("Company").child(hoangLong.getTenNhaXe()).setValue(hoangLong);
    }

    private void addContols() {
        autoEdtDiemDi = findViewById(R.id.autoEdtDiemDi);
        autoEdtDiemDen = findViewById(R.id.autoEdtDiemDen);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnSearch = findViewById(R.id.btnSearch);
        txtPickedDate = findViewById(R.id.txtPickedDay);
        lvPassengerCars = findViewById(R.id.lvPassengerCars);
    }

    private void addEvents() {

        ArrayAdapter<String> adapterDiemDi = new ArrayAdapter<>(
                SearchActivity.this,android.R.layout.simple_list_item_1,
                Arrays.asList(getResources()
                        .getStringArray(R.array.arrTinhThanh)));
        ArrayAdapter<String> adapterDiemDen = new ArrayAdapter<>(
                SearchActivity.this,android.R.layout.simple_list_item_1,
                Arrays.asList(getResources()
                        .getStringArray(R.array.arrTinhThanh)));

        autoEdtDiemDi.setAdapter(adapterDiemDi);
        autoEdtDiemDen.setAdapter(adapterDiemDen);

        xeKhachArrayList = new ArrayList<>();
        adapterResult = new AdapterResult(SearchActivity.this,R.layout.result_item_layout,xeKhachArrayList);
        lvPassengerCars.setAdapter(adapterResult);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = 1, month = 1, year = 1970;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                            DecimalFormat df = new DecimalFormat("00");
                            txtPickedDate.setText(df.format(dayOfMonth)+ "-" + df.format(monthOfYear+1) + "-" + year);
                        }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pickedDate = txtPickedDate.getText().toString().trim();
                String start = autoEdtDiemDi.getText().toString().trim();
                String end = autoEdtDiemDen.getText().toString().trim();
//                String pickedTime = txtPickedTime.getText().toString().trim();

                if(pickedDate.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    Toast.makeText(SearchActivity.this,
                            "Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                xeKhachArrayList.clear();
                adapterResult.notifyDataSetChanged();
                String searchString1 = start + "-" + end + "-" + pickedDate;
                Log.d(TAG,"Search String: " + searchString1);
                Query myCarQuery = databaseReference.child("CoachList").orderByKey().startAt(searchString1);
                myCarQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot child : dataSnapshot.getChildren()){
                                XeKhach xe = child.getValue(XeKhach.class);
                                if(xe.getTimeStart() > System.currentTimeMillis()){
                                    xeKhachArrayList.add(xe);
                                    adapterResult.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                });
            }


        });
    }

}
