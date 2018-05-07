package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent main = new Intent(SplashScreenActivity.this,QuanLiXeKhachActivity.class);
//                startActivity(main);
//                finish();
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser != null){
                    Intent main = new Intent(SplashScreenActivity.this,QuanLiXeKhachActivity.class);
                    startActivity(main);
                    finish();
                }else {
                    Intent main = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(main);
                    finish();
                }

            }
        },1000);
    }
}
