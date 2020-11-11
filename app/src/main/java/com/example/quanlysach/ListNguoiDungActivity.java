package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.adapter.NguoiDungAdapter;
import com.example.quanlysach.adapter.NguoiDungRecyclerAdapter;
import com.example.quanlysach.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {

    Intent intent;

    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungDao nguoiDungDAO;
    NguoiDungAdapter adapter=null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        recyclerView = findViewById(R.id.recyclerView);

        nguoiDungDAO = new NguoiDungDao(ListNguoiDungActivity.this);
        dsNguoiDung=((NguoiDungDao) nguoiDungDAO).getAllNguoiDung();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        NguoiDungRecyclerAdapter adapter = new NguoiDungRecyclerAdapter(ListNguoiDungActivity.this,dsNguoiDung);
        recyclerView.setAdapter(adapter);




        //adapter = new NguoiDungAdapter(ListNguoiDungActivity.this,dsNguoiDung);
        // lvNguoiDung.setAdapter(adapter);




    }

    public void startThemNguoiDung(View view)
    {
        intent = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.add:
                // intent = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
                // startActivity(intent);
                return(true);
           // case R.id.changePass:
              //  break;
           // case R.id.logOut:

              //  break;
        }
        return super.onOptionsItemSelected(item);
    }
}