package com.example.trungnguyenhau.shoponline.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.NhanVien;
import com.example.trungnguyenhau.shoponline.Model.Login.ModelLogin;
import com.example.trungnguyenhau.shoponline.Presenter.DangKy.PresenterLogicDangKy;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy.ViewDangKy;
import com.example.trungnguyenhau.shoponline.Views.Home.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDangKy extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, ViewDangKy, GoogleApiClient.OnConnectionFailedListener {
    private PresenterLogicDangKy presenterLogicDangKy;
    private Boolean checkInfoUser = false;

    private View view;
    private EditText edtHoTen, edtDiaChiEmail, edtPassword, edtConfirmPassword;
    private TextInputLayout txtILFullName, txtILEmail, txtILPassword, txtILConfirmPassword;
    private SwitchCompat switchNhanKhuyenMai;
    private Button btnDangKy;


    public FragmentDangKy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {

        txtILFullName = (TextInputLayout) view.findViewById(R.id.inputtext_hoten_dk);
        txtILPassword = (TextInputLayout) view.findViewById(R.id.inputtext_matkhau_dk);
        txtILConfirmPassword = (TextInputLayout) view.findViewById(R.id.inputtext_nhaplaimatkhau_dk);
        txtILEmail = (TextInputLayout)view.findViewById(R.id.inputtext_email_dk);

        edtHoTen = (EditText) view.findViewById(R.id.edittext_hoten_dk);
        edtHoTen.setOnFocusChangeListener(this);

        edtDiaChiEmail = (EditText) view.findViewById(R.id.edittext_email_dk);
        edtDiaChiEmail.setOnFocusChangeListener(this);

        edtPassword = (EditText) view.findViewById(R.id.edittext_matkhau_dk);
        edtConfirmPassword = (EditText) view.findViewById(R.id.edittext_nhaplaimatkhau_dk);
        edtConfirmPassword.setOnFocusChangeListener(this);

        switchNhanKhuyenMai = (SwitchCompat) view.findViewById(R.id.switch_nhankhuyenmai);
        btnDangKy = (Button) view.findViewById(R.id.button_dangky);
        btnDangKy.setOnClickListener(this);


        presenterLogicDangKy = new PresenterLogicDangKy(this);
    }

    private void addEvents() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.button_dangky:
                signInUser();
                break;
        }
    }

    private String emaildocquyen = "";
    private void signInUser() {
        String hoten = edtHoTen.getText().toString();
        String email = edtDiaChiEmail.getText().toString();
        String matkhau = edtPassword.getText().toString();
        String nhaplaimatkhau = edtConfirmPassword.getText().toString();

        switchNhanKhuyenMai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if(checkInfoUser) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKy.ProcessSignUp(nhanVien);
        }else{
            Log.d("kiemtra","Dang ky that bai ");
        }
    }

    @Override
    public void SignUpSuccess() {
        Toast.makeText(getActivity(),"Đăng ký thành công !",Toast.LENGTH_LONG).show();
    }

    @Override
    public void SignUpFailed() {
        Toast.makeText(getActivity(),"Đăng ký thất bại !",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edittext_hoten_dk:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        txtILFullName.setErrorEnabled(true);
                        txtILFullName.setError("Bạn chưa nhập mục này !");
                        checkInfoUser = false;
                    }else{
                        txtILFullName.setErrorEnabled(false);
                        txtILFullName.setError("");
                        checkInfoUser = true;
                    }
                }

                break;

            case R.id.edittext_email_dk:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        txtILEmail.setErrorEnabled(true);
                        txtILEmail.setError("Bạn chưa nhập mục này !");
                        checkInfoUser = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            txtILEmail.setErrorEnabled(true);
                            txtILEmail.setError("Đây không phải là địa chỉ Email !");
                            checkInfoUser = false;
                        }else{
                            txtILEmail.setErrorEnabled(false);
                            txtILEmail.setError("");
                            checkInfoUser = true;
                        }
                    }

                }
                break;

            case R.id.edittext_matkhau_dk:
                break;

            case R.id.edittext_nhaplaimatkhau_dk:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edtPassword.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        txtILConfirmPassword.setErrorEnabled(true);
                        txtILConfirmPassword.setError("Mật khẩu không trùng khớp !");
                        checkInfoUser = false;
                    }else{
                        txtILConfirmPassword.setErrorEnabled(false);
                        txtILConfirmPassword.setError("");
                        checkInfoUser = true;
                    }
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    // Kết nối googleplus thất bại
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
