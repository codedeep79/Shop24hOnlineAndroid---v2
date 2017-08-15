package com.example.trungnguyenhau.shoponline.Views.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trungnguyenhau.shoponline.Adapter.AdapterTopDienThoaiDienTu;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ILoadMore;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.LoadMoreScroll;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;
import com.example.trungnguyenhau.shoponline.Presenter.Search.PresenterLogicSearch;
import com.example.trungnguyenhau.shoponline.R;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/15/2017.
 */

public class SearchActivity extends AppCompatActivity  implements ViewSearch, ILoadMore, SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicSearch presenterLogicSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTimKiem);

        setSupportActionBar(toolbar);

        presenterLogicSearch = new PresenterLogicSearch(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(this,R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }

    // Bắt sự kiện khi người dùng nhấn tìm kiếm thì sẽ thực hiện câu lệnh
    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicSearch.searchByName(query,0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
