package com.taptm.shurikus.githubviewer.repo;


import com.taptm.shurikus.githubviewer.data.source.Repository;

import javax.inject.Inject;

public class ReposPresenter implements ReposContract.Presenter {

    private final Repository mRepository;

    private final ReposContract.View mRepoView;

    @Inject
    public ReposPresenter(Repository repository,
                          ReposContract.View repoView) {
        mRepository = repository;
        mRepoView = repoView;
    }

    @Inject
    void setListeners(){
        mRepoView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
