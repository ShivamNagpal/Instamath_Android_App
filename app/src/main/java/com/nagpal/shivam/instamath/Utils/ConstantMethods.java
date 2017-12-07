package com.nagpal.shivam.instamath.Utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class ConstantMethods {

    public static float dpToFloat(Resources resources, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
