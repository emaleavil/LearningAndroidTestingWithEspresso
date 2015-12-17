package com.example.emanuel.temperatureconvertertdd;

import android.app.Activity;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by emanuel on 17/12/15.
 */
public class CustomMatchers {

    public static Matcher<View> onScreen(final Activity activity){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                int[] xy = new int[2];
                item.getLocationOnScreen(xy);

                int[] xyRoot = new int[2];
                activity.getWindow().getDecorView().getLocationOnScreen(xyRoot);

                int y = xy[1] - xyRoot[1];

               return y  >= 0 &&  y <= item.getRootView().getHeight();
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
