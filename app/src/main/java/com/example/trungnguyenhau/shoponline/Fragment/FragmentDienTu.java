package com.example.trungnguyenhau.shoponline.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trungnguyenhau.shoponline.Adapter.AdapterDienTu;
import com.example.trungnguyenhau.shoponline.Adapter.AdapterThuongHieuLonDienTu;
import com.example.trungnguyenhau.shoponline.Adapter.AdapterTopDienThoaiDienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.DienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ThuongHieu;
import com.example.trungnguyenhau.shoponline.Presenter.Home_Electronic.PresenterLogicDienTu;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.Home.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDienTu extends Fragment implements ViewDienTu {
    private RecyclerView recyclerView,recylerTopCacThuongHieuLon,recylerHangMoiVe;
    private List<DienTu> dienTuList;
    private PresenterLogicDienTu presenterLogicDienTu;
    private ImageView imSanPham1,imSanPham2,imSanPham3;
    private TextView txtSanPham1,txtSanPham2,txtSanPham3;

    public FragmentDienTu() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dientu, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDienTu);
        recylerTopCacThuongHieuLon = (RecyclerView) view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recylerHangMoiVe = (RecyclerView) view.findViewById(R.id.recylerHangMoiVe);
        imSanPham1 = (ImageView) view.findViewById(R.id.imSanPham1);
        imSanPham2 = (ImageView) view.findViewById(R.id.imSanPham2);
        imSanPham3 = (ImageView) view.findViewById(R.id.imSanPham3);
        txtSanPham1 = (TextView) view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = (TextView) view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = (TextView) view.findViewById(R.id.txtSanPham3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTu) {
        dienTuList = dienTu;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(),dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieu) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(),thuongHieu);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);

        recylerTopCacThuongHieuLon.setLayoutManager(layoutManager);
        recylerTopCacThuongHieuLon.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPham) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPham);

        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recylerHangMoiVe.setLayoutManager(layoutManagerTop);
        recylerHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
        Random random = new Random();

        int vitri = random.nextInt(sanPham.size());

        Picasso.with(getContext()).load(sanPham.get(vitri).getANHLON()).fit().centerInside().into(imSanPham1);
        txtSanPham1.setText(sanPham.get(vitri).getTENSP());

        int vitri1 = random.nextInt(sanPham.size());
        Picasso.with(getContext()).load(sanPham.get(vitri1).getANHLON()).fit().centerInside().into(imSanPham2);
        txtSanPham2.setText(sanPham.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPham.size());
        Picasso.with(getContext()).load(sanPham.get(vitri2).getANHLON()).fit().centerInside().into(imSanPham3);
        txtSanPham3.setText(sanPham.get(vitri2).getTENSP());
    }
}
