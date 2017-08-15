package com.example.trungnguyenhau.shoponline.Presenter.Home_Electronic;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ThuongHieu;
import com.example.trungnguyenhau.shoponline.Model.Home_Electronic.ModelElectronic;
import com.example.trungnguyenhau.shoponline.Views.Home.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class PresenterLogicDienTu implements IPresenterDienTu {

    private ViewDienTu viewDienTu;
    private ModelElectronic modelElectronic;

    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelElectronic = new ModelElectronic();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTus = new ArrayList<>();
		
		// Sắp xếp theo thứ tự. RecycleView chồng RecyleView và lồng vào nhau
        List<ThuongHieu> thuongHieuList = modelElectronic.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelElectronic.LayDanhSachSanPhamTOP("LayDanhSachTopDienThoaiVaMayTinhBang","TOPDIENTHOAI&MAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieu(thuongHieuList);
        dienTu.setSanPham(sanPhamList);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);


        List<SanPham> phukienList = modelElectronic.LayDanhSachSanPhamTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");
        List<ThuongHieu> topphukienList = modelElectronic.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieu(topphukienList);
        dienTu1.setSanPham(phukienList);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);


        List<SanPham> tienichList = modelElectronic.LayDanhSachSanPhamTOP("LayTopTienIch","TOPTIENICH");
        List<ThuongHieu> toptienichList = modelElectronic.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieu(toptienichList);
        dienTu2.setSanPham(tienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top Video & Tivi");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);

        if(thuongHieuList.size() >0 && sanPhamList.size() > 0){


            viewDienTu.HienThiDanhSach(dienTus);
        }
    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelElectronic.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");
        if(thuongHieuList.size() > 0){
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelElectronic.LayDanhSachSanPhamTOP("LayDanhSachSanPhamMoi","DANHSACHSANPHAMMOIVE");
        if(sanPhamList.size() > 0){
            viewDienTu.HienThiSanPhamMoiVe(sanPhamList);
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }
}
