package com.example.quanlysach.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.R;
import com.example.quanlysach.SachActivity;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachRecyclerAdapter extends RecyclerView.Adapter<SachRecyclerAdapter.RecyclerHolderView>{
    private Context context;
    private List<Sach> arrSach;
    private LayoutInflater inflater;
    public SachDao sachDao;

    public SachRecyclerAdapter(Context context, List<Sach> arrSach) {
        this.context = context;
        this.arrSach = arrSach;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sachDao = new SachDao(context);
    }



    @NonNull
    @Override
    public RecyclerHolderView onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
            View view = inflater.inflate(R.layout.item_book,null);
        SachRecyclerAdapter.RecyclerHolderView view1 = new SachRecyclerAdapter.RecyclerHolderView(view);
        view1.ivIcon = (ImageView)view.findViewById(R.id.ivIcon);
        view1.tvBookName = (TextView)view.findViewById(R.id.tvBookName);
        view1.tvSoLuong = (TextView)view.findViewById(R.id.tvSoLuong);
        view1.tvPrice = (TextView)view.findViewById(R.id.tvBookPrice);


        view1.ivDelete = (ImageView)view.findViewById(R.id.ivDelete);
        view1.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               sachDao.deleteSachByID(arrSach.get(i).getMaSach());
                Sach sach = arrSach.get(i);
                arrSach.remove(sach);
                notifyDataSetChanged();
            }
        });

        return view1;
    }


    public void onBindViewHolder(@NonNull RecyclerHolderView holder, final int position) {
        Sach sach = (Sach) arrSach.get(position);
        holder.tvBookName.setText("Tên Sách"+sach.getTenSach());
        holder.tvSoLuong.setText("Số Lượng"+sach.getSoLuong());
        holder.tvPrice.setText("Giá Bìa"+ sach.getGiaBia());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, SachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaSach_key",arrSach.get(position).getMaSach());
                bundle.putString("TenSach_key",arrSach.get(position).getTenSach());
                bundle.putString("SoLuong_key", String.valueOf(arrSach.get(position).getSoLuong()));
                bundle.putString("GiaBia_key", String.valueOf(arrSach.get(position).getGiaBia()));
                bundle.putString("NXB_key",arrSach.get(position).getNXB());
                bundle.putString("TacGia_key",arrSach.get(position).getTacGia());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrSach.size();
    }

    public class RecyclerHolderView extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        TextView tvBookName;
        TextView tvPrice;
        TextView tvSoLuong;
        ImageView ivDelete;

        public RecyclerHolderView(View itemView) {
            super(itemView);
            this.ivIcon = ivIcon;
            this.tvBookName = tvBookName;
            this.tvPrice = tvPrice;
            this.tvSoLuong = tvSoLuong;
            this.ivDelete = ivDelete;
        }
    }
}
