package com.taptm.shurikus.githubviewer.searchname;


import android.content.Intent;

import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.User;
import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.Repository;

import java.util.List;

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

    @Override
    public void searchUser(String strSearch) {
        if(strSearch.equals("")){
            mSearchNameView.showMessage(R.string.msg_search_not_be_empty);
            return;
        }
        mRepository.searchUsers(strSearch, new DataSource.LoadUsersCallback() {

            @Override
            public void onUsersLoaded(List<User> users) {
                mSearchNameView.showUsers(users);
            }

            @Override
            public void onDataNotAvailable() {
                mSearchNameView.showMessage(R.string.msg_nothing_found);
                mSearchNameView.clearAdapter();
            }
        });
    }

    @Override
    public void openRepos(User user) {
        String repoUrl = user.getRepos_url();
        if(repoUrl != null && !repoUrl.equals("")){
            mSearchNameView.openRepoActivity(repoUrl);
        }else {
            mSearchNameView.showMessage(R.string.msg_no_reference_repository);
        }
    }
}
