package com.example.trungnguyenhau.shoponline.Model.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.trungnguyenhau.shoponline.ConnectInternet.DownloadJson;
import com.example.trungnguyenhau.shoponline.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by NguyenTrungHau on 8/11/2017.
 */

public class ModelLogin {
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;

    public AccessToken getTokenFacebookCurrent(){

        // Trong quá trình load, token có thay đổi hay bị mất gì đó
        // Hoặc để lâu quá token bị mất thì hàm này sẽ dò lại token cũ
        // Nghĩa là khi mất token thì hàm dưới này mới chạy
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };


        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return mGoogleApiClient;
    }

    // Nếu google đăng nhập thành công thì nó sẽ lưu vào cache
    public GoogleSignInResult getInfoLoginGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(optionalPendingResult.isDone()){
            return optionalPendingResult.get();
        }else{
            return null;
        }
    }

    public boolean checkLogin(Context context,String username, String password){
        boolean kiemtra = false;
        String duongdan = MainActivity.URL;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap",username);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",password);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJson downloadJson = new DownloadJson(duongdan,attrs);
        downloadJson.execute();

        try {
            String dulieu = downloadJson.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKetQua = jsonObject.getString("ketqua");
            if(jsonKetQua.equals("true")){
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");
                updateCacheLogin(context,tennv);

            }else{
                kiemtra = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

    public String getCacheLogin(Context context){
        SharedPreferences cachedLogin = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedLogin.getString("tennv","");
        return tennv;
    }

    public void updateCacheLogin(Context context,String tenv){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv",tenv);

        editor.commit();
    }

    public void destroyToken(){
      accessTokenTracker.stopTracking();
    }
}
