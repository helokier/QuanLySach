package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.Dao.HoaDonChiTietDao;
import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.adapter.HoaDonChiTietAdapter;
import com.example.quanlysach.model.HoaDon;
import com.example.quanlysach.model.HoaDonChiTiet;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDao hoaDonChiTietDAO;
    SachDao sachDAO;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    HoaDonChiTietAdapter adapter = null;
    double thanhTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT HOÁ ĐƠN");
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua); 
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        adapter = new HoaDonChiTietAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }
    public void ADDHoaDonChiTiet(View view) {
       HoaDonChiTietDao hoaDonChiTietDAO = new HoaDonChiTietDao(HoaDonChiTietActivity.this);
       SachDao sachDAO = new SachDao(HoaDonChiTietActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Sach sach = sachDAO.getSachByID(edMaSach.getText().toString());
                if (sach!=null){
                    int pos = checkMaSach(dsHDCT,edMaSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(),new
                            Date());
                    HoaDonChiTiet hoaDonChiTiet = new
                            HoaDonChiTiet(1,hoaDon, Integer.parseInt(edSoLuong.getText().toString()), sach);
                    Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();

                    if (pos>=0){
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos,hoaDonChiTiet);
                    }else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);

                }else {
                    Toast.makeText(getApplicationContext(),"Mã sách không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDao(HoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd: dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " +thanhTien);
            Intent intent = new Intent(HoaDonChiTietActivity.this, ListHoaDonActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach){
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++){
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }
    public int validation(){
        if
        (edMaSach.getText().toString().isEmpty()||edSoLuong.getText().toString().isEmpty()||
                edMaHoaDon.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}