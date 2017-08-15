package com.example.trungnguyenhau.shoponline.Views.ChiTietSanPham;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DanhGia;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface ViewChiTietSanPham {
    void HienChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGiohangThatBai();
}
