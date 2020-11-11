package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.adapter.SachAdapter;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TopSachActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDao sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach);
        lvBook = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
        TextView text = findViewById(R.id.text);
    }
    public void VIEW_SACH_TOP_10(View view) {
        try {
            if (Integer.parseInt(edThang.getText().toString()) > 13 ||
                    Integer.parseInt(edThang.getText().toString()) < 0) {
                Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
            } else {
                sachDAO = new SachDao(TopSachActivity.this);
                dsSach = sachDAO.getSachTop10(edThang.getText().toString());
                adapter = new SachAdapter(this, dsSach);
                lvBook.setAdapter(adapter);
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Lỗi nhập không đúng kí tự", Toast.LENGTH_SHORT).show();
        }
    }
}