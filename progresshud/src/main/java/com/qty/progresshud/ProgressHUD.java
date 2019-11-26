package com.qty.progresshud;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

/**
 * Progress indicator
 */
public class ProgressHUD {

    /**
     * Context object, must inherit from ContextWrapper
     */
    private Context mContext;

    /**
     * Resource object
     */
    private Resources mResources;

    /**
     * Dialog for displaying HUD content
     */
    private Dialog mHudDialog;

    /**
     * Handler object to delay the dismiss dialog
     */
    private Handler mHandler;

    /**
     * Window background drawable
     */
    private Drawable mWindownBg;

    /**
     * Dialog content background drawable
     */
    private Drawable mDialogBg;

    /**
     * Dismiss listener
     */
    private OnDismissListener mListener;

    /**
     * Dialog padding left
     */
    private int mPaddingLeft;

    /**
     * Dialog padding top
     */
    private int mPaddingTop;

    /**
     * Dialog padding right
     */
    private int mPaddingRight;

    /**
     * Dialog padding bottom
     */
    private int mPaddingBottom;

    /**
     * Dialog label text size
     */
    private float mLabelTextSize;

    /**
     * Dialog detail text size
     */
    private float mDetailTextSize;

    /**
     * Dialog label text color
     */
    private int mLabelTextColor;

    /**
     * Dialog detail text color
     */
    private int mDetailTextColor;

    /**
     * Dialog state or loading view animation speed
     */
    private int mAnimSpeedRate;

    /**
     * Delay show dialog time
     */
    private int mDelayTime;

    /**
     * Progress dialog current progress
     */
    private int mProgress;

    /**
     * Window background dimamount, Value must be greater than or equal to 0.0 and less than or equal to 1.0
     */
    private float mDimAmount;

    /**
     * Progress dialog is show current progress
     */
    private boolean isShowProgress;

    /**
     * Tap off screen to cancel HUD
     */
    private boolean isCanceledOnTouchOutside;

    /**
     * Intercept dialog key events
     */
    private boolean isInterceptKeyEvent = true;

