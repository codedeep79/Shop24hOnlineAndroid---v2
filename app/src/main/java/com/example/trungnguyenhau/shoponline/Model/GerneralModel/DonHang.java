package com.example.trungnguyenhau.shoponline.Model.GerneralModel;

import java.io.Serializable;

/**
 * Created by TRUNGNGUYENHAU on 4/27/2017.
 */

public class DonHang implements Serializable{
    private String urlImage;
    private String tenMatHang;
    private String soLuong;
    private String donGia;

    public DonHang(String urlImage, String tenMatHang, String soLuong, String donGia) {
        this.urlImage = urlImage;
        this.tenMatHang = tenMatHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }
}
