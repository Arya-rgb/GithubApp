package com.kompas.githubapp.ui.userlist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import com.kompas.githubapp.R

import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.matches

@RunWith(AndroidJUnit4::class)
class UserListActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(UserListActivity::class.java)
    }

    @Test
    fun loadUser() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }


}