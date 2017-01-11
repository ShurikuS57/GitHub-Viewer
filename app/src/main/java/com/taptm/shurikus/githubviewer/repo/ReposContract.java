package com.taptm.shurikus.githubviewer.repo;

import com.taptm.shurikus.githubviewer.BasePresenter;
import com.taptm.shurikus.githubviewer.BaseView;

public interface ReposContract {

    interface View extends BaseView<ReposContract.Presenter> {


        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}
