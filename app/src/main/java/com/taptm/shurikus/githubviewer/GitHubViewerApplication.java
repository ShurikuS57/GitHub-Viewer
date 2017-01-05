package com.taptm.shurikus.githubviewer;


import android.app.Application;

import com.taptm.shurikus.githubviewer.data.source.DaggerRepositoryComponent;
import com.taptm.shurikus.githubviewer.data.source.RepositoryComponent;

public class GitHubViewerApplication extends Application {

    private RepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext())))
                .build();

    }

    public RepositoryComponent getRepositoryComponent() {
        return mRepositoryComponent;
    }

}
