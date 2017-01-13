package com.taptm.shurikus.githubviewer.data.source.local;


import android.content.Context;
import android.support.annotation.NonNull;

import com.taptm.shurikus.githubviewer.data.source.DataSource;

import static dagger.internal.Preconditions.checkNotNull;

public class LocalDataSource implements DataSource {

    private DbHelper mDbHelper;

    public LocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new DbHelper(context);
    }

    @Override
    public void searchUsers(String strSearch, @NonNull LoadUsersCallback callback) {

    }

    @Override
    public void getRepos(@NonNull String userName, @NonNull LoadReposCallback callback) {

    }
}
