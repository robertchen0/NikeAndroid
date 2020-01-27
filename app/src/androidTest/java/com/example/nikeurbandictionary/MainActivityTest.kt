package com.example.nikeurbandictionary

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nikeurbandictionary.model.CustomAdapter
import com.example.nikeurbandictionary.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)
    val testInput = "lol"

    @Test
    fun doSearchWithDefinedInput(){
        Espresso.onView(
            ViewMatchers.withId(R.id.et_search))
            .perform(ViewActions.typeText(testInput))
        Espresso.onView(
            ViewMatchers.withId(R.id.btn_search))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        Espresso.onView(
            ViewMatchers.withId(R.id.recylerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CustomAdapter.CustomViewHolder>(3, click()))
    }
}