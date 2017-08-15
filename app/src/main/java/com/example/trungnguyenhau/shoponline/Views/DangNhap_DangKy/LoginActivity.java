package com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;

import com.example.trungnguyenhau.shoponline.Adapter.ViewPagerAdaterDangNhap;
import com.example.trungnguyenhau.shoponline.R;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Tablayout vừa đăng nhập vừa đăng ký
public class LoginActivity extends AppCompatActivity{
    private TabLayout tabLayout_login;
    private ViewPager viewPager_login;
    private Toolbar toolbar_login;

    private ViewPagerAdaterDangNhap viewPagerAdaterDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();

    }

    private void addControls() {
        tabLayout_login = (TabLayout) findViewById(R.id.tab_login);

        viewPager_login = (ViewPager) findViewById(R.id.viewpager_login);
        viewPagerAdaterDangNhap = new ViewPagerAdaterDangNhap(getSupportFragmentManager());
        viewPager_login.setAdapter(viewPagerAdaterDangNhap);
        viewPagerAdaterDangNhap.notifyDataSetChanged();

        tabLayout_login.setupWithViewPager(viewPager_login);

        toolbar_login = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar_login);
    }

    private void addEvents() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }


}
