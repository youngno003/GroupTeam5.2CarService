package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by Asus on 04/05/2018.
 */

public class LoginActivity extends AppCompatActivity
        implements FirebaseAuth.AuthStateListener, GoogleApiClient.OnConnectionFailedListener{

    private final static String TAG = LoginActivity.class.getSimpleName();

    private TextView txtCreateId,txtForgotPass;
    private EditText edtUser,edtPass;
    private Button btnLogin,btnLoginMail;

    FirebaseAuth mAuth;
    int REQUEST_CODE_GMAIL = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SetControl();

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        // yêu cầu trả về gì
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestProfile()
                .build();

        // khởi tạo Client kết nối để activity_login Google (mở màn hình activity_login google, truyền thông tin vào)
        final GoogleApiClient signInApi = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this,LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhapAuth();
            }
        });

        txtCreateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Tạo tài khoản");
                startActivity(new Intent(LoginActivity.this,DangKyTaiKhoanActivity.class));
                finish();
            }
        });
        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Quên mật khẩu");
                startActivity(new Intent(LoginActivity.this,QuenMatKhauActivity.class));
                finish();
            }
        });

        btnLoginMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDangNhapGoogle = Auth.GoogleSignInApi.getSignInIntent(signInApi);
                startActivityForResult(iDangNhapGoogle,REQUEST_CODE_GMAIL);
            }
        });

    }

    private void DangNhapAuth() {
        final String email = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if(email.length() > 0 && pass.length() >0)
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,
                                "Đăng nhập thành công với ID: " +email, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,
                                "Sai email hoặc pass\nemail phải có ..@gmail.com", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(email.length() == 0 && pass.length() == 0)
        {
            Toast.makeText(LoginActivity.this, "Xin nhập thông tin", Toast.LENGTH_SHORT).show();
        }
        if(email.length() != 0 && pass.length() == 0)
        {
            Toast.makeText(LoginActivity.this, "Xin nhập Password", Toast.LENGTH_SHORT).show();
        }
        if(email.length() == 0 && pass.length() != 0)
        {
            Toast.makeText(LoginActivity.this, "Xin nhập Email", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetControl(){
        edtUser = findViewById(R.id.edittextUser);
        edtPass = findViewById(R.id.edittextPass);
        txtCreateId     =  findViewById(R.id.buttonCreateID);
        txtForgotPass   =  findViewById(R.id.buttonForgotPass);
        btnLogin        = findViewById(R.id.buttonLogin);
        btnLoginMail    = findViewById(R.id.buttonLoginMail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_GMAIL)
        {
            if(resultCode == RESULT_OK)
            {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                String tokenID = result.getSignInAccount().getIdToken();
                AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null);
                mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this,
                                    "Đăng nhập Gmail thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,
                                    "Đăng nhập Gmail thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(LoginActivity.this,QuanLiXeKhachActivity.class));
            finish();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
