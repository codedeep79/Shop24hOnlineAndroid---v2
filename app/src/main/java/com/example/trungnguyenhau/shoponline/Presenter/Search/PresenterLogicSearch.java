package com.example.trungnguyenhau.shoponline.Presenter.Search;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Model.Search.ModelSearch;
import com.example.trungnguyenhau.shoponline.Views.Search.ViewSearch;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/15/2017.
 */

public class PresenterLogicSearch implements IPresenterSearch {

    ViewSearch viewSearch;
    ModelSearch modelSearch;

    public PresenterLogicSearch(ViewSearch viewSearch){
        this.viewSearch = viewSearch;
        modelSearch = new ModelSearch();
    }

    @Override
    public void searchByName(String tensp, int limit) {
        List<SanPham> sanPhamList = modelSearch.searchByName(tensp,"DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP",limit);

        if(sanPhamList.size() > 0){
            viewSearch.TimKiemThanhCong(sanPhamList);

        }else{
            viewSearch.TimKiemThatBai();
        }
    }
}
