package com.qty.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qty.progresshud.ProgressHUD;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVTextWithLoadingAutoDismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ProgressHUD.getInstance(this).dismiss();
    }

    private void showText() {
        ProgressHUD.getInstance(this).showText("正在加载...");
    }

    private void showTextAutoDismiss() {
        ProgressHUD.getInstance(this).setListener(() -> Log.d(TAG, "ProgressHUD dismiss.")).showText("正在加载...", 1500);
    }

    private void showHTextWithLoading() {
        ProgressHUD.getInstance(this).showHTextWithLoading("正在加载...");
    }

    private void showHTextWithLoadingAutoDismiss() {
        ProgressHUD.getInstance(this).setListener(() -> Log.d(TAG, "ProgressHUD dismiss.")).showHTextWithLoading("正在加载...", 1500);
    }

    private void showVTextWithLoading() {
        ProgressHUD.getInstance(this).showVTextWithLoading("正在加载...");
    }

    private void showVTextWithLoadingAutoDismiss() {
        ProgressHUD.getInstance(this).setListener(() -> Log.d(TAG, "ProgressHUD dismiss.")).showVTextWithLoading("正在加载...", 1500);
    }

    private void showLoading() {
        ProgressHUD.getInstance(this).showLoading();
    }

    private void showLoadingAutoDismiss() {
        ProgressHUD.getInstance(this).showLoading(1500);
    }

    private void showState() {
        ProgressHUD.getInstance(this).showVTextWithState("成功", R.drawable.ic_ok, 1500);
    }

    public int dpToPixel(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale);
    }
}
