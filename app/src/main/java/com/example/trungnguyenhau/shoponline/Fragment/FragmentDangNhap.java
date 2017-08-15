package com.example.trungnguyenhau.shoponline.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline.Model.Login.ModelLogin;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.Home.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private CallbackManager callbackManager;
    private Button btnDangNhap, btnDangNhapGooglePlus;
    private LoginButton btnDangNhapFacebook;
    private EditText edtUsername, edtPassword;
    private View view;

    private ProgressDialog progressDialog;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private int RC_SIGN_IN = 999;

    private ModelLogin modelLogin;
    public FragmentDangNhap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        modelLogin = new ModelLogin();
        mGoogleApiClient = modelLogin.getGoogleApiClient(getActivity(), this);

        // Hàm này phải đặt đầu tiên khởi tạo, nếu khai báo dưới setContentView sẽ báo lỗi
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        AppEventsLogger.activateApp(getContext());

        view = inflater.inflate(R.layout.layout_fragment_dang_nhap, container, false);
        addControls();
        initialFacebook();

        // Add code to print out the key hash
        //try {
        //    PackageInfo info = getActivity().getPackageManager().getPackageInfo(
        //            "com.example.trungnguyenhau.shoponline",
        //            PackageManager.GET_SIGNATURES);
        //    for (Signature signature : info.signatures) {
        //        MessageDigest md = MessageDigest.getInstance("SHA");
        //        md.update(signature.toByteArray());
        //        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
        //    }
        //} catch (PackageManager.NameNotFoundException e) {

        //} catch (NoSuchAlgorithmException e) {

        //}

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.button_dangnhap:
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                boolean check = modelLogin.checkLogin(getActivity(), username, password);
                if(check){
                    Intent home = new Intent(getActivity(), HomeActivity.class);
                    startActivity(home);
                }else{
                    Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_dangnhap_faceebook:
                // Sử dụng dòng lệnh này sexbaso lỗi
                LoginManager.getInstance().logInWithPublishPermissions(
                        FragmentDangNhap.this, Arrays.asList("public_profile", "email"));
                break;
            case R.id.button_dangnhap_google:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                showProcessDialog();
                break;
        }

    }

    private void addControls(){
        modelLogin = new ModelLogin();
        callbackManager = CallbackManager.Factory.create();
        edtUsername = (EditText) view.findViewById(R.id.edittext_email);
        edtPassword = (EditText) view.findViewById(R.id.edittext_password);
        btnDangNhap = (Button) view.findViewById(R.id.button_dangnhap);
        btnDangNhap.setOnClickListener(this);

        btnDangNhapFacebook = (LoginButton) view.findViewById(R.id.button_dangnhap_faceebook);
        btnDangNhapGooglePlus = (Button) view.findViewById(R.id.button_dangnhap_google);
        btnDangNhapGooglePlus.setOnClickListener(this);
    }

    private void initialFacebook(){
        // Khi nhấn login facebook thì nó sẽ chạy logInWithPublishPermissions, sau đó trả về
        // onActivityResult và sẽ hiển thị kết quả trạng thái vào registerCallback
        btnDangNhapFacebook.setReadPermissions(Arrays.asList("email"));
        btnDangNhapFacebook.setFragment(this);
        btnDangNhapFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Nếu thành công thì facebook sẽ đưa token vào cache rồi để khỏi cần load thêm lần nữa.
                //Toast.makeText(getContext(),
                //        "Thành Công. Id Username la " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();

                Intent intentHome = new Intent(getContext(), HomeActivity.class);
                startActivity(intentHome);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Khi click vào OK facebook thì nó sẽ chạy vào hàm này
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Toast.makeText(getContext(), result.getSignInAccount().getDisplayName(), Toast.LENGTH_SHORT).show();
            if (result.isSuccess())
            {
                progressDialog.dismiss();
                Intent intentHome = new Intent(getContext(), HomeActivity.class);
                startActivity(intentHome);
            }
        }

    }

    private void showProcessDialog(){
            if(progressDialog == null){
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.show();
        }
    }


    // Kết nối googleplus thất bại
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
        }
    }
}
