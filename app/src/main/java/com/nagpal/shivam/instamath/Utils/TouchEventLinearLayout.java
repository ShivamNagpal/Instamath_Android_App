/*
 * Copyright 2018 Shivam Nagpal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagpal.shivam.instamath.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventLinearLayout extends LinearLayout {
    private TouchEventListener touchEventListener;
    private boolean actionUpActive;
    private float downY;

    public TouchEventLinearLayout(Context context) {
        super(context);
    }

    public TouchEventLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setActionUpActive(boolean actionUpActive) {
        this.actionUpActive = actionUpActive;
    }

    public void setTouchEventListener(TouchEventListener touchEventListener) {
        this.touchEventListener = touchEventListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (touchEventListener == null) {
            return super.onInterceptTouchEvent(ev);
        }

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (actionUpActive) {
                    touchEventListener.onActionUp();
                    return true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                downY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dY = ev.getRawY() - downY;
                if (Math.abs(dY) > 200) {
                    if (dY > 0) {
                        touchEventListener.onSlideDown();
                    } else if (dY < 0) {
                        touchEventListener.onSlideUp();
                    }
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);

    }

    public interface TouchEventListener {
        void onActionUp();

        void onSlideUp();

        void onSlideDown();
    }
}
