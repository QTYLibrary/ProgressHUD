package com.qty.progresshud;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

class VerticalProgressHUDDialog extends BaseDialog {

    private View mDialogView;
    private FrameLayout mContentContainer;
    private TextView mLabelTv;
    private TextView mDetailTv;
    private View mContentView;
    private ViewGroup.LayoutParams mLayoutParams;
    private Drawable mBackgroud;

    private CharSequence mLabel;
    private CharSequence mDetail;
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
     * Progress dialog current progress
     */
    private int mProgress;

    public VerticalProgressHUDDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogView = LayoutInflater.from(getContext()).inflate(R.layout.vertical_dialog, null, false);
        setContentView(mDialogView);
        initViews();
    }

    private void initViews() {
        mDialogView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        mContentContainer = findViewById(R.id.content);
        mLabelTv = findViewById(R.id.label);
        mDetailTv = findViewById(R.id.detail);

        if (mBackgroud != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mDialogView.setBackground(mBackgroud);
            } else {
                mDialogView.setBackgroundDrawable(mBackgroud);
            }
        }

        mLabelTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mLabelTextSize);
        mLabelTv.setTextColor(mLabelTextColor);
        mDetailTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mDetailTextSize);
        mDetailTv.setTextColor(mDetailTextColor);

        if (mContentView != null && mContentView instanceof Indeterminate) {
            Indeterminate view = (Indeterminate)mContentView;
            view.setAnimationSpeed(mAnimSpeedRate);
        }

        if (mContentView != null && mContentView instanceof Determinate) {
            Determinate view = (Determinate)mContentView;
            view.setProgress(mProgress);
        }

        if (mContentView != null && mLayoutParams != null) {
            mContentContainer.removeAllViews();
            mContentContainer.addView(mContentView, mLayoutParams);
            if (mContentContainer.getVisibility() != View.VISIBLE) {
                mContentContainer.setVisibility(View.VISIBLE);
            }
        } else {
            if (mContentContainer.getVisibility() != View.GONE) {
                mContentContainer.setVisibility(View.GONE);
            }
        }

        if (!TextUtils.isEmpty(mLabel)) {
            mLabelTv.setText(mLabel);
            if (mLabelTv.getVisibility() != View.VISIBLE) {
                mLabelTv.setVisibility(View.VISIBLE);
            }
        } else {
            if (mLabelTv.getVisibility() != View.GONE) {
                mLabelTv.setVisibility(View.GONE);
            }
        }

        if (!TextUtils.isEmpty(mDetail)) {
            mDetailTv.setText(mDetail);
            if (mDetailTv.getVisibility() != View.VISIBLE) {
                mDetailTv.setVisibility(View.VISIBLE);
            }
        } else {
            if (mDetailTv.getVisibility() != View.GONE) {
                mDetailTv.setVisibility(View.GONE);
            }
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
        if (mDialogView != null) {
            mDialogView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }
    }

    public void setView(View view, ViewGroup.LayoutParams layoutParams) {
        mContentView = view;
        mLayoutParams = layoutParams;
        if (mContentContainer != null && mLayoutParams != null) {
            if (view != null) {
                mContentContainer.removeAllViews();
                mContentContainer.addView(view, layoutParams);
                if (mContentContainer.getVisibility() != View.VISIBLE) {
                    mContentContainer.setVisibility(View.VISIBLE);
                }
            } else {
                if (mContentContainer.getVisibility() != View.GONE) {
                    mContentContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setText(CharSequence text) {
        mLabel = text;
        if (mLabelTv != null) {
            if (!TextUtils.isEmpty(text)) {
                mLabelTv.setText(text);
                if (mLabelTv.getVisibility() != View.VISIBLE) {
                    mLabelTv.setVisibility(View.VISIBLE);
                }
            } else {
                if (mLabelTv.getVisibility() != View.GONE) {
                    mLabelTv.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setDetail(CharSequence detail) {
        mDetail = detail;
        if (mDetailTv != null) {
            if (!TextUtils.isEmpty(detail)) {
                mDetailTv.setText(detail);
                if (mDetailTv.getVisibility() != View.VISIBLE) {
                    mDetailTv.setVisibility(View.VISIBLE);
                }
            } else {
                if (mDetailTv.getVisibility() != View.GONE) {
                    mDetailTv.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setDialogBackground(Drawable drawable) {
        mBackgroud = drawable;
        if (mDialogView != null && drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mDialogView.setBackground(mBackgroud);
            } else {
                mDialogView.setBackgroundDrawable(mBackgroud);
            }
        }
    }

    public void setLabelTextSize(float size) {
        mLabelTextSize = size;
        if (mLabelTv != null) {
            mLabelTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mLabelTextSize);
        }
    }

    public void setLabelTextColor(int color) {
        mLabelTextColor = color;
        if (mLabelTv != null) {
            mLabelTv.setTextColor(mLabelTextColor);
        }
    }

    public void setDetailTextSize(float size) {
        mDetailTextSize = size;
        if (mDetailTv != null) {
            mDetailTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mDetailTextSize);
        }
    }

    public void setDetailTextColor(int color) {
        mDetailTextColor = color;
        if (mDetailTv != null) {
            mDetailTv.setTextColor(mDetailTextColor);
        }
    }

    public void setAnimationSpeedRate(int rate) {
        mAnimSpeedRate = rate;
        if (mContentView != null && mContentView instanceof Indeterminate) {
            Indeterminate view = (Indeterminate)mContentView;
            view.setAnimationSpeed(mAnimSpeedRate);
        }
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mContentView != null && mContentView instanceof Determinate) {
            Determinate view = (Determinate)mContentView;
            view.setProgress(mProgress);
        }
    }

    public void showProgress(boolean show) {

    }
}
