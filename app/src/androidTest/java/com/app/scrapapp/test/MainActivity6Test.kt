package com.app.scrapapp.test

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId/*
import androidx.test.ext.truth.content.IntentSubject.assertThat*/
import androidx.test.runner.AndroidJUnit4
import com.app.scrapapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivity6Test {

    @JvmField
    @Rule
    var mActivityRule: IntentsTestRule<MainActivity6> = IntentsTestRule<MainActivity6>(
        MainActivity6::class.java
    )

    @Test
    fun mobileNumberIsEmpty() {
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextPhoneNumber)).check(matches(hasErrorText("Please enter your mobile number")))
    }

    @Test
    fun mobileNumberIsNotValid() {
        onView(withId(R.id.editTextPhoneNumber)).perform(typeText("989"))
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextPhoneNumber)).check(matches(hasErrorText("Please enter valid mobile number")))
    }

    @Test
    fun mobileNumberIsValid() {
        onView(withId(R.id.editTextPhoneNumber)).perform(typeText("9890352190"))
        onView(withId(R.id.buttonLogin)).perform(click())

        val intent: Intent = Iterables.getOnlyElement(Intents.getIntents())/*
        assertThat(intent).extras().containsKey("phoneNumber")
        assertThat(intent).extras().string("phoneNumber").isEqualTo("9890352190")*/
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
