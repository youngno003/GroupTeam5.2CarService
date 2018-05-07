package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Man Trinh 2018/04/18
public class DangKyTaiKhoanActivity extends AppCompatActivity {

private FirebaseAuth mAuth;

    private final static String TAG = DangKyTaiKhoanActivity.class.getSimpleName();

    private Button btnDangki;
    //private ImageView imgAvatar;
    private EditText edtFullName, edtEmail, edtPassword, edtPhone, edtPasswordConfirm;

    
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky_tai_khoan);
        mAuth = FirebaseAuth.getInstance();
        AnhXa();
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String passwordConfirm = edtPasswordConfirm.getText().toString().trim();
                String fullName = edtFullName.getText().toString().trim();
                String phoneNumber = edtPhone.getText().toString().trim();
                // Bắt lỗi
                if(email.isEmpty()||password.isEmpty()||passwordConfirm.isEmpty()||fullName.isEmpty()||phoneNumber.isEmpty()){
                    Log.d(TAG,"Bạn nhập thiếu thông tin.");
                    Toast.makeText(DangKyTaiKhoanActivity.this,"Bạn nhập thiếu thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false){
                    Log.d(TAG,"Email không đúng định dạng");
                    Toast.makeText(DangKyTaiKhoanActivity.this,"Email không đúng định dạng.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6){
                    Log.d(TAG,"Mật khẩu quá ngắn ( < 6 ký tự )");
                    Toast.makeText(DangKyTaiKhoanActivity.this,"Mật khẩu quá ngắn.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.contentEquals(passwordConfirm)){
                    Log.d(TAG,"Xác nhập lại mật khẩu không khớp.");
                    Toast.makeText(DangKyTaiKhoanActivity.this,"Xác nhập lại mật khẩu không khớp.",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String rexStr = "^[0-9]$";
                if(phoneNumber.length() < 9 || phoneNumber.length() > 12){
                    Log.d(TAG,"Số điện thoại không hợp lệ");
                    Toast.makeText(DangKyTaiKhoanActivity.this,"Số điện thoại không hợp lệ",Toast.LENGTH_SHORT).show();
                    return;
                }

                Dangki(email,password,fullName,phoneNumber);

            }
        });
    }

    private void AnhXa() {
        btnDangki = findViewById(R.id.btnDk);
        edtFullName = findViewById(R.id.txtHovaten);
        edtEmail = findViewById(R.id.txtEmail);
        edtPassword = findViewById(R.id.txtMatkhau);
        edtPasswordConfirm = findViewById(R.id.txtRePass);
        edtPhone = findViewById(R.id.txtSDT);
    }
    private void Dangki(String email, String password, final String fullName, final String phone ) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(DangKyTaiKhoanActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            updateUI(user,fullName,phone);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(DangKyTaiKhoanActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }

    private void updateUI(FirebaseUser user, String fullName , String Phone) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        myref.child("Phone").child(user.getUid()).setValue(Phone);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Xử lý khi nhấn nút back
        startActivity(new Intent(DangKyTaiKhoanActivity.this,LoginActivity.class));
        finish();
    }
}