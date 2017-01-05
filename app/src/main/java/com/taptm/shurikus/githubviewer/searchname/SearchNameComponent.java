package com.taptm.shurikus.githubviewer.searchname;

import com.taptm.shurikus.githubviewer.data.source.RepositoryComponent;
import com.taptm.shurikus.githubviewer.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = SearchNamePresenterModule.class)
public interface SearchNameComponent {

    void inject(SearchNameActivity searchNameActivity);

}
