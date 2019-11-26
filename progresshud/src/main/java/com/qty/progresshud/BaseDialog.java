package com.qty.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(0f);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);
            window.setWindowAnimations(R.style.DialogWindowAnim);
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
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(drawable);
        }
    }

    /**
     * Set the dimness of the dialog background
     * @param dimAmount Background dimness
     */
    public void setWindowBackgroundDimAmount(float dimAmount) {
        if (getWindow() != null) {
            getWindow().setDimAmount(dimAmount);
        }
    }

    /**
     * Set dialog window type
     * @param type Window type
     */
    public void setWindowType(int type) {
        if (getWindow() != null) {
            getWindow().setType(type);
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
