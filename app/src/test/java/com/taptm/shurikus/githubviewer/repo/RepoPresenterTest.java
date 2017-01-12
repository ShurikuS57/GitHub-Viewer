package com.taptm.shurikus.githubviewer.repo;


import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.FakeData;
import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepoPresenterTest {

    @Mock
    private Repository mRepository;

    @Mock
    private ReposContract.View mView;

    @Captor
    private ArgumentCaptor<DataSource.LoadReposCallback> mLoadReposCallbackCaptor;

    private List<Repo> mRepos;

    private ReposPresenter mRepoPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mRepoPresenter = new ReposPresenter(mRepository,mView);

        when(mView.isActive()).thenReturn(true);

        mRepos = FakeData.getFakeRepo();
    }

    @Test
    public void openEmptyRepos_CallViewToDisplay(){
        String emptyUrlRepos = "";

        mRepoPresenter.openReposForStrUrl(emptyUrlRepos);
        verify(mView).showMessage(R.string.msg_url_repos_no_validate);
    }


}
