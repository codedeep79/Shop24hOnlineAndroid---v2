package com.example.trungnguyenhau.shoponline.Model.GerneralModel;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class DienTu {
    List<ThuongHieu> thuongHieu;
    List<SanPham> sanPham;
    boolean thuonghieu;
    String tennoibat,tentopnoibat;

    public List<ThuongHieu> getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(List<ThuongHieu> thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public List<SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPham(List<SanPham> sanPham) {
        this.sanPham = sanPham;
    }

    public boolean isThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }
}
