package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
// Man Trinh 2018/04/18
public class QuenMatKhauActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final static String TAG = "QuenMatKhauActivity";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Button btnDangki;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dat_lai_mat_khau);
        mAuth = FirebaseAuth.getInstance();
        AnhXa();
        btnDangki.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ReEmail();
            }
        });
    }
    private void ReEmail()
    {
        //FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email.getText().toString().trim();
        if(Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches() == false){
            Log.d(TAG,"Email không đúng định dạng");
            Toast.makeText(QuenMatKhauActivity.this,"Email không đúng định dạng.",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(QuenMatKhauActivity.this, "Đã Gửi thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(QuenMatKhauActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(QuenMatKhauActivity.this, "THất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void AnhXa()
    {
        btnDangki = (Button)findViewById(R.id.btnSend);
        email = (EditText)findViewById(R.id.txtEmail1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Xử lý khi nhấn nút back
        startActivity(new Intent(QuenMatKhauActivity.this,LoginActivity.class));
        finish();
    }
}
