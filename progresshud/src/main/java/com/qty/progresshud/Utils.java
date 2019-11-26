package com.qty.progresshud;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.IntegerRes;

public class Utils {

    private static float scale;

    public static int dpToPixel(float dp, Context context) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }
}
