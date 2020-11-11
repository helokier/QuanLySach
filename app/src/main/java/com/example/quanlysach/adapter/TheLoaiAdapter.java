package com.example.quanlysach.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.Dao.TheLoaiDao;
import com.example.quanlysach.R;
import com.example.quanlysach.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> theLoais;
    public Activity context;//khai bao context
    public LayoutInflater inflater;//khai bao doi tuong xu ly layout
    public TheLoaiDao theLoaiDao;

    public TheLoaiAdapter(List<TheLoai> theLoais, Activity context) {
        this.context = context;
        this.theLoais = theLoais;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //khoi tao lop Dao
        theLoaiDao = new TheLoaiDao(context);

    }

    @Override
    public int getCount() {
        return theLoais.size();
    }

    @Override
    public Object getItem(int position) {
        return theLoais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TheLoaiAdapter.ViewHolder holder;
        if(convertView==null)//truong hop converView=null
        {
            holder = new TheLoaiAdapter.ViewHolder();//phai tao viewHolder
            //anh xa convertView sang viewHolder
            convertView = inflater.inflate(R.layout.item_theloai,null);
            //anh xa tung truong trong viewHolder
            holder.img = (ImageView)convertView.findViewById(R.id.ivIcon);//anh xa anh
            holder.tvMaTheLoai = (TextView)convertView.findViewById(R.id.tvMaTheLoai);//anh xa ten
            holder.tvTenTheLoai = (TextView)convertView.findViewById(R.id.tvTenTheLoai);//anh xa phone
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);//anh xa button delete
            //tao 1 template bang cach setTag
            convertView.setTag(holder);
        }
        else//truong hop convertView khac null
        {
            holder = (TheLoaiAdapter.ViewHolder)convertView.getTag();//lay template
        }
        //xu ly du lieu
        TheLoai theLoai = (TheLoai)theLoais.get(position);//lay ve nguoi dung theo chi so
        holder.tvMaTheLoai.setText(theLoai.getMaTheLoai());//lay ve ten va gan len view
        holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());//lay ve so dien thoai va gan len view

        return convertView;
    }
    public static class ViewHolder {//dinh nghia viewHolder (anh xa itemview sang code java)
        ImageView img;
        TextView tvMaTheLoai;
        TextView tvTenTheLoai;
        ImageView imgDelete;
    }
}
