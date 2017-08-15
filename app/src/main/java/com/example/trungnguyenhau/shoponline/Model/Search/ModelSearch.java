package com.example.trungnguyenhau.shoponline.Model.Search;

import com.example.trungnguyenhau.shoponline.ConnectInternet.DownloadJson;
import com.example.trungnguyenhau.shoponline.MainActivity;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ChiTietKhuyenMai;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by NguyenTrungHau on 8/15/2017.
 */

public class ModelSearch {
    public List<SanPham> searchByName(String tensp, String tenmang, String tenham, int limit){
        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = MainActivity.URL;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String,String> hsTenSP = new HashMap<>();
        hsTenSP.put("tensp",String.valueOf(tensp));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsTenSP);
        attrs.add(hsLimit);

        DownloadJson downloadJson = new DownloadJson(duongdan,attrs);
        //end phương thức post
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i<dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(MainActivity.HOST + object.getString("HINHSANPHAM"));
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                sanPhams.add(sanPham);


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;
    }
}
