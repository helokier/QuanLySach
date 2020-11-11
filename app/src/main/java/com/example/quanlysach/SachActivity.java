package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.model.Sach;
import com.example.quanlysach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class SachActivity extends AppCompatActivity {
    EditText edMaSach, edTenSach, edTacGia, edNXB, edGiaBia, edSoLuong;
    Spinner spnTheLoai;
    TheLoaiDao theLoaiDao;
   private   SachDao sachDao;
    ArrayAdapter adapter;
    Button btnAddBook, btnCancelBook, btnShowBook;
    List<TheLoai> theLoais = new ArrayList<>();
    List<Sach> saches = new ArrayList<>();
    Intent intent;
    String spiner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM SÁCH");
        setContentView(R.layout.activity_sach);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edTacGia = findViewById(R.id.edTacGia);
        spnTheLoai = findViewById(R.id.spnTheLoai);
        edNXB = findViewById(R.id.edNXB);
        edGiaBia = findViewById(R.id.edGiaBia);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnCancelBook = findViewById(R.id.btnCancelBook);
        btnShowBook = findViewById(R.id.btnShowBook);
        sachDao = new SachDao(SachActivity.this);
        abc();

        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spiner =theLoais.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai() ;
                Toast.makeText(getApplicationContext(),spiner,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }


    }
    public void addSach(View view) {
        //tao moi nguoiDungDao va truyen context
      SachDao  sachDao = new SachDao(SachActivity.this);
        //dua du lieu tren form vao doi tuong nguoi dung
        Sach sach = new Sach(edMaSach.getText().toString(),spiner,
                edTenSach.getText().toString(),edTacGia.getText().toString(),
                edNXB.getText().toString(),Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
        try {
            if (sachDao.inserSach(sach)>0)//thuc hien goi ham them du lieu
            {
                //dua ra thonhg bao thanh cong
                Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
            }
            else
            {
                //dua ra thong bao that bai

                Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Log.e("SachActivity: ",e.getMessage());//tra ve thong bao loi neu co loi
        }
    }
    public  void ShowUser2(View view){
        intent =new Intent(SachActivity.this,ListSachActivity.class);
        startActivity(intent);
    }
    public  void abc(){
        theLoaiDao = new TheLoaiDao(SachActivity.this);
        theLoais = theLoaiDao.getAllTheLoai();
        ArrayAdapter<TheLoai> adapter =new ArrayAdapter<TheLoai>(this, android.R.layout.simple_list_item_1,theLoais);
        spnTheLoai.setAdapter(adapter);
    }

    public int checkPositionTheLoai(String strTheLoai){
        for (int i = 0; i <theLoais.size(); i++){
            if (strTheLoai.equals(theLoais.get(i).getMaTheLoai())){
                return i;
            }
        }
        return 0;
    }
}

