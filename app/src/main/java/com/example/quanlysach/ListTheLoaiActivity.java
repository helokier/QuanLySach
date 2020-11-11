package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.adapter.NguoiDungAdapter;
import com.example.quanlysach.adapter.NguoiDungRecyclerAdapter;
import com.example.quanlysach.adapter.TheLoaiAdapter;
import com.example.quanlysach.adapter.TheLoaiRecyclerAdapter;
import com.example.quanlysach.model.NguoiDung;
import com.example.quanlysach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    Intent intent;

    public static List<TheLoai> dsTheLoai = new ArrayList<>();

    TheLoaiDao theLoaiDao;
    TheLoaiAdapter adapter=null;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        recyclerView = findViewById(R.id.recyclerView);

        theLoaiDao = new TheLoaiDao(ListTheLoaiActivity.this);
        dsTheLoai=((TheLoaiDao) theLoaiDao).getAllTheLoai();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        TheLoaiRecyclerAdapter adapter = new TheLoaiRecyclerAdapter(ListTheLoaiActivity.this,dsTheLoai);
        recyclerView.setAdapter(adapter);
    }
}