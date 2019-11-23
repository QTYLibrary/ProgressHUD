package com.qty.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

/**
 * Progress indicator
 */
public class ProgressHUD {

    private Context context;
    private Dialog hudDialog;
    private OnDismissListener listener;
    private boolean canceledOnTouchOutside;

    private ProgressHUD() {
    }

    public static ProgressHUD getInstance() {
        return ProgressHUDSingle.sInstance;
    }

    /**
     * Initialization progress indicator
     * @param context Context
     */
    public void init(Context context) {
        this.context = context;
    }

    /**
     * Display text only
     * @param text The text to display
     */
    public void showText(CharSequence text) {
        showText(text, -1);
    }

    /**
     * Display text only, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime  Time of disappearance, in milliseconds
     */
    public void showText(CharSequence text, int dismissTime) {
        // TODO:
    }

    /**
     * Horizontal layout with text and default loading image indicator
     * @param text The text to display
     */
    public void showHTextWithLoading(CharSequence text) {
        showHTextWithLoading(text, -1);
    }

    /**
     * Horizontal layout with text and default loading image indicator, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showHTextWithLoading(CharSequence text, int dismissTime) {
        // TODO:
    }

    /**
     * Vertical layout with text and default loading image indicator
     * @param text The text to display
     */
    public void showVTextWithLoading(CharSequence text) {
        showVTextWithLoading(text, -1);
    }

    /**
     * Vertical layout with text and default loading image indicator, and disappear after the specified time
     * @param text The text to display
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showVTextWithLoading(CharSequence text, int dismissTime) {
        // TODO:
    }

    /**
     * Indicator showing status icons
     * @param stateResId Status picture resource ID
     */
    public void showState(@DrawableRes int stateResId) {
        showState(stateResId, -1);
    }

    /**
     * Indicator showing status icons, and disappear after the specified time
     * @param stateResId Status picture resource ID
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showState(@DrawableRes int stateResId, int dismissTime) {
        showHTextWithState(null, stateResId, dismissTime);
    }

    /**
     * Horizontal layout indicator with status text and status picture
     * @param text Status text
     * @param stateResId Status picture resource ID
     */
    public void showHTextWithState(CharSequence text, @DrawableRes int stateResId) {
        showHTextWithState(text, stateResId, -1);
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
     * Vertical layout indicator with status text and status picture
     * @param text Status text
     * @param stateResId Status picture resource ID
     */
    public void showVTextWithState(CharSequence text, int stateResId) {
        showVTextWithState(text, stateResId, -1);
    }

    /**
     * Vertical layout indicator with status text and status picture, and disappear after the specified time
     * @param text Status text
     * @param stateResId Status picture resource ID
     * @param dismissTime Time of disappearance, in milliseconds
     */
    public void showVTextWithState(CharSequence text, int stateResId, int dismissTime) {
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
        // TODO:
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
        canceledOnTouchOutside = enabled;
        return this;
    }

    /**
     * Dismiss display progress indicator
     */
    public void dismiss() {
        if (hudDialog != null) {
            hudDialog.dismiss();
            hudDialog = null;
        }
        if (this.listener != null) {
            this.listener.onDismiss();
            this.listener = null;
        }
        canceledOnTouchOutside = false;
    }

    /**
     * Set progress indicator disappear callback
     * @param listener OnDismissListener
     */
    public void setListener(OnDismissListener listener) {
        this.listener = listener;
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    private static final class ProgressHUDSingle {
        private static final ProgressHUD sInstance = new ProgressHUD();
    }

}

class ProgressHUDDialog extends BaseDialog {


    public ProgressHUDDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);
    }
}
