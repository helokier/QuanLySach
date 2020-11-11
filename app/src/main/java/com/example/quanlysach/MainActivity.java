package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  ImageView ND,TL,Sach,HD,TOPSach,TK;
    String ussername ="";
    TextView tvname;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("QUẢN LÝ SÁCH");
        ND = findViewById(R.id.ND);
        TL = findViewById(R.id.TL);
        Sach = findViewById(R.id.Sach);
        HD = findViewById(R.id.HD);
        TOPSach = findViewById(R.id.TOPSach);
        TK =findViewById(R.id.TK);
        ND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,NguoiDungActivity.class);
                startActivity(intent);
            }
        });
        TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,TheLoaiActivity.class);
                startActivity(intent);
            }
        });
        Sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,SachActivity.class);
                startActivity(intent);
            }
        });
        HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,HoaDonActivity.class);
                startActivity(intent);
            }
        });
       TOPSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,TopSachActivity.class);
                startActivity(intent);
            }
        });
        TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,ThongKeActivity.class);
                startActivity(intent);
            }
        });

    }
    public int checkLoginRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("USSER_FILE.txt",MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("Remember",false);
        if(check){
            ussername = sharedPreferences.getString("USSERNAME","");
            tvname.setText("Xin Chào: ");
            return 1;
        }
        return -1;
    }

}