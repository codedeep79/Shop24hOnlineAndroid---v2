package com.example.trungnguyenhau.shoponline.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaTheoCuaSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
}
