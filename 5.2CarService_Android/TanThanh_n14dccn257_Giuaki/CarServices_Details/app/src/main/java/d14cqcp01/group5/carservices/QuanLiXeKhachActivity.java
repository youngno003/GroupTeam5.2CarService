package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuanLiXeKhachActivity extends AppCompatActivity {

    private Button btnbtnSearhPassengerCar;
    private Button btnBookingHistory;
    private Button btnUserInfo;
    private Button btnDangXuat;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quan_li_xe_khach);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        addControls();
        addEvents();

    }

    private void addControls() {
        btnbtnSearhPassengerCar = findViewById(R.id.btnSearhPassengerCar);
        btnBookingHistory = findViewById(R.id.btnBookingHistory);
        btnUserInfo = findViewById(R.id.btnUserInfo);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }

    private void addEvents() {
        btnbtnSearhPassengerCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến tìm kiếm xe khách
                Intent intent = new Intent(QuanLiXeKhachActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    startActivity(new Intent(QuanLiXeKhachActivity.this,HistoryLayoutController.class));
                    finish();
                }
                else{
                    startActivity(new Intent(QuanLiXeKhachActivity.this,LoginActivity.class));
                    finish();
                }

            }
        });

        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    startActivity(new Intent(QuanLiXeKhachActivity.this,UserInfoActivity.class));
                    finish();
                }
                else{
                    startActivity(new Intent(QuanLiXeKhachActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(QuanLiXeKhachActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
