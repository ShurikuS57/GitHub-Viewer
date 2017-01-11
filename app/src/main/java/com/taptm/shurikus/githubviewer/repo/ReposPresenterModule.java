package com.taptm.shurikus.githubviewer.repo;

import dagger.Module;
import dagger.Provides;

@Module
public class ReposPresenterModule {

    private final ReposContract.View mView;

    private final String mReposUrl;

    public ReposPresenterModule(ReposContract.View mView, String reposUrl) {
        this.mView = mView;
        this.mReposUrl = reposUrl;
    }

    @Provides
    ReposContract.View provideRepoContractView(){
        return mView;
    }

    @Provides
    String provideReposUrl(){
        return mReposUrl;
    }
}
