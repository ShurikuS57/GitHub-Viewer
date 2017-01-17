package com.taptm.shurikus.githubviewer.searchname;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.User;
import com.taptm.shurikus.githubviewer.data.source.remote.FakeData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchNameTest {

    @Rule
    public ActivityTestRule<SearchNameActivity> mTasksActivityTestRule =
            new ActivityTestRule<SearchNameActivity>(SearchNameActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();

                }
            };

    @Test
    public void searchEmptyName_CheckShowToast(){
        onView(withId(R.id.editText_search_name)).perform(clearText(), typeText(""));
        onView(withId(R.id.imageButton_search_name)).perform(click());

        Activity activity = mTasksActivityTestRule.getActivity();
        onView(withText(R.string.msg_search_not_be_empty)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkSerchName_CheckOpenRepos(){
        User user = FakeData.getFakeUsers().get(0);
        List<Repo> repos = FakeData.getFakeRepos();

        onView(withId(R.id.editText_search_name)).perform(clearText(), typeText(user.getLogin()));
        onView(withId(R.id.imageButton_search_name)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.list_result_name)).atPosition(0).perform(click());

        for (int i = 0; i < repos.size(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.list_repos))
                    .atPosition(i)
                    .onChildView(withId(R.id.text_repo_name))
                    .check(matches(withText(startsWith(repos.get(i).getName()))));
        }

    }



}
