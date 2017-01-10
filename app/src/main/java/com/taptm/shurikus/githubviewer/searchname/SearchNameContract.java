package com.taptm.shurikus.githubviewer.searchname;


import android.support.annotation.StringRes;

import com.taptm.shurikus.githubviewer.BasePresenter;
import com.taptm.shurikus.githubviewer.BaseView;
import com.taptm.shurikus.githubviewer.data.User;

import java.util.List;

public interface SearchNameContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showUsers(List<User> users);

        void clearAdapter();

        void showMessage(@StringRes int resourceId);

    }


    interface Presenter extends BasePresenter {

        void searchUser(String strSearch);

        void openRepos(User user);

    }
}
