package com.taptm.shurikus.githubviewer.repo;


import com.taptm.shurikus.githubviewer.data.source.RepositoryComponent;
import com.taptm.shurikus.githubviewer.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = ReposPresenterModule.class)
public interface ReposComponent {

    void inject(ReposActivity reposActivity);

}
