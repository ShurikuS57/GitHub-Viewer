package com.taptm.shurikus.githubviewer.searchname;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.taptm.shurikus.githubviewer.GitHubViewerApplication;
import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.utils.ActivityUtils;

import javax.inject.Inject;

public class SearchNameActivity extends AppCompatActivity {

    @Inject
    SearchNamePresenter mSearchNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

        initToolbar();

        SearchNameFragment searchNameFragment = (SearchNameFragment) getFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(searchNameFragment == null){
            searchNameFragment = SearchNameFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    searchNameFragment, R.id.contentFrame);
        }

        DaggerSearchNameComponent.builder()
                .searchNamePresenterModule(new SearchNamePresenterModule(searchNameFragment))
                .repositoryComponent(((GitHubViewerApplication) getApplication())
                        .getRepositoryComponent())
                .build().inject(this);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_search_user);
    }
}
