package com.taptm.shurikus.githubviewer.repo;

import android.support.annotation.StringRes;

import com.taptm.shurikus.githubviewer.BasePresenter;
import com.taptm.shurikus.githubviewer.BaseView;
import com.taptm.shurikus.githubviewer.data.Repo;

import java.util.List;

public interface ReposContract {

    interface View extends BaseView<ReposContract.Presenter> {

        void showRepos(List<Repo> repos);

        void openRepoUrl(Repo repo);

        void showMessage(@StringRes int resourceId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void openReposForStrUrl(String urlRepos);

        void openRepoCklicked(Repo repo);

    }
}
