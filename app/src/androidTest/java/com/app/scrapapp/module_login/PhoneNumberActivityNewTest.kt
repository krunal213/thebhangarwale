package com.app.scrapapp.module_login

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.app.scrapapp.R
import com.app.scrapapp.test.Test.hasTextInputLayoutErrorText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PhoneNumberActivityNewTest {

    @JvmField
    @Rule
    var mActivityRule: ActivityTestRule<PhoneNumberActivityNew> =
        ActivityTestRule<PhoneNumberActivityNew>(
            PhoneNumberActivityNew::class.java
        )

    @Test
    fun mobileNumberIsEmpty() {
       onView(ViewMatchers.withId(R.id.linearLayoutContinueNew)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.textInputLayoutPhoneNumberNew))
            .check(ViewAssertions.matches(hasTextInputLayoutErrorText("Please enter your mobile number")))


    }

}








