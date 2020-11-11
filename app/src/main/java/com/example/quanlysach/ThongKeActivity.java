package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlysach.Dao.HoaDonChiTietDao;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDao hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setTitle("DOANH THU");
        tvNgay = findViewById(R.id.tvThongKeNgay);
        tvThang = findViewById(R.id.tvThongKeThang);
        tvNam = findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDao(this);
        tvNgay.setText("Hôm nay      : " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này   : " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này       : " + hoaDonChiTietDAO.getDoanhThuTheoNam());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView text = findViewById(R.id.text);
    }
}