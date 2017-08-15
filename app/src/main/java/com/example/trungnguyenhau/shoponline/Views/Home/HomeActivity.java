package com.example.trungnguyenhau.shoponline.Views.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trungnguyenhau.shoponline.Adapter.ExpandAdapter;
import com.example.trungnguyenhau.shoponline.Adapter.ViewPagerAdapter;
import com.example.trungnguyenhau.shoponline.Model.GerneralModel.TypeProduct;
import com.example.trungnguyenhau.shoponline.Model.Login.ModelLogin;
import com.example.trungnguyenhau.shoponline.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.trungnguyenhau.shoponline.Presenter.Home.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy.LoginActivity;
import com.example.trungnguyenhau.shoponline.Views.GioHang.GioHangActivity;
import com.example.trungnguyenhau.shoponline.Views.Search.SearchActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewXuLyMenu,
        GoogleApiClient.OnConnectionFailedListener ,AppBarLayout.OnOffsetChangedListener, View.OnClickListener{

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private TextView txtGioHang;
    private Boolean onPause = false;

    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ExpandableListView epdListViewCaregory;

    private DrawerLayout drawerLayout;
    // Là cái nút góc trái menu
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String usernameFacebook = "";
    private AccessToken accessToken;
    private PresenterLogicXuLyMenu presenterLogicXuLyMenu;
    private ModelLogin modelLogin;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInResult googleSignInResult;
    private MenuItem menuLogin,menuLogout;
    private Menu menu;
    private Button button_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_home);
        addControls();
        addEvents();

    }

    private void addEvents() {
        button_search.setOnClickListener(this);
    }

    private void addControls() {
        button_search = (Button) findViewById(R.id.button_search);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // Viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // Drawerlayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        // ExpandableListView
        epdListViewCaregory = (ExpandableListView) findViewById(R.id.expandableview_menuhome);


        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        // Tại vì đang sài gói thư viện hỗ trợ nên setSupportActionBar thì mới hiện toolbar
        setSupportActionBar(toolbar);


        // actionBarDrawerToggle phải để dưới toolbar thì mới đổi màu actionBarDrawerToggle vì
        // actionBarDrawerToggle ghi đè
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        // Thêm actionBarDrawerToggle vào drawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Vì mình sẽ lây component trên actionBar,
        // cho phép nút toggle nhấn ra vô
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();


        /*progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải vui lòng chờ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        // Gắn tab thương hiệu trên trang chủ
        tabLayout.setupWithViewPager(viewPager);

        // Xử lý logic cho menu
        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu(this);
        presenterLogicXuLyMenu.LayDanhSachMenu();

        // Muốn lấy được username, email, tên profile thì phải lấy được token ra
        // Tới đây đã in ra được token rồi
        //Log.d("token", presenterLogicXuLyMenu.getUserFacebook() + "");
        Log.d("token", presenterLogicXuLyMenu.getUserFacebook() + "");

        modelLogin = new ModelLogin();
        mGoogleApiClient = modelLogin.getGoogleApiClient(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        this.menu = menu;
        menuLogin = menu.findItem(R.id.itDangNhap);
        menuLogout = menu.findItem(R.id.itDangXuat);

        // Giỏ hàng
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(HomeActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        accessToken = presenterLogicXuLyMenu.getUserFacebook();

        // Nếu để hàm này trên onCreate thì googleSignInResult luôn luôn khác null
        googleSignInResult = modelLogin.getInfoLoginGoogle(mGoogleApiClient);

        // Nghĩa là đã đăng nhập facebook
        if(accessToken != null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        usernameFacebook = object.getString("name");

                        menuLogin.setTitle("Username: " + usernameFacebook);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Sau khi lấy tên username facebook xong thì nó sẽ
            // trả về graphRequest
            Bundle parameter = new Bundle();
            // Các tham số phải ghi đúng từng chữ như trong tài liệu facebook
            parameter.putString("fields","name");

            graphRequest.setParameters(parameter);
            // Bản chất của executeAsync cũng giống như AsyncTask là chạy bất đồng bộ
            graphRequest.executeAsync();
        }

        String tennv = modelLogin.getCacheLogin(this);
        if(!tennv.equals("")){
            menuLogin.setTitle(tennv);
        }

        if(googleSignInResult != null){
            menuLogin.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        if (accessToken != null || googleSignInResult != null || !tennv.equals(""))
        {
            menuLogout.setVisible(true);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Nếu actionBarDrawerToggle được click trên menu thì mở ra
        // vì bản chất cái item đó nằm trên menu
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                // getToken: Chính là mã của người dùng đó.
                // Đôi khi nhiều lúc accessToken vẫn rỗng nhưng mã người dùng không rỗng
                // nghia là có đăng nhập
                if(accessToken == null && googleSignInResult == null){
                    Intent iDangNhap = new Intent(this, LoginActivity.class);
                    startActivity(iDangNhap);
                }
                break;

            case R.id.itDangXuat:
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

                if(googleSignInResult != null){
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);

                }

                if(!modelLogin.getCacheLogin(this).equals(""))
                {
                    modelLogin.updateCacheLogin(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;

            case R.id.itSearch:
                Intent iSearch = new Intent(this, SearchActivity.class);
                startActivity(iSearch);
                break;
            case R.id.itDonHangCuaToi:

                break;


        }

        return true;
    }

    // Chỉ Hiển thị thông tin xử lý menu
    @Override
    public void HienThiDanhSachMenu(List<TypeProduct> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(HomeActivity.this, loaiSanPhamList);
        epdListViewCaregory.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        // Nếu trượt lên thì mất thanh tìm kiếm và menu tìm kiếm hiện ra, nếu hiển thị thanh tìm kiếm
        // thì menu tìm kiếm mất
        if(collapsingToolbarLayout.getHeight() + verticalOffset <=  1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
            LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

            MenuItem itSearch = menu.findItem(R.id.itSearch);
            itSearch.setVisible(true);

        }else{
            LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
            try{
                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(false);
            }catch (Exception e){

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause){
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    public void onClick(View view) {
        Intent iSearch = new Intent(this, SearchActivity.class);
        startActivity(iSearch);
    }
}
