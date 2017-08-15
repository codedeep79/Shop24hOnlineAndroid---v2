package com.example.trungnguyenhau.shoponline.Views.Home;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface ViewHienThiSanPhamTheoDanhMuc {
    void HienThiDanhSachSanPham(List<SanPham> sanPhamList);
    void LoiHienThiDanhSachSanPham();
}
