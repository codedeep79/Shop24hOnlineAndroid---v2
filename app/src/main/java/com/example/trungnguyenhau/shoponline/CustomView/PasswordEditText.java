package com.example.trungnguyenhau.shoponline.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.trungnguyenhau.shoponline.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by NguyenTrungHau on 8/11/2017.
 */

public class PasswordEditText extends EditText {
    private Drawable eye, eyeStrike;
    private Boolean useStrike = false;
    private Boolean useValidate = false;
    private Drawable drawable;
    private Boolean visible = false;
    private TextInputLayout textInputLayout;

    // Độ đục 70% của icon hình con mắt
    private int ALPHA = (int) (255 * .70f);

    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialView(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialView(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialView(attrs);
    }

    // PasswordEditText_useStrike trong styles.xml:
    // PasswordEditText: name trong styles.xml
    // useStrike: attr name trong styles.xml
    private void initialView(AttributeSet attrs){
        if(attrs != null){
            TypedArray array
                    = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            // Lây thuộc tính trong file styles.xml
            this.useStrike
                    = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
            this.useValidate
                    = array.getBoolean(R.styleable.PasswordEditText_useValidate, false);
        }

        // mutate(): Có thì nó mới vẽ drawable lên
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        // Khởi tạo pattern
        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()) {
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                        }
                        else {
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }
                    }
                }
            });
        }
        setUp();
    }

    // Nếu nhấn thì hiện kí tự ra của password, không nhấn thì passwork là kí tự chấm
    private  void setUp(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    // Có sự kiện onTouchEvent thì nó mới su dụng sự kiện khi nhấn vào
    // thay đổi icon hiển thị
    // Định nghĩa sự kiện chạm vào màn hình
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            setUp();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
