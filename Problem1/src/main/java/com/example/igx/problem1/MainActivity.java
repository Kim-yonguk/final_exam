package com.example.igx.problem1;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.wearable.MessageApi;

import java.util.ArrayList;
import java.util.Calendar;

import static com.google.android.gms.wearable.MessageApi.*;

public class MainActivity extends AppCompatActivity /* implements Something1, Something2 */ {


    public GetGps GPS;
    public GetSensor Sensortest;
    static double lat;
    static double lng;
    PermissionListener permissionlistener = new PermissionListener()
    {
        @Override
        public void onPermissionGranted()
        {
            Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions)
        {
            Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        new TedPermission(this)

                .setPermissionListener(permissionlistener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("취소하셨습니다. [설정] > [권한] 에서 권한을 다시 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();
        setContentView(R.layout.activity_main);

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);



        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            GPS=new GetGps(MainActivity.this);

                lat=GPS.getLatitude();
                lng=GPS.getLongitude();

                GPS.getLocation();
            String str1= "LOCATION";
            String str= "위도 : " + lat + "경도 : " + lng;

            text_selectedType.setText(str1);
            text_selectedData.setText(str);



            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sensortest=new GetSensor();



               double g=Sensortest.gx;
                double g1=Sensortest.gy;
                double g2=Sensortest.gz;


                double a=Sensortest.ax;
                double a1=Sensortest.ay;
                double a2=Sensortest.az;

                double l=Sensortest.lx;

                double l1=Sensortest.ly;

                double l2=Sensortest.lz;

                double gy=Sensortest.GYx;

                double gy1=Sensortest.GYy;
                double gy2=Sensortest.GYz;


                String str3="SENSOR";
                String str4="gravity : " + g + " " + g1 + " " + g2 +  " " + "accel : " + a + " " + a1 + " " + a2 + " " + "linear : " + l + " " + l1 + " " + l2 + " " + " gyro : " + gy + " " + gy1 + " " + gy2 + " ";

                text_selectedType.setText(str3);
                text_selectedData.setText(str4);





            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPS=new GetGps(MainActivity.this);

                lat=GPS.getLatitude();
                lng=GPS.getLongitude();

                GPS.getLocation();
                String str1= "Location";
                String str= "위도 : " + lat + "경도 : " + lng;


                Sensortest=new GetSensor();



                double g=Sensortest.gx;
                double g1=Sensortest.gy;
                double g2=Sensortest.gz;


                double a=Sensortest.ax;
                double a1=Sensortest.ay;
                double a2=Sensortest.az;

                double l=Sensortest.lx;

                double l1=Sensortest.ly;

                double l2=Sensortest.lz;

                double gy=Sensortest.GYx;

                double gy1=Sensortest.GYy;
                double gy2=Sensortest.GYz;


                String str3="SENSOR";
                String str4="gravity : " + g + " " + g1 + " " + g2 +  " " + "accel : " + a + " " + a1 + " " + a2 + " " + "linear : " + l + " " + l1 + " " + l2 + " " + " gyro : " + gy + " " + gy1 + " " + gy2 + " ";




                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_PHONE_NUMBER,010);
                sendIntent.putExtra(Intent.EXTRA_TEXT, str+"                                         "+str4);
                sendIntent.setType("text/plain");


// Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }

            }
        });
    }
}
