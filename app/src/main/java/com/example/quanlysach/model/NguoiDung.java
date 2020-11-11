package com.example.quanlysach.model;

public class NguoiDung {
    private String userName;
    private String password;
    private String phone;
    private String fullName;

    public NguoiDung(String userName, String password, String phone, String fullName) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
    }

    public NguoiDung() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
