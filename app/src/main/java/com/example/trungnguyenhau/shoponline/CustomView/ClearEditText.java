package com.example.trungnguyenhau.shoponline.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.trungnguyenhau.shoponline.R;

/**
 * Created by NguyenTrungHau on 8/11/2017.
 */

// Mỗi constructor sẽ đại diện cho mỗi phiên bản khác nhau
public class ClearEditText extends EditText {
    private Drawable imageX, noneImageX,drawable;;
    private Boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        initialView();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialView();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialView();
    }

    private void initialView(){
        imageX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        noneImageX = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();

        setUp();
    }

    private void setUp(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible ? imageX : noneImageX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }

    // Khi nhập vào ediitext sẽ thay chạy vào sự kiện này
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            setUp();
        }else{
            visible = true;
            setUp();
        }

    }
}
