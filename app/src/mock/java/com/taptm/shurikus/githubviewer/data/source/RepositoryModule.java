package com.taptm.shurikus.githubviewer.data.source;

import android.content.Context;

import com.taptm.shurikus.githubviewer.data.FakeRemoteDataSource;
import com.taptm.shurikus.githubviewer.data.source.local.LocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    @Local
    DataSource provideLocalDataSource(Context context) {
        return new LocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    DataSource provideRemoteDataSource() {
        return new FakeRemoteDataSource();
    }

}
