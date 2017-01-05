package com.taptm.shurikus.githubviewer.data.source;

import com.taptm.shurikus.githubviewer.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, ApplicationModule.class})
public interface RepositoryComponent {

    Repository getRepository();

}
