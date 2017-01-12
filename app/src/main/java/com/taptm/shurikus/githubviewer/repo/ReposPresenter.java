package com.taptm.shurikus.githubviewer.repo;


import android.webkit.URLUtil;

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
    public void openReposForStrUrl(String urlRepos) {
        if(!validateRepoUrl(urlRepos)){
            mRepoView.showMessage(R.string.msg_url_repos_no_validate);
            return;
        }

        mRepository.getRepos(urlRepos, new DataSource.LoadReposCallback() {

            @Override
            public void onReposLoaded(List<Repo> repos) {
                mRepoView.showRepos(repos);
            }

            @Override
            public void onDataNotAvailable() {
                mRepoView.showMessage(R.string.msg_no_data_from_server);
            }
        });
    }

    @Override
    public void openRepoCklicked(Repo repo) {
        String urlRepo = repo.getHtml_url();
        if(validateRepoUrl(urlRepo)){
            mRepoView.openRepoUrl(repo);
        }else {
            mRepoView.showMessage(R.string.msg_url_repos_no_validate);
        }
    }

    private boolean validateRepoUrl(String urlRepos){
        if(urlRepos != null && !urlRepos.equals("")
                && URLUtil.isValidUrl(urlRepos)){
            return true;
        }else {
            return false;
        }
    }

}
