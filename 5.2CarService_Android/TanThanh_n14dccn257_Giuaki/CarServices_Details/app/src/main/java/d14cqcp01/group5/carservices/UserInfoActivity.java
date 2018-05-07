package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfoActivity extends AppCompatActivity {

    private TextView txtHoTen,txtEmail,txtSDT;
    private Button btnThoat;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Setcontrol();
        addEvents();
    }

    private void addEvents() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null)
        {
            String ten = currentUser.getDisplayName();
            txtHoTen.setText(ten);
            String email = currentUser.getEmail();
            txtEmail.setText(email);

            mDatabase.child("Phone").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String sdt = dataSnapshot.getValue(String.class);
                    txtSDT.setText(sdt);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfoActivity.this,QuanLiXeKhachActivity.class));
                finish();
            }
        });
    }

    private void Setcontrol() {
        txtHoTen = findViewById(R.id.txtHoTen);
        txtEmail = findViewById(R.id.txtEmail);
        txtSDT = findViewById(R.id.txtSDT);
        btnThoat = findViewById(R.id.btnThoat);
    }
}
