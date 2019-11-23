package com.qty.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qty.progresshud.ProgressHUD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressHUD.getInstance().init(getApplicationContext());
        ProgressHUD.getInstance().setCanceledOnTouchOutside(true)
                .setListener(new ProgressHUD.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
    }
}
