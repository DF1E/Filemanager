package com.dnielfe.manager;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class BrowserActivityMainFunctionalTest extends AbstractBrowserActivityFunctionalTestCase {
    private BrowserActivity mBrowserActivity;

    @Rule
    public ActivityTestRule<BrowserActivity> mActivityRule =
            new ActivityTestRule<>(BrowserActivity.class);

    @Before
    public void setUp() throws Exception {
        mBrowserActivity = mActivityRule.getActivity();
        setAbsBrowserActivity(mBrowserActivity);
    }

    @After
    public void tearDown() throws Exception {
        mBrowserActivity.finish();
    }

    @Test
    public void testPreconditions() {
        assertNotNull(mBrowserActivity);
        assertThat(mBrowserActivity.hasWindowFocus(), is(true));
    }

    @Test
    public void testUiState_WithDefaultScreen_CorrectElementsDisplayed() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .check(matches(hasDescendant(withId(R.id.pager))));
        onView(withId(R.id.toolbar))
                .check(matches(hasDescendant(withText(R.string.app_name))));
        onView(withId(R.id.folderinfo))
                .check(matches(isDisplayed()));
        onView(withId(R.id.search))
                .check(matches(isDisplayed()));
        onView(withId(R.id.pick_cancel))
                .check(doesNotExist());
        onView(withId(R.id.directory_buttons))
                .check(matches(isDisplayed()))
                .check(matches(withChild(withText("/"))));
        onView(withId(R.id.indicator))
                .check(matches(isDisplayed()));
        onView(withId(R.id.pager))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withId(android.R.id.list))))
                .check(matches(hasDescendant(withId(R.id.fabbutton))));
        // TODO: check that list and fabbutton are actually displayed when fragments are tagged
    }

    // TODO: test folder info
    // TODO: test viewpager nav
    // TODO: test browser list item context actions
    // TODO: test add file
    // TODO: test add folder
}
