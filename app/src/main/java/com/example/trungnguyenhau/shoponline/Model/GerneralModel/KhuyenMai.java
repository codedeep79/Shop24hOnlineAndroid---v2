package com.example.trungnguyenhau.shoponline.Model.GerneralModel;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class KhuyenMai {
    private int MAKM,MALOAISP;
    private String TENKM,NGAYBATDAU,NGAYKETTHUC,HINHKHUYENMAI,TENLOAISP;
    private List<SanPham> DanhSachSanPhamKhuyenMai;

    public int getMAKM() {
        return MAKM;
    }

    public void setMAKM(int MAKM) {
        this.MAKM = MAKM;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public String getTENKM() {
        return TENKM;
    }

    public void setTENKM(String TENKM) {
        this.TENKM = TENKM;
    }

    public String getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public void setNGAYBATDAU(String NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public String getHINHKHUYENMAI() {
        return HINHKHUYENMAI;
    }

    public void setHINHKHUYENMAI(String HINHKHUYENMAI) {
        this.HINHKHUYENMAI = HINHKHUYENMAI;
    }

    public List<SanPham> getDanhSachSanPhamKhuyenMai() {
        return DanhSachSanPhamKhuyenMai;
    }

    public void setDanhSachSanPhamKhuyenMai(List<SanPham> danhSachSanPhamKhuyenMai) {
        DanhSachSanPhamKhuyenMai = danhSachSanPhamKhuyenMai;
    }


    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }
}
