package com.taptm.shurikus.githubviewer.repo;


import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.Repository;

import java.util.List;

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

    @Override
    public void openReposFromUserName(String userName) {
        mRepoView.setLoadingIndicator(true);
        if(!validateString(userName)){
            mRepoView.showMessage(R.string.msg_url_repos_no_validate);
            return;
        }

        mRepository.getRepos(userName, new DataSource.LoadReposCallback() {

            @Override
            public void onReposLoaded(List<Repo> repos) {
                mRepoView.showRepos(repos);
                mRepoView.setLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable() {
                mRepoView.setLoadingIndicator(false);
                mRepoView.showMessage(R.string.msg_no_data_from_server);
            }
        });
    }

    @Override
    public void openRepoClicked(Repo repo) {
        String urlRepo = repo.getHtml_url();
        if(validateString(urlRepo)){
            mRepoView.openRepoUrl(repo);
        }else {
            mRepoView.showMessage(R.string.msg_url_repos_no_validate);
        }
    }

    private boolean validateString(String urlRepos){
        if(urlRepos != null && !urlRepos.equals("")){
            return true;
        }else {
            return false;
        }
    }

}
