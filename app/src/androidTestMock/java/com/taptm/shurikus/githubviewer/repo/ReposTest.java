package com.taptm.shurikus.githubviewer.repo;

import android.content.Intent;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReposTest {

    @Rule
    public ActivityTestRule<ReposActivity> mReposActivityTestRule =
            new ActivityTestRule<ReposActivity>(ReposActivity.class, true, false);


    @Test
    public void showRepos_DisplayedInUi() throws Exception {
        List<Repo> repos = FakeData.getFakeRepos();
        User user = FakeData.getFakeUsers().get(0);


        Intent intent = new Intent();
        intent.putExtra(ReposActivity.EXTRA_USER_NAME, user.getLogin());
        mReposActivityTestRule.launchActivity(intent);

        for (int i = 0; i < repos.size(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.list_repos))
                    .atPosition(i)
                    .onChildView(withId(R.id.text_repo_name))
                    .check(matches(withText(startsWith(repos.get(i).getName()))));
        }
    }

}
