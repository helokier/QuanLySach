package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.model.NguoiDung;
import com.example.quanlysach.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
     Button btnAddTheLoai,btnCancelTheLoai,btnShowTheLoai;
     EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;
     TheLoaiDao theLoaiDao;
     Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("THỂ LOẠI");
        btnAddTheLoai = findViewById(R.id.btnAddTheLoai);
        btnCancelTheLoai = findViewById(R.id.btnCancelTheLoai);
        btnShowTheLoai = findViewById(R.id.btnShowTheLoai);
        edMaTheLoai = findViewById(R.id.edMaTheLoai);
        edTenTheLoai = findViewById(R.id.edTenTheLoai);
        edViTri = findViewById(R.id.edViTri);
        edMoTa = findViewById(R.id.edMoTa);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bunTL");
        if (bundle != null) {
            edMaTheLoai.setText(bundle.getString("MaTheLoai_key"));
            edTenTheLoai.setText(bundle.getString("TenTheLoai_key"));
            edViTri.setText(bundle.getString("ViTri_key"));
            edMoTa.setText(bundle.getString("MoTa_key"));
        }
    }
    public void addTheLloai(View view) {
        //tao moi nguoiDungDao va truyen context
        theLoaiDao = new TheLoaiDao(TheLoaiActivity.this);
        //dua du lieu tren form vao doi tuong nguoi dung
        TheLoai theLoai = new TheLoai(edMaTheLoai.getText().toString(),edTenTheLoai.getText().toString(),
                edViTri.getText().toString(),edMoTa.getText().toString());
        try {
            if (theLoaiDao.inserTheLoai(theLoai)<0)//thuc hien goi ham them du lieu
            {
                //dua ra thonhg bao thanh cong
                Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_LONG).show();
            }
            else
            {
                //dua ra thong bao that bai
                Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Log.e("TheLoaiActivity: ",e.getMessage());//tra ve thong bao loi neu co loi
        }
    }
    public void CancelTl(View view){
        edMaTheLoai.setText("");
        edTenTheLoai.setText("");
        edViTri.setText("");
        edMoTa.setText("");
    }
    public  void ShowUser1(View view){
        intent =new Intent(TheLoaiActivity.this,ListTheLoaiActivity.class);
        startActivity(intent);
    }
}