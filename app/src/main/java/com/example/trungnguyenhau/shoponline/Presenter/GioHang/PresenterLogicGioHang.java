package com.example.trungnguyenhau.shoponline.Presenter.GioHang;

import android.content.Context;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.GioHang.ModelGioHang;
import com.example.trungnguyenhau.shoponline.Views.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class PresenterLogicGioHang implements IPresenterGioHang {

    private ModelGioHang modelGioHang;
    private ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
