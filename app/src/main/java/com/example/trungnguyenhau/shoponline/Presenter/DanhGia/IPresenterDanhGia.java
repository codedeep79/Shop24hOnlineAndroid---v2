package com.example.trungnguyenhau.shoponline.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DanhGia;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
