package com.example.trungnguyenhau.shoponline.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.example.trungnguyenhau.shoponline.Model.DanhGia.ModelDanhGia;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DanhGia;
import com.example.trungnguyenhau.shoponline.Views.DanhGia.ViewDanhGia;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class PresenterLogicDanhGia implements IPresenterDanhGia {

    private ViewDanhGia viewDanhGia;
    private ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else{
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,limit);

        if (danhGiaList.size() > 0){
            progressBar.setVisibility(View.GONE);
            viewDanhGia.HienThiDanhSachDanhGiaTheoSanPham(danhGiaList);
        }
    }
}

