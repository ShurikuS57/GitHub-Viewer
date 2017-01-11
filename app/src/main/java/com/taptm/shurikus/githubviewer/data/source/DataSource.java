package com.taptm.shurikus.githubviewer.data.source;


import android.support.annotation.NonNull;

import com.taptm.shurikus.githubviewer.data.User;

import java.util.List;

public interface DataSource {

    interface LoadUsersCallback {

        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    void searchUsers(@NonNull final String strSearch, @NonNull final LoadUsersCallback callback);


}
