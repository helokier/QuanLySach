package com.example.quanlysach.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.quanlysach.Dao.HoaDonChiTietDao;
import com.example.quanlysach.Dao.HoaDonDao;
import com.example.quanlysach.Dao.NguoiDungDao;
import com.example.quanlysach.Dao.SachDao;
import com.example.quanlysach.Dao.TheLoaiDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDao.SQL_THE_LOAI);
        db.execSQL(SachDao.SQL_SACH);
        db.execSQL(HoaDonDao.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDao.SQL_HOA_DON_CHI_TIET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+NguoiDungDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TheLoaiDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SachDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+HoaDonDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+HoaDonChiTietDao.TABLE_NAME);
        onCreate(db);

    }
}
