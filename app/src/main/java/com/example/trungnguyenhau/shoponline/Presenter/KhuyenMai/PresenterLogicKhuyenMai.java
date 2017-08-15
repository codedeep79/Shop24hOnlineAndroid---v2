package com.example.trungnguyenhau.shoponline.Presenter.KhuyenMai;

import android.util.Log;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.KhuyenMai;
import com.example.trungnguyenhau.shoponline.Model.KhuyenMai.ModelPromotion;
import com.example.trungnguyenhau.shoponline.Views.Home.ViewKhuyenMai;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai{
    ViewKhuyenMai viewKhuyenMai;
    ModelPromotion modelPromotion;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelPromotion = new ModelPromotion();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelPromotion.LayDanhSachSanPhamTheoMaLoai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");
        if(khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
