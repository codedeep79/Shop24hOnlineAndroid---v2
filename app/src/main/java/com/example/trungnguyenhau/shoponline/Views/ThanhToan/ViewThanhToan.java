package com.example.trungnguyenhau.shoponline.Views.ThanhToan;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
