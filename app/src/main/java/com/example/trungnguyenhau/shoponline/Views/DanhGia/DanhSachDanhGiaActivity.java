package com.example.trungnguyenhau.shoponline.Views.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.trungnguyenhau.shoponline.Adapter.AdapterDanhGia;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DanhGia;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ILoadMore;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.LoadMoreScroll;
import com.example.trungnguyenhau.shoponline.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.trungnguyenhau.shoponline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {

    private RecyclerView recyclerDanhSachDanhGia;
    private ProgressBar progressBar;
    private int masp = 0;
    private PresenterLogicDanhGia presenterLogicDanhGia;
    private List<DanhGia> tatcaDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);

        recyclerDanhSachDanhGia = (RecyclerView) findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp",0);
        tatcaDanhGia = new ArrayList<>();

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);


    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {
        tatcaDanhGia.addAll(danhGiaList);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,tatcaDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
