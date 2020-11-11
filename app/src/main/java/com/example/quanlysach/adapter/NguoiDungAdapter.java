package com.example.quanlysach.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.R;
import com.example.quanlysach.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    public List<NguoiDung> arrNguoiDung;//tao list nguoi dung
    public Activity context;//khai bao context
    public LayoutInflater inflater;//khai bao doi tuong xu ly layout
    public NguoiDungDao nguoiDungDAO;//khai bao doi tuong DAO
    //phuong thuc khoi tao
    public NguoiDungAdapter(Activity c, List<NguoiDung> arr)
    {
        super();//goi phuong thuc khoi tao cua lop cha
        this.context = c;//gan gia tri context
        this.arrNguoiDung = arr;//gan list nguoi dung
        //khoi tao layout
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //khoi tao lop DAO
        nguoiDungDAO = new NguoiDungDao(c);
    }
    @Override
    public int getCount() {//lay ve so dong
        return arrNguoiDung.size();//so dong bang kich thuoc mang
    }

    @Override
    public Object getItem(int position) {//lay ve doi tuong theo vi tri
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {//lay ve vi tri
        return 0;
    }

    @Override//lay view
    public View getView(int position, View convertView, ViewGroup parent) {
        ///xu ly giao dien
        ViewHolder holder;
        if(convertView==null)//truong hop converView=null
        {
            holder = new ViewHolder();//phai tao viewHolder
            //anh xa convertView sang viewHolder
            convertView = inflater.inflate(R.layout.item_nguoi_dung,null);
            //anh xa tung truong trong viewHolder
            holder.img = (ImageView)convertView.findViewById(R.id.ivIcon);//anh xa anh
            holder.txtName = (TextView)convertView.findViewById(R.id.tvName);//anh xa ten
            holder.txtPhone = (TextView)convertView.findViewById(R.id.tvPhone);//anh xa phone
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);//anh xa button delete
            //tao 1 template bang cach setTag
            convertView.setTag(holder);
        }
        else//truong hop convertView khac null
        {
            holder = (ViewHolder)convertView.getTag();//lay template
        }
        //xu ly du lieu
        NguoiDung nguoiDung = (NguoiDung)arrNguoiDung.get(position);//lay ve nguoi dung theo chi so
        holder.txtName.setText(nguoiDung.getFullName());//lay ve ten va gan len view
        holder.txtPhone.setText(nguoiDung.getPhone());//lay ve so dien thoai va gan len view

        return convertView;
    }
    public static class ViewHolder {//dinh nghia viewHolder (anh xa itemview sang code java)
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }
}