    private Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            if (mHudDialog != null) {
                mHudDialog.show();
            }
        }
    };

    private Runnable mDismissRunnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };

    private ProgressHUD() {
        mHandler = new Handler();
    }

    public static ProgressHUD getInstance(ContextWrapper context) {
        ProgressHUDSingle.sInstance.setContext(context);
        return ProgressHUDSingle.sInstance;
    }

    /**
     * Initialization progress indicator
     * @param context Context
     */
    private void setContext(Context context) {
        if (context != null) {
            mContext = context;
            mResources = context.getResources();
            setDefaultValue();
        }
    }

    private void setDefaultValue() {
        mWindownBg = mResources.getDrawable(R.drawable.window_background);
        mDialogBg = mResources.getDrawable(R.drawable.dialog_bg);
        mPaddingLeft = mResources.getDimensionPixelSize(R.dimen.padding_left);
        mPaddingTop = mResources.getDimensionPixelSize(R.dimen.padding_top);
        mPaddingRight = mResources.getDimensionPixelSize(R.dimen.padding_right);
        mPaddingBottom = mResources.getDimensionPixelSize(R.dimen.padding_bottom);
        mLabelTextSize = mResources.getInteger(R.integer.label_text_size);
        Log.d("qty", "setDefaultValue=>size: " + mLabelTextSize);
        mLabelTextColor = mResources.getColor(R.color.label_text_color);
        mDetailTextSize = mResources.getInteger(R.integer.detail_text_size);
        mDetailTextColor = mResources.getColor(R.color.detail_text_color);
        mAnimSpeedRate = mResources.getInteger(R.integer.animation_speed);
        mDimAmount = mResources.getInteger(R.integer.window_dimamount);
        isShowProgress = mResources.getBoolean(R.bool.show_progress);
        isInterceptKeyEvent = mResources.getBoolean(R.bool.intercept_key_event);
        isCanceledOnTouchOutside = mResources.getBoolean(R.bool.canceled_on_touch_outside);
    }


    public void showLoading() {
        showLoading(0);
    }

    public void showLoading(int dismissTime) {
        View view = new SpinView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mResources.getDimensionPixelSize(R.dimen.vertical_loading_view_width),
                mResources.getDimensionPixelSize(R.dimen.vertical_loading_view_height));
        showVTextWithLoading(view, layoutParams, null, dismissTime);
    }

    /**
     * Indicator showing status icons
     * @param stateResId Status picture resource ID
     */
    public void showState(@DrawableRes int stateResId) {
        showState(stateResId, 0);
    }

    /**
     * Indicator showing status icons, and disappear after the specified time
     * @param stateResId Status picture resource ID
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showState(@DrawableRes int stateResId, int dismissTime) {
        showVTextWithState(null, stateResId, dismissTime);
    }

    /**
     * Vertical layout indicator with status text and status picture
     * @param text Status text
     * @param stateResId Status picture resource ID
     */
    public void showVTextWithState(CharSequence text, int stateResId) {
        showVTextWithState(text, stateResId, 0);
    }

    /**
     * Vertical layout indicator with status text and status picture, and disappear after the specified time
     * @param text Status text
     * @param stateResId Status picture resource ID
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showVTextWithState(CharSequence text, int stateResId, int dismissTime) {
        ImageView view = new ImageView(mContext);
        view.setImageResource(stateResId);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        showVTextWithLoading(view, layoutParams, text, dismissTime);
    }


    /**
     * Display text only
     * @param text The text to display
     */
    public void showText(CharSequence text) {
        showText(text, 0);
    }

    /**
     * Display text only, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime  Time of disappearance, in milliseconds
     */
    public void showText(CharSequence text, int dismissTime) {
       showVTextWithLoading(null, null, text, dismissTime);
    }

    /**
     * Vertical layout with text and default loading image indicator
     * @param text The text to display
     */
    public void showVTextWithLoading(CharSequence text) {
        showVTextWithLoading(text, 0);
    }

    /**
     * Vertical layout with text and default loading image indicator, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showVTextWithLoading(CharSequence text, int dismissTime) {
        View view = new SpinView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mResources.getDimensionPixelSize(R.dimen.vertical_loading_view_width),
                mResources.getDimensionPixelSize(R.dimen.vertical_loading_view_height));
        showVTextWithLoading(view, layoutParams, text, dismissTime);
    }

    public void showVTextWithLoading(View view, ViewGroup.LayoutParams layoutParams, CharSequence text, int dismissTime) {
        if (mHudDialog == null || (mHudDialog != null && !(mHudDialog instanceof VerticalProgressHUDDialog))) {
            if (mHudDialog != null) {
                mHudDialog.dismiss();
                mHudDialog = null;
            }
        }
        if (mHudDialog == null) {
            mHudDialog = new VerticalProgressHUDDialog(mContext);
        }
        VerticalProgressHUDDialog dialog = (VerticalProgressHUDDialog)mHudDialog;
        dialog.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        dialog.setWindowBackgroundDrawable(mWindownBg);
        dialog.setWindowBackgroundDimAmount(mDimAmount);
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        dialog.setInterceptKeyEvent(isInterceptKeyEvent);
        dialog.showProgress(isShowProgress);
        dialog.setDialogBackground(mDialogBg);
        dialog.setLabelTextSize(mLabelTextSize);
        dialog.setLabelTextColor(mLabelTextColor);
        dialog.setDetailTextSize(mDetailTextSize);
        dialog.setDetailTextColor(mDetailTextColor);
        dialog.setAnimationSpeedRate(mAnimSpeedRate);
        dialog.setProgress(mProgress);
        dialog.setView(view, layoutParams);
        dialog.setText(text);
        dialog.setDetail(null);
        if (mDelayTime > 0) {
            mHandler.postDelayed(mShowRunnable, mDelayTime);
            if (dismissTime > 0) {
                mHandler.postDelayed(mDismissRunnable, dismissTime + mDelayTime);
            }
        } else {
            dialog.show();
            if (dismissTime > 0) {
                mHandler.postDelayed(mDismissRunnable, dismissTime);
            }
        }
    }


    /**
     * Horizontal layout with text and default loading image indicator
     * @param text The text to display
     */
    public void showHTextWithLoading(CharSequence text) {
        showHTextWithLoading(text, 0);
    }

    /**
     * Horizontal layout with text and default loading image indicator, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showHTextWithLoading(CharSequence text, int dismissTime) {
        if (mHudDialog == null || (mHudDialog != null && !(mHudDialog instanceof HorizontalProgressHUDDialog))) {
            if (mHudDialog != null) {
                mHudDialog.dismiss();
                mHudDialog = null;
            }
        }
        if (mHudDialog == null) {
            mHudDialog = new HorizontalProgressHUDDialog(mContext);
        }
        HorizontalProgressHUDDialog dialog = (HorizontalProgressHUDDialog)mHudDialog;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mResources.getDimensionPixelSize(R.dimen.horizontal_loading_view_width),
                mResources.getDimensionPixelSize(R.dimen.horizontal_loading_view_height));
        dialog.setView(new SpinView(mContext), layoutParams);
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        dialog.setText(text);
        dialog.show();
        if (dismissTime > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, dismissTime);
        }
    }


    /**
     * Horizontal layout indicator with status text and status picture
     * @param text Status text
     * @param stateResId Status picture resource ID
     */
    public void showHTextWithState(CharSequence text, @DrawableRes int stateResId) {
        showHTextWithState(text, stateResId, 0);
    }

    /**
     * Horizontal layout indicator with status text and status picture, and disappear after the specified time
     * @param text Status text
     * @param stateResId Status picture resource ID
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showHTextWithState(CharSequence text, @DrawableRes int stateResId, int dismissTime) {
        // TODO:
    }

    /**
     * Ring progress indicator
     * @param progress Current progress
     * @param showProgressText Show progress value in the ring
     */
    public void showRingProgress(float progress, boolean showProgressText) {
        showRingProgressWithText(null, progress, showProgressText);
    }

    /**
     * Ring progress indicator
     * @param progress Current progress
     * @param showProgressText Show progress value in the ring
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showRingProgress(float progress, boolean showProgressText, boolean autoDismiss) {
        showRingProgressWithText(null, progress, showProgressText, autoDismiss);
    }

    /**
     * Ring progress indicator with text
     * @param text The text to display
     * @param progress Current progress
     * @param showProgressText Show progress value in the ring
     */
    public void showRingProgressWithText(CharSequence text, float progress, boolean showProgressText) {
        showRingProgressWithText(text, progress, showProgressText, false);
    }

    /**
     * Ring progress indicator with text
     * @param text The text to display
     * @param progress Current progress
     * @param showProgressText Show progress value in the ring
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showRingProgressWithText(CharSequence text, float progress, boolean showProgressText, boolean autoDismiss) {
        // TODO:
    }

    /**
     * Circular progress indicator
     * @param progress Current progress
     */
    public void showCircleProgress(float progress) {
        showCircleProgressWithText(null, progress, false);
    }

    /**
     * Circular progress indicator, and disappear after the specified time
     * @param progress Current progress
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showCircleProgress(float progress, boolean autoDismiss) {
        showCircleProgressWithText(null, progress, autoDismiss);
    }

    /**
     * Circular progress indicator with text
     * @param progress Current progress
     */
    public void showCircleProgressWithText(CharSequence text, float progress) {
        showCircleProgressWithText(text, progress, false);
    }

    /**
     * Circular progress indicator with text, and disappear after the specified time
     * @param progress Current progress
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showCircleProgressWithText(CharSequence text, float progress, boolean autoDismiss) {
        // TODO:
    }

    /**
     * Horizontal progress bar indicator
     * @param progress Current progress
     * @param showProgressText Time of disappearance, in milliseconds
     */
    public void showHorizontalProgress(float progress, boolean showProgressText) {
        showHorizontalProgressWithText(null, progress, showProgressText, false);
    }

    /**
     * Horizontal progress bar indicator, and disappear after the specified time
     * @param progress Current progress
     * @param showProgressText Time of disappearance, in milliseconds
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showHorizontalProgress(float progress, boolean showProgressText, boolean autoDismiss) {
        showHorizontalProgressWithText(null, progress, showProgressText, autoDismiss);
    }

    /**
     * Horizontal progress bar indicator with text
     * @param text The text to display
     * @param progress Current progress
     * @param showProgressText Time of disappearance, in milliseconds
     */
    public void showHorizontalProgressWithText(CharSequence text, float progress, boolean showProgressText) {
        showHorizontalProgressWithText(text, progress, showProgressText, false);
    }

    /**
     * Horizontal progress bar indicator with text
     * @param text The text to display
     * @param progress Current progress
     * @param showProgressText Time of disappearance, in milliseconds
     * @param autoDismiss After the progress is over, it will disappear automatically.
     */
    public void showHorizontalProgressWithText(CharSequence text, float progress, boolean showProgressText, boolean autoDismiss) {
        // TODO:
    }

    /**
     * Custom progress indicator
     * @param view Progress indicator view
     */
    public void showCustomHUD(View view) {
        showCustomHUD(view, 0);
    }

    /**
     * Custom progress indicator, and disappear after the specified time
     * @param view Progress indicator view
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showCustomHUD(View view, int dismissTime) {
        // TODO:
    }

    /**
     * Click external to cancel the progress indicator
     * @param enabled Whether to allow click outside the progress indicator to cancel
     */
    public ProgressHUD setCanceledOnTouchOutside(boolean enabled) {
        isCanceledOnTouchOutside = enabled;
        return this;
    }

    public ProgressHUD setPadding(int padding) {
        mPaddingLeft = padding;
        mPaddingTop = padding;
        mPaddingRight = padding;
        mPaddingBottom = padding;
        return this;
    }

    public ProgressHUD setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
        return this;
    }

    public ProgressHUD setWindowBackgroundDrawable(@DrawableRes int resId) {
        setWindowBackgroundDrawable(mContext.getResources().getDrawable(resId));
        return this;
    }

    public ProgressHUD setWindowBackgroundDrawable(Drawable drawable) {
        if (drawable != null) {
            mWindownBg = drawable;
        }
        return this;
    }

    public ProgressHUD setWindowBackgroundDimAmount(float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }

    public ProgressHUD setContentBackgroundDrawable(@DrawableRes int resId) {
        setContentBackgroundDrawable(mContext.getResources().getDrawable(resId));
        return this;
    }

    public ProgressHUD setContentBackgroundDrawable(Drawable drawable) {
        mDialogBg = drawable;
        return this;
    }

    public ProgressHUD setLabelTextSize(int size) {
        mLabelTextSize = size;
        return this;
    }

    public ProgressHUD setDetailTextSize(int size) {
        mDetailTextSize = size;
        return this;
    }

    public ProgressHUD setLabelTextColor(int color) {
        mLabelTextColor = color;
        return this;
    }

    public ProgressHUD setDetailTextColro(int color) {
        mDetailTextColor = color;
        return this;
    }

    public ProgressHUD setAnimSpeedRate(int rate) {
        mAnimSpeedRate = rate;
        return this;
    }

    public ProgressHUD setDelayTime(int time) {
        mDelayTime = time;
        return this;
    }

    private ProgressHUD setProgress(int progress) {
        mProgress = progress;
        return this;
    }

    public ProgressHUD setInterceptKeyEvent(boolean enabled) {
        isInterceptKeyEvent = enabled;
        return this;
    }

    /**
     * Dismiss display progress indicator
     */
    public void dismiss() {
        mHandler.removeCallbacks(mShowRunnable);
        mHandler.removeCallbacks(mDismissRunnable);
        if (mHudDialog != null) {
            mHudDialog.dismiss();
            mHudDialog = null;
        }
        if (mListener != null) {
            mListener.onDismiss();
            mListener = null;
        }
        setDefaultValue();
    }

    /**
     * Set progress indicator disappear callback
     * @param listener OnDismissListener
     */
    public ProgressHUD setListener(OnDismissListener listener) {
        mListener = listener;
        return this;
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    private static final class ProgressHUDSingle {
        private static final ProgressHUD sInstance = new ProgressHUD();
    }

}



