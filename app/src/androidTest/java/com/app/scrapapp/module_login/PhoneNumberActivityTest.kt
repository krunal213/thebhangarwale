package com.app.scrapapp.module_login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.app.scrapapp.R
import com.app.scrapapp.testing.DataBindingIdlingResource
import com.app.scrapapp.testing.monitorActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PhoneNumberActivityTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun mobileNumberIsEmpty() {
        val activityScenario = ActivityScenario.launch(PhoneNumberActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        Espresso.onView(ViewMatchers.withId(R.id.linearLayoutContinue)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editTextPhoneNumber))
            .check(ViewAssertions.matches(ViewMatchers.hasErrorText("Please enter your mobile number")))
        //activityScenario.close()
    }


}
