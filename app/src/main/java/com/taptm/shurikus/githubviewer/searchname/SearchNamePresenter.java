package com.taptm.shurikus.githubviewer.searchname;


import com.taptm.shurikus.githubviewer.data.source.Repository;

import javax.inject.Inject;

public class SearchNamePresenter implements SearchNameContract.Presenter {

    private final Repository mRepository;

    private final SearchNameContract.View mSearchNameView;

    @Inject
    SearchNamePresenter(Repository repository,
                        SearchNameContract.View searchNameView) {
        mRepository = repository;
        mSearchNameView = searchNameView;
    }

    @Inject
    void setupListeners() {
        mSearchNameView.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
