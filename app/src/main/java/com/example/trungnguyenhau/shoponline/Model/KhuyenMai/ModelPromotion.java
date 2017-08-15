package com.example.trungnguyenhau.shoponline.Model.KhuyenMai;

import com.example.trungnguyenhau.shoponline.ConnectInternet.DownloadJson;
import com.example.trungnguyenhau.shoponline.MainActivity;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.ChiTietKhuyenMai;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.KhuyenMai;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class ModelPromotion {
    public List<KhuyenMai> LayDanhSachSanPhamTheoMaLoai(String tenham, String tenmang){
        List<KhuyenMai> khuyenMais = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String URL = MainActivity.URL;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);


        attrs.add(hsHam);


        DownloadJson downloadJson = new DownloadJson(URL,attrs);
        //end phương thức post
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKhuyenMai = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachKhuyenMai.length();

            for (int i = 0; i < dem; i++){
                JSONObject object = jsonArrayDanhSachKhuyenMai.getJSONObject(i);
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(MainActivity.HOST + object.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAM");
                int demsanpham = arrayDanhSachSanPham.length();

                for (int j=0; j<demsanpham; j++){
                    JSONObject objectSanPham = arrayDanhSachSanPham.getJSONObject(j);

                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSanPham.getInt("MASP"));
                    sanPham.setTENSP(objectSanPham.getString("TENSP"));
                    sanPham.setGIA(objectSanPham.getInt("GIA"));
                    sanPham.setANHLON(MainActivity.HOST  + objectSanPham.getString("ANHLON"));
                    sanPham.setANHNHO(objectSanPham.getString("ANHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);
                }

                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMais.add(khuyenMai);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMais;
    }
}
