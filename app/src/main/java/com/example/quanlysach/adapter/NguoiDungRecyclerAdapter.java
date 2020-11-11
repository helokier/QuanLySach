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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.NguoiDungActivity;
import com.example.quanlysach.R;
import com.example.quanlysach.model.NguoiDung;

import java.util.List;

public class NguoiDungRecyclerAdapter extends RecyclerView.Adapter<NguoiDungRecyclerAdapter.RecyclerHolderView>{
    private Context context;
    private List<NguoiDung> arrNguoiDung;
    private LayoutInflater inflater;
    NguoiDungDao nguoiDungDAO;
    public NguoiDungRecyclerAdapter(Context context, List<NguoiDung> arrNguoiDung)
    {

        this.context = context;
        this.arrNguoiDung = arrNguoiDung;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDao(context);
    }

    @Override
    public int getItemCount() {
        return arrNguoiDung.size();
    }

    //gan du lieu
    @Override
    public void onBindViewHolder(RecyclerHolderView recyclerHolderView, final int position) {
        NguoiDung nguoiDung = arrNguoiDung.get(position);
        recyclerHolderView.tvName.setText(nguoiDung.getFullName());
        recyclerHolderView.tvPhone.setText(nguoiDung.getPhone());
        recyclerHolderView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, NguoiDungActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username_key",arrNguoiDung.get(position).getUserName());
                bundle.putString("password_key",arrNguoiDung.get(position).getPassword());
                bundle.putString("phone_key",arrNguoiDung.get(position).getPhone());
                bundle.putString("hoten_key",arrNguoiDung.get(position).getFullName());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });


    }
    //tao view
    @Override
    public RecyclerHolderView onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = inflater.inflate(R.layout.item_nguoi_dung,null);
        RecyclerHolderView  view1 = new RecyclerHolderView(view);
        view1.ivIcon = (ImageView)view.findViewById(R.id.ivIcon);
        view1.tvName = (TextView)view.findViewById(R.id.tvName);
        view1.tvPhone = (TextView)view.findViewById(R.id.tvPhone);


        view1.ivDelete = (ImageView)view.findViewById(R.id.ivDelete);
        view1.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(i).getUserName());
                NguoiDung nguoiDung = arrNguoiDung.get(i);
                arrNguoiDung.remove(nguoiDung);
                notifyDataSetChanged();
            }
        });


        return view1;
    }



    public class RecyclerHolderView extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        TextView tvName;
        TextView tvPhone;
        ImageView ivDelete;

        public RecyclerHolderView(View itemView) {
            super(itemView);
            this.ivIcon = ivIcon;
            this.tvName = tvName;
            this.tvPhone = tvPhone;

            this.ivDelete = ivDelete;
        }
    }
}
