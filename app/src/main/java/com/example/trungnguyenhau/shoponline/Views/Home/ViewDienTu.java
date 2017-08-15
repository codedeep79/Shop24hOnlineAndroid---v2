package com.example.trungnguyenhau.shoponline.Views.Home;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ThuongHieu;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public interface ViewDienTu {
    void HienThiDanhSach (List<DienTu> dienTu);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieu);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPham);
}
