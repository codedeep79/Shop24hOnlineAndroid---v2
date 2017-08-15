package com.example.trungnguyenhau.shoponline.Presenter.DangKy;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.NhanVien;
import com.example.trungnguyenhau.shoponline.Model.Login.ModelSignUp;
import com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy.ViewDangKy;

/**
 * Created by NguyenTrungHau on 8/12/2017.
 */

public class PresenterLogicDangKy implements IPresenterDangKy {
    private ViewDangKy viewDangKy;
    private ModelSignUp modelSignUp;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelSignUp = new ModelSignUp();
    }

    @Override
    public void ProcessSignUp(NhanVien nhanvien) {
        boolean check = modelSignUp.DangKyThanhVien(nhanvien);
        if(check){
            viewDangKy.SignUpSuccess();
        }else{
            viewDangKy.SignUpFailed();
        }
    }
}
