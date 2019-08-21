package com.ottsolution.demo.customviews;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;
import com.ottsolution.demo.ui.features.home.HomeActivity;

import static com.ottsolution.demo.utils.UtilityKt.getRealDisplayPoint;

public class MyGallery extends Gallery {
    private GalleryListener mlistener;
    public static final int RIGHT = 1;
    public int VELOCITY = 4050;

    public MyGallery(Context context) {
        super(context);
    }

    public MyGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);

        int x = getRealDisplayPoint(getContext()).x;
        VELOCITY = (int) (x * 3.8);
        setAnimationDuration(500);
    }

    public void setListener(GalleryListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Context context = this.getContext();

            if (mlistener != null) mlistener.onStartSlide();

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int kEvent;

        if (isScrollingLeft(e1, e2)) { // Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else { // Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }

        onKeyDown(kEvent, null);
        return false;
    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }

    /**
     * Slide direction=1 to the right, the left -1
     */
    public void slide(int direction) {
        MotionEvent e1 = MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN,
                300.0f, 265.33334f, 0);

        MotionEvent e2 = MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 89.333336f,
                238.00003f, 0);

        if (direction == RIGHT) this.onFling(e1, e2, -VELOCITY, 0);
        else this.onFling(e1, e2, VELOCITY, 0);
    }
}
