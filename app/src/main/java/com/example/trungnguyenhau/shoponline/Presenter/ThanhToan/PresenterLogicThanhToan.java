package com.example.trungnguyenhau.shoponline.Presenter.ThanhToan;

import android.content.Context;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.HoaDon;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.GioHang.ModelGioHang;
import com.example.trungnguyenhau.shoponline.Model.ThanhToan.ModelThanhToan;
import com.example.trungnguyenhau.shoponline.Views.ThanhToan.ViewThanhToan;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    private ViewThanhToan viewThanhToan;
    private ModelThanhToan modelThanhToan;
    private ModelGioHang modelGioHang;
    private List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();
            for(int i = 0; i<dem ;i++){
                modelGioHang.XoaSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }

        }else{
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {

        sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
