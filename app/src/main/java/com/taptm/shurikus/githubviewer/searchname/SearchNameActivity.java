package com.taptm.shurikus.githubviewer.searchname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        setContentView(R.layout.search_name_act);

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
}
