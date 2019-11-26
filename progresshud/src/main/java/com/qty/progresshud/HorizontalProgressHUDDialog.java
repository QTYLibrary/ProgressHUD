package com.qty.progresshud;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

class HorizontalProgressHUDDialog extends BaseDialog {

    private FrameLayout mContentContainer;
    private TextView mLabelTv;
    private View mContentView;
    private CharSequence mLabel;
    private CharSequence mDetail;
    private ViewGroup.LayoutParams mLayoutParams;

    public HorizontalProgressHUDDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_dialog);
        initViews();
    }

    private void initViews() {
        mContentContainer = findViewById(R.id.content);
        mLabelTv = findViewById(R.id.label);

        if (mContentView != null) {
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

    }

    public void setView(View view, ViewGroup.LayoutParams layoutParams) {
        mContentView = view;
        mLayoutParams = layoutParams;
        if (mContentContainer != null) {
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

}
