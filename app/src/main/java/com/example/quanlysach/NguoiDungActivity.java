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
import com.example.quanlysach.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
 Button btnAddUser,btnChangUser,btnCancelUser,btnShowUser;
 Intent intent;
 NguoiDungDao nguoiDungDAO;
 EditText edUserName,edRePassword,edPhone,edFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("NGƯỜI DÙNG");
        btnChangUser = (Button) findViewById(R.id.btnChangUser);
        edUserName = findViewById(R.id.edUserName);
        edRePassword = findViewById(R.id.edPassword);
        edPhone = findViewById(R.id.edPhone);
        edFullName = findViewById(R.id.edFullName);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        if (bundle != null) {
            edUserName.setText(bundle.getString("username_key"));
            edRePassword.setText(bundle.getString("password_key"));
            edPhone.setText(bundle.getString("phone_key"));
            edFullName.setText(bundle.getString("hoten_key"));
        }
    }
    public void addUser(View view) {
        //tao moi nguoiDungDao va truyen context
        nguoiDungDAO = new NguoiDungDao(NguoiDungActivity.this);
        //dua du lieu tren form vao doi tuong nguoi dung
        NguoiDung nd = new NguoiDung(edUserName.getText().toString(),edRePassword.getText().toString(),
                edPhone.getText().toString(),edFullName.getText().toString());
        try {
            if (nguoiDungDAO.insertNguoiDung(nd)<0)//thuc hien goi ham them du lieu
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
            Log.e("NguoiDungActivity: ",e.getMessage());//tra ve thong bao loi neu co loi
        }
    }


    public  void ShowUser(View view){
        intent =new Intent(NguoiDungActivity.this,ListNguoiDungActivity.class);
        startActivity(intent);
    }
    public void updateUser(View view) {
        //tao moi nguoiDungDao va truyen context
        nguoiDungDAO = new NguoiDungDao(NguoiDungActivity.this);
        //dua du lieu tren form vao doi tuong nguoi dung
        NguoiDung nd = new NguoiDung(edUserName.getText().toString(),edRePassword.getText().toString(),
                edPhone.getText().toString(),edFullName.getText().toString());
        try {
            if (nguoiDungDAO.updateInfoNguoiDung
                    (edUserName.getText().toString(),edFullName.getText().toString(), edPhone.getText().toString())==1)//thuc hien goi ham them du lieu
            {
                //dua ra thonhg bao thanh cong
                Toast.makeText(getApplicationContext(),"Update Thành Công",Toast.LENGTH_LONG).show();
            }
            else
            {
                //dua ra thong bao that bai
                Toast.makeText(getApplicationContext(),"Update Thất Bại",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Log.e("NguoiDungActivity: ",e.getMessage());//tra ve thong bao loi neu co loi
        }
    }
    public void Cancel(View view){
        edUserName.setText("");
        edRePassword.setText("");
        edPhone.setText("");
        edFullName.setText("");
    }
}