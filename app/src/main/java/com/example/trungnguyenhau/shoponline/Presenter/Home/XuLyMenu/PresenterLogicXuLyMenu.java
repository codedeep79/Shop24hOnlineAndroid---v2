package com.example.trungnguyenhau.shoponline.Presenter.Home.XuLyMenu;

import com.example.trungnguyenhau.shoponline.ConnectInternet.DownloadJson;
import com.example.trungnguyenhau.shoponline.MainActivity;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.TypeProduct;
import com.example.trungnguyenhau.shoponline.Model.Home.ProccessMenu.ProccessMenuJson;
import com.example.trungnguyenhau.shoponline.Model.Login.ModelLogin;
import com.example.trungnguyenhau.shoponline.Views.Home.ViewXuLyMenu;
import com.facebook.AccessToken;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by TRUNGNGUYENHAU on 4/22/2017.
 */
// Presenter đóng vai trò trung chuyển dữ liệu
public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    private ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<TypeProduct> listTypeProduct = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";
        // Download Json bằng phương thức get
        // String URL = "http://192.168.0.102:88/shop24h/loaisanpham.php?maloaicha=1";
        // DownloadJson downloadJson = new DownloadJson(URL);
        // Kết thức download json bằng phương thức get

        // Lây Json bằng phương thức POST
        String URL = MainActivity.URL;

        HashMap<String,String> hashMapHam = new HashMap<>();
        hashMapHam.put("ham","LayDanhSachMenu");

        HashMap<String, String> hashMapMaChaMot = new HashMap<>();
        hashMapMaChaMot.put("maloaicha", "1");
        //HashMap<String, String> hashMapMaChaNam = new HashMap<>();
        //hashMapMaChaMot.put("maloaicha", "5");

        attrs.add(hashMapMaChaMot);
        //attrs.add(hashMapMaChaNam);
        attrs.add(hashMapHam);



        DownloadJson downloadJson = new DownloadJson(URL, attrs);
        // Kết thức download json bằng phương thức post
        downloadJson.execute();


        try {
            // Get data from doInBackground
            dataJson = downloadJson.get();
            ProccessMenuJson proccessMenuJson = new ProccessMenuJson();
            listTypeProduct = proccessMenuJson.ParserJSONMenu(dataJson);

            viewXuLyMenu.HienThiDanhSachMenu(listTypeProduct);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken getUserFacebook() {
        ModelLogin modelLogin = new ModelLogin();
        AccessToken accessToken = modelLogin.getTokenFacebookCurrent();
        return accessToken;
    }
}
