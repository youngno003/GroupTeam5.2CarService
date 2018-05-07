package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 05/05/2018.
 */

public class DetailActivity extends AppCompatActivity {
    private final static String TAG = DetailActivity.class.getSimpleName();

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        final TextView companyText = (TextView) findViewById(R.id.companyText);
        final TextView typeText = (TextView) findViewById(R.id.typeText);
        final TextView priceText = (TextView) findViewById(R.id.priceText);
        final TextView timeStartText = (TextView) findViewById(R.id.timeStartText);
        final TextView timeEndText = (TextView) findViewById(R.id.timeEndText);
        final TextView fromText = (TextView) findViewById(R.id.fromText);
        final TextView toText = (TextView) findViewById(R.id.toText);
        final TextView journeyText = (TextView) findViewById(R.id.journeyText);

        final Intent intent = getIntent();
        final String key = "TP HCM-Tiền Giang-08-05-2018 06:00-Hoàng Long"  ;
//        if(!key.isEmpty()){
            db.child("CoachList").child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() != ""){
                        companyText.setText(dataSnapshot.child("companyId").getValue().toString());
                        typeText.setText(dataSnapshot.child("type").getValue().toString());
                        priceText.setText(dataSnapshot.child("price").getValue().toString());
                        Date startDate = new Date((long) dataSnapshot.child("timeStart").getValue());
                        Date endDate = new Date((long) dataSnapshot.child("timeEnd").getValue());
                        SimpleDateFormat format = new SimpleDateFormat(" dd-MM-yyyy  hh:mm");
                        timeStartText.setText(format.format(startDate));
                        timeEndText.setText(format.format(endDate));
                        journeyText.setText(dataSnapshot.child("journey").getValue().toString());
                        fromText.setText(dataSnapshot.child("from").getValue().toString());
                        toText.setText(dataSnapshot.child("to").getValue().toString());
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            findViewById(R.id.SetButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent booking = new Intent(DetailActivity.this,DatChoActivity.class);
                    booking.putExtra(getString(R.string.currentCarID),key);
                    booking.putExtra(getString(R.string.currentCarType),typeText.getText());
                    startActivity(booking);
                    finish();
                }
            });
            findViewById(R.id.btnRating).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent rating = new Intent(DetailActivity.this,CommentActivity.class);
                    rating.putExtra("COMPANYID",companyText.getText());
                    startActivity(rating);
                    finish();
                }
            });
//        }else {
//            Log.d(TAG,"Key = empty");
//            Toast.makeText(DetailActivity.this,"Không tìm thấy xe", Toast.LENGTH_SHORT);
//        }


//    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
    }
}