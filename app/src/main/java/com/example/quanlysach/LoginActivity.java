package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.Dao.NguoiDungDao;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btnChangePass,btnCancel;
    CheckBox chkRememberPass;
    Intent intent;
    NguoiDungDao nguoiDungDAO;
    String u,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        nguoiDungDAO = new NguoiDungDao(LoginActivity.this);
   btnLogin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           u = edUserName.getText().toString();
           p = edPassword.getText().toString();
           if (u.isEmpty() || p.isEmpty()) {
               Toast.makeText(getApplicationContext(), "Tên Đăng Và Nhập Mật Khẩu Không Được Trống", Toast.LENGTH_SHORT).show();
           } else {
              // if (nguoiDungDAO.(u, p) >= 0) {
                 //  Toast.makeText(getApplicationContext(), "Login thanh cong", Toast.LENGTH_SHORT).show();

                   //    }
                   if (u.equalsIgnoreCase("admin") && p.equalsIgnoreCase("admin")) {
                       CheckLogin(u, p, chkRememberPass.isChecked());
                       Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                       intent = new Intent(LoginActivity.this, MainActivity.class);
                       startActivity(intent);

                   } else {
                       Toast.makeText(getApplicationContext(), "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                   }
               }
           }

   });
   btnCancel.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           edUserName.setText("");
           edPassword.setText("");
       }
   });
   btnChangePass.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           intent =new Intent(LoginActivity.this,ChangePassActivity.class);
           startActivity(intent);
       }
   });
   }


    public void CheckLogin(String username , String passworld, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USSER_FILE.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("USSERNAME",username);
            editor.putString("Passworld",passworld);
            editor.putBoolean("Remember",status);
        }
        editor.commit();
    }


}