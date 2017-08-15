package com.example.trungnguyenhau.shoponline;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.trungnguyenhau.shoponline.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainFragment mainFragment;

    public final static String URL = "http://192.168.0.101:88/shop24h/loaisanpham.php";
    public final static String HOST = "http://192.168.0.101:88/shop24h";

    // 2 Fragment: 1 fragment cho các đối tượng có chung cấu trúc,
    // và một fragment cho shopping
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ẩn toolbar, 2 dòng code phía dưới phải ở trên setContentView
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
    }

    private void addEvent() {

    }

    private void addControls() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // Khi chạy thì nó sé vào MainFragment
        mainFragment = MainFragment.newInstance();
        fragmentTransaction.replace(R.id.frame_container, mainFragment);
        fragmentTransaction.commit();
    }
}
