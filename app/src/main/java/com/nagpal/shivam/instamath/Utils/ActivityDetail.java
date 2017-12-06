package com.nagpal.shivam.instamath.Utils;

public class ActivityDetail {
    private String mName;
    private Class mActivityClass;

    public ActivityDetail(String name, Class activityClass) {
        mName = name;
        mActivityClass = activityClass;
    }

    public String getName() {
        return mName;
    }

    public Class getActivityClass() {
        return mActivityClass;
    }
}