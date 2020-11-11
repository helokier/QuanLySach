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

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.NguoiDungActivity;
import com.example.quanlysach.R;
import com.example.quanlysach.TheLoaiActivity;
import com.example.quanlysach.model.NguoiDung;
import com.example.quanlysach.model.TheLoai;

import java.util.List;

public class TheLoaiRecyclerAdapter  extends RecyclerView.Adapter<TheLoaiRecyclerAdapter.RecyclerHolderView> {
    private Context context;
    private List<TheLoai> arrTheLoai;
    private LayoutInflater inflater;
    TheLoaiDao theLoaiDao;
    public TheLoaiRecyclerAdapter(Context context, List<TheLoai> arrTheLoai)
    {

        this.context = context;
        this.arrTheLoai = arrTheLoai;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDao = new TheLoaiDao(context);
    }

    @NonNull
    @Override
    public RecyclerHolderView onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
        View view = inflater.inflate(R.layout.item_theloai,null);
        RecyclerHolderView view1 = new RecyclerHolderView(view);
        view1.ivIcon = (ImageView)view.findViewById(R.id.ivIcon);
        view1.tvName = (TextView)view.findViewById(R.id.tvTenTheLoai);
        view1.tvMatheLoai = (TextView)view.findViewById(R.id.tvMaTheLoai);


        view1.ivDelete = (ImageView)view.findViewById(R.id.ivDelete);
        view1.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                theLoaiDao.deleteTheLoaiByID(arrTheLoai.get(i).getMaTheLoai());
                TheLoai theLoai = arrTheLoai.get(i);
                arrTheLoai.remove(theLoai);
                notifyDataSetChanged();
            }
        });


        return view1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolderView holder, final int position) {
        TheLoai theLoai = arrTheLoai.get(position);
        holder.tvMatheLoai.setText(theLoai.getMaTheLoai());
        holder.tvName.setText(theLoai.getTenTheLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TheLoaiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaTheLoai_key",arrTheLoai.get(position).getMaTheLoai());
                bundle.putString("TenTheLoai_key",arrTheLoai.get(position).getTenTheLoai());
                bundle.putString("ViTri_key",arrTheLoai.get(position).getViTri());
                bundle.putString("MoTa_key",arrTheLoai.get(position).getMoTa());
                intent.putExtra("bunTL",bundle);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrTheLoai.size();
    }

    //gan du lieu


    public class RecyclerHolderView extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        TextView tvName;
        TextView tvMatheLoai;
        ImageView ivDelete;

        public RecyclerHolderView(View itemView) {
            super(itemView);
            this.ivIcon = ivIcon;
            this.tvName = tvName;
            this.tvMatheLoai = tvMatheLoai;

            this.ivDelete = ivDelete;
        }
    }
}
