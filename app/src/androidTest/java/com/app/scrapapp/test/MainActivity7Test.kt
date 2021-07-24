package com.app.scrapapp.test

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*/*
import androidx.test.ext.truth.content.IntentSubject*/
import androidx.test.runner.AndroidJUnit4
import com.app.scrapapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivity7Test {

    @JvmField
    @Rule
    var mActivityRule: IntentsTestRule<MainActivity7> = IntentsTestRule<MainActivity7>(
        MainActivity7::class.java,
        true,
        false
    )

    @Test
    fun onOtpScreenLaunch() {
        val intent : Intent = Intent()
        intent.putExtra("phoneNumber","9890352190")
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.textViewPhoneNumber)).check(matches(withText("Your Phone Number is"+"\t"+9890352190)))
    }


    @Test
    fun otpIsEmpty() {
        val intent : Intent = Intent()
        intent.putExtra("phoneNumber","9890352190")
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.textViewPhoneNumber)).check(matches(withText("Your Phone Number is"+"\t"+9890352190)))

        onView(withId(R.id.buttonLogin)).perform(ViewActions.click())
        onView(withId(R.id.editTextOTP)).check(matches(hasErrorText("Please Enter Otp")))
    }

    @Test
    fun otpIsNotValid() {
        val intent : Intent = Intent()
        intent.putExtra("phoneNumber","9890352190")
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.textViewPhoneNumber)).check(matches(withText("Your Phone Number is"+"\t"+9890352190)))

        onView(withId(R.id.editTextOTP)).perform(ViewActions.typeText("989"))
        onView(withId(R.id.buttonLogin)).perform(ViewActions.click())
        onView(withId(R.id.editTextOTP)).check(matches(hasErrorText("Please Enter Valid Otp")))
    }

    @Test
    fun otpIsValid() {
        val intent : Intent = Intent()
        intent.putExtra("phoneNumber","9890352190")
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.textViewPhoneNumber)).check(matches(withText("Your Phone Number is"+"\t"+9890352190)))

        onView(withId(R.id.editTextOTP)).perform(ViewActions.typeText("9890"))
        onView(withId(R.id.buttonLogin)).perform(ViewActions.click())

        val intentOtp: Intent = Iterables.getOnlyElement(Intents.getIntents())/*
        IntentSubject.assertThat(intentOtp).extras().containsKey("phoneNumber")
        IntentSubject.assertThat(intentOtp).extras().string("phoneNumber").isEqualTo("9890352190")*/
    }




    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
