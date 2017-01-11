package com.taptm.shurikus.githubviewer.repo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.taptm.shurikus.githubviewer.GitHubViewerApplication;
import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.utils.ActivityUtils;

import javax.inject.Inject;

public class ReposActivity extends AppCompatActivity {

    public static final String EXTRA_URL_REPOS = "URL_REPOS";

    @Inject
    ReposPresenter mReposPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        String urlRepos = getIntent().getStringExtra(EXTRA_URL_REPOS);

        ReposFragment reposFragment = (ReposFragment) getFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(reposFragment == null){
            reposFragment = ReposFragment.newInstance(urlRepos);
            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    reposFragment, R.id.contentFrame);
        }

        DaggerReposComponent.builder()
                .reposPresenterModule(new ReposPresenterModule(reposFragment, urlRepos))
                .repositoryComponent(((GitHubViewerApplication) getApplication())
                .getRepositoryComponent())
                .build()
                .inject(this);
    }
}
