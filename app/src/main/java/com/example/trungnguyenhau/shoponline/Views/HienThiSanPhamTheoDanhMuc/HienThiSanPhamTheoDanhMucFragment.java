package com.example.trungnguyenhau.shoponline.Views.HienThiSanPhamTheoDanhMuc;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.trungnguyenhau.shoponline.Adapter.AdapterTopDienThoaiDienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ILoadMore;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.LoadMoreScroll;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.Home.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucFragment extends android.support.v4.app.Fragment implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener,ILoadMore{

    private RecyclerView recyclerView;
    private Button btnThayDoiTrangThaiRecycler;
    private ProgressBar progressBar;
    private boolean danggrid = true;
    private RecyclerView.LayoutManager layoutManager;
    private PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    private AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    private int masp;
    private boolean kiemtra;
    private Toolbar toolbar;
    private List<SanPham> sanPhamList1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc,container,false);

        setHasOptionsMenu(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = (Button) view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI",0);
        String tensanpham = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA",false);

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);

        toolbar.setTitle(tensanpham);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home,menu);
    }


    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;
        if(danggrid){
            layoutManager = new GridLayoutManager(getContext(),2);
            adapterTopDienThoaiDienTu =  new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList1);

        }else{
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList1);

        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);
                break;
        }
    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> listsanPhamsLoadMore = sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(masp,kiemtra,tongitem,progressBar);
        sanPhamList1.addAll(listsanPhamsLoadMore);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }
}
