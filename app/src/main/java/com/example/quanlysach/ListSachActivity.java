package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.adapter.SachAdapter;
import com.example.quanlysach.adapter.SachRecyclerAdapter;
import com.example.quanlysach.adapter.TheLoaiRecyclerAdapter;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListSachActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();

    SachAdapter adapter = null;
    RecyclerView recyclerView;
    SachDao sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SÁCH");
        setContentView(R.layout.activity_list_sach);
        recyclerView = findViewById(R.id.recyclerView);


        sachDAO = new SachDao(ListSachActivity.this);
        dsSach = sachDAO.getAllSach();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        SachRecyclerAdapter adapter = new SachRecyclerAdapter(ListSachActivity.this,dsSach);
        recyclerView.setAdapter(adapter);
    }

}