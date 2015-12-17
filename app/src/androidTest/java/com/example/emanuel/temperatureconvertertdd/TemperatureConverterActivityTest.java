package com.example.emanuel.temperatureconvertertdd;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isRightAlignedWith;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.emanuel.temperatureconvertertdd.CustomMatchers.onScreen;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.theInstance;

/**
 * Created by emanuel on 16/12/15.
 */
public class TemperatureConverterActivityTest extends ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity activity;


    public TemperatureConverterActivityTest() {
        super(TemperatureConverterActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public final void testPreconditions(){
       theInstance(activity).matches(notNullValue());
    }

    @SmallTest
    public final void testHasInputFields(){
       onView(withId(R.id.celsius)).check(matches(notNullValue()));
       onView(withId(R.id.fahrenheit)).check(matches(notNullValue()));

    }

    @SmallTest
    public final void testViewsEmptyAtBeginning(){
        onView(allOf(withId(R.id.celsius),withText(""))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fahrenheit),withText(""))).check(matches(isDisplayed()));
    }

    @SmallTest
    public final void testOnScreen(){
        onView(withId(R.id.celsius)).check(matches(onScreen(activity)));
        onView(withId(R.id.fahrenheit)).check(matches(onScreen(activity)));
    }

    @SmallTest
    /**
     * If view has padding alignment is true but if you're using margin alignment is false
     */
    public final void testAlignmentWithParent(){
        onView(withId(R.id.celsius_label)).check(isLeftAlignedWith(withId(R.id.parent_container)));
        onView(withId(R.id.celsius)).check(isLeftAlignedWith(withId(R.id.parent_container)));
        onView(withId(R.id.fahrenheit_label)).check(isLeftAlignedWith(withId(R.id.parent_container)));
        onView(withId(R.id.fahrenheit)).check(isLeftAlignedWith(withId(R.id.parent_container)));
    }

    @SmallTest
    public final void testAlignmentWithSiblings(){
        onView(withId(R.id.celsius)).check(isLeftAlignedWith(withId(R.id.celsius_label)));
        onView(withId(R.id.fahrenheit)).check(isLeftAlignedWith(withId(R.id.fahrenheit_label)));
        onView(withId(R.id.fahrenheit)).check(isLeftAlignedWith(withId(R.id.celsius)));
        onView(withId(R.id.fahrenheit)).check(isRightAlignedWith(withId(R.id.celsius)));
    }

    @SmallTest
    public final void testViewPosition(){
        onView(withId(R.id.celsius)).check(isBelow(withId(R.id.celsius_label)));
        onView(withId(R.id.fahrenheit)).check(isBelow(withId(R.id.fahrenheit_label)));
    }

    @SmallTest
    public final void testSiblings(){
        onView(withId(R.id.celsius))
                .check(matches(hasSibling(withId(R.id.celsius_label))))
                .check(matches(hasSibling(withId(R.id.fahrenheit_label))))
                .check(matches(hasSibling(withId(R.id.fahrenheit))));
    }

    public void tearDown() throws Exception {

    }
}