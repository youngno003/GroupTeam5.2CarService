package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by T420 on 5/5/2018.
 */

public class PaymentController extends AppCompatActivity {
    private EditText tenEditText,  maBaoMatEditText, soTheEditText;
    private Spinner monthPicker,yearsPicker;
    private Button thanhToan;
    private DatabaseReference mData;
    private String linkPayment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lich_su_dat_ve_thanh_toan);
        monthPicker = (Spinner) findViewById(R.id.month);
        yearsPicker = (Spinner) findViewById(R.id.yearPicker);
        List<String> plants = new ArrayList<>();
        for (int i = 1 ; i <= 12 ; i++ ){
            plants.add(String.valueOf(i));
        }

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plants);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        monthPicker.setAdapter(spinnerArrayAdapter);
        List<String> plants2 = new ArrayList<String>();
        for(int i = 1970 ; i < 2019 ; i++) {
            plants2.add(String.valueOf(i));
        }
        final ArrayAdapter<String> spinnerArrayAdapt = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plants2);
        spinnerArrayAdapt.setDropDownViewResource(R.layout.spinner_item);
        yearsPicker.setAdapter(spinnerArrayAdapt);
        yearsPicker.setSelection(48);



        tenEditText = (EditText) findViewById(R.id.tenInTrenThe);
        soTheEditText = (EditText) findViewById(R.id.soThe);
        maBaoMatEditText = (EditText) findViewById(R.id.maBaoMat);
        thanhToan = (Button) findViewById(R.id.thanhToan);
        linkPayment = getIntent().getStringExtra("EXTRA_SESSION_ID");
        mData = FirebaseDatabase.getInstance().getReference();
        thanhToan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                notice(view.getContext());
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/

        super.onBackPressed();
        startActivity(new Intent(this, HistoryLayoutController.class));
        finish();
    }
    private void notice(Context context){
        AlertDialog.Builder builder;
        if (soTheEditText.length() < 20) {
            Toast.makeText(context,"Wrong number card form",Toast.LENGTH_SHORT).show();
            return;
        }

        if (tenEditText.length() == 0) {
            Toast.makeText(context,"Card's name is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (maBaoMatEditText.length() != 3) {
            Toast.makeText(context,"Wrong card's private code form",Toast.LENGTH_SHORT).show();
            return;
        }
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn mua vé?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mData.child(linkPayment).setValue("Đã thanh toán");
                        Intent intent = new Intent(getBaseContext(),HistoryLayoutController.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}
