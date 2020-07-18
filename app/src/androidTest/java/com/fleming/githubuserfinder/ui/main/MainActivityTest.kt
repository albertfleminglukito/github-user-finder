package com.fleming.githubuserfinder.ui.main

import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fleming.githubuserfinder.R
import com.fleming.githubuserfinder.waitFor
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun validateShowErrorWhenKeywordEmpty() {
        onView(withId(R.id.etSearch)).perform(typeText(""))
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.tvErrorMessage)).check(matches(withText(R.string.message_get_user_keyword_empty_error)))
        onView(withId(R.id.btnErrorOk)).perform(click())
        onView(withId(R.id.tvErrorMessage)).check(doesNotExist())
    }

    @Test
    fun validateShowErrorWhenResultIsEmpty() {
        onView(withId(R.id.etSearch)).perform(typeText("impossiblegithubusernametest123321"))
        onView(withId(R.id.btnSearch)).perform(click())
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.tvErrorMessage)).check(matches(withText(R.string.message_get_user_empty_result_error)))
        onView(withId(R.id.btnErrorOk)).perform(click())
        onView(withId(R.id.tvErrorMessage)).check(doesNotExist())
    }

    @Test
    fun validateShowResult() {
        onView(withId(R.id.etSearch)).perform(typeText("octo"))
        onView(withId(R.id.btnSearch)).perform(click())
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.tvErrorMessage)).check(doesNotExist())
        onView(withContentDescription("tvUserName1")).check(matches(not(withText(""))))
    }

}
