package com.qty.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

abstract class BaseDialog extends Dialog {

    /**
     * Intercept dialog key events
     */
    private boolean isInterceptKeyEvent = true;
    private Window mWindow;
    private Drawable mBackgroundDrawable;
    private float mDimAmount;
    private int mWindowType;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mWindow = getWindow();
        if (mWindow != null) {
            mWindow.setBackgroundDrawable(mBackgroundDrawable);
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mWindow.setDimAmount(mDimAmount);
            mWindow.setType(mWindowType);
            WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            mWindow.setAttributes(layoutParams);
            mWindow.setWindowAnimations(R.style.DialogWindowAnim);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (isInterceptKeyEvent) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * Set dialog background image
     * @param drawable Background Drawable
     */
    public void setWindowBackgroundDrawable(Drawable drawable) {
        mBackgroundDrawable = drawable;
        if (mWindow != null) {
            mWindow.setBackgroundDrawable(drawable);
        }
    }

    /**
     * Set the dimness of the dialog background
     * @param dimAmount Background dimness
     */
    public void setWindowBackgroundDimAmount(float dimAmount) {
        Log.d("qty", "setWindowBackgroundDimAmount=>dimAmount: " + dimAmount + ", mWindow: " + mWindow);
        mDimAmount = dimAmount;
        if (mWindow != null) {
            mWindow.setDimAmount(dimAmount);
        }
    }

    /**
     * Set dialog window type
     * @param type Window type
     */
    public void setWindowType(int type) {
        mWindowType = type;
        if (mWindow != null) {
            mWindow.setType(type);
        }
    }

    /**
     * Whether to intercept key events
     * @param enabled true intercept key events, or false disintercept key events
     */
    public void setInterceptKeyEvent(boolean enabled) {
        isInterceptKeyEvent = enabled;
    }
}
