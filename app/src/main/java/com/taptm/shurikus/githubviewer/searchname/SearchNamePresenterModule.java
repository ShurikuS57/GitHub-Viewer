package com.taptm.shurikus.githubviewer.searchname;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchNamePresenterModule {

    private final SearchNameContract.View mView;

    public SearchNamePresenterModule(SearchNameContract.View view) {
        mView = view;
    }

    @Provides
    SearchNameContract.View provideSearchNameContractView() {
        return mView;
    }

}
