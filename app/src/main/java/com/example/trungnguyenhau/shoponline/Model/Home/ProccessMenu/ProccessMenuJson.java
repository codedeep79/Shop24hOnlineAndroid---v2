package com.example.trungnguyenhau.shoponline.Model.Home.ProccessMenu;

import android.util.Log;

import com.example.trungnguyenhau.shoponline.ConnectInternet.DownloadJson;
import com.example.trungnguyenhau.shoponline.MainActivity;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.TypeProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by TRUNGNGUYENHAU on 4/21/2017.
 */

public class ProccessMenuJson {
    String tennguoidung = "";

    public List<TypeProduct> ParserJSONMenu(String dulieujson) throws JSONException {
        List<TypeProduct> loaiSanPhamList = new ArrayList<>();
        try {
            Log.d("kiemtra",dulieujson);
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for(int i=0;i<count;i++){
                JSONObject value = loaisanpham.getJSONObject(i);

                TypeProduct dataTypeProduct = new TypeProduct();
                dataTypeProduct.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataTypeProduct.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataTypeProduct.setTENLOAISP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataTypeProduct);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

    public List<TypeProduct> LayLoaiSanPhamTheoMaLoai(int maloaisp){
        List<TypeProduct> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";
        // URL
        String duongdan = MainActivity.URL;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String,String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha",String.valueOf(maloaisp));

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJson downloadJSON = new DownloadJson(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            ProccessMenuJson proccessMenuJson = new ProccessMenuJson();
            loaiSanPhamList = proccessMenuJson.ParserJSONMenu(dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }
}
