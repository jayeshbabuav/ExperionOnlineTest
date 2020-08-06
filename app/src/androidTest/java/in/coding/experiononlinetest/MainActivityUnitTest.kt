package `in`.coding.experiononlinetest

import `in`.coding.experiononlinetest.views.MainActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityUnitTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //testing if recyclerview is visible
    @Test
    fun isRecyclerViewVisible() {
        onView(withId(R.id.itemsView)).check(ViewAssertions.matches(isDisplayed()))
    }
}