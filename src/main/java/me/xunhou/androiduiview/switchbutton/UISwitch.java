package me.xunhou.androiduiview.switchbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ihgoo on 2015/8/29.
 */
public class UISwitch extends View{



    private int motionX;
    private int motionY;


    public UISwitch(Context context) {
        super(context);
    }

    public UISwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UISwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                motionX = (int) event.getX();
                break;
            case MotionEvent.ACTION_DOWN:
                motionX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        return super.onTouchEvent(event);
    }
}
