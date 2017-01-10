package com.taptm.shurikus.githubviewer.data.source;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository implements DataSource {

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    @Inject
    Repository(@Remote DataSource tasksRemoteDataSource,
                    @Local DataSource tasksLocalDataSource) {
        mRemoteDataSource = tasksRemoteDataSource;
        mLocalDataSource = tasksLocalDataSource;
    }

    @Override
    public void searchUsers(String strSearch, @NonNull LoadUsersCallback callback) {
        mRemoteDataSource.searchUsers(strSearch, callback);
    }
}
