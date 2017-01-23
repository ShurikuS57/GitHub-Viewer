package com.taptm.shurikus.githubviewer.repo;


import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.User;
import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.Repository;
import com.taptm.shurikus.githubviewer.data.source.remote.FakeData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Matchers.eq;
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

        HashMap<String, List<Repo>> reposMap = FakeData.getFakeRepos();

        mRepos = reposMap.get("shurik236");
    }

    @Test
    public void openEmptyRepos_CallViewToDisplay(){
        String emptyUrlRepos = "";

        mRepoPresenter.openReposFromUserName(emptyUrlRepos);
        verify(mView).showMessage(R.string.msg_url_repos_no_validate);
    }

    @Test
    public void openValidateUserRepos(){
        User user = FakeData.getFakeUsers().get(0);
        String login = user.getLogin();

        mRepoPresenter.openReposFromUserName(login);
        verify(mRepository).getRepos(eq(login), mLoadReposCallbackCaptor.capture());
        mLoadReposCallbackCaptor.getValue().onReposLoaded(FakeData.getFakeRepos().get("shurik236"));

    }

    @Test
    public void openInvalidateUserRepos(){
        String loseLogin = "fake_login";

        mRepoPresenter.openReposFromUserName(loseLogin);
        verify(mRepository).getRepos(eq(loseLogin), mLoadReposCallbackCaptor.capture());
        mLoadReposCallbackCaptor.getValue().onDataNotAvailable();
    }

    @Test
    public void openValidateRepos(){
        Repo repo = FakeData.getFakeRepos().get("shurik236").get(0);
        String repoUrl = repo.getHtml_url();

        mRepoPresenter.openRepoClicked(repo);
        verify(mView).openRepoUrl(repo);
    }

    @Test
    public void openInvalidateRepos(){
        Repo repo = FakeData.getFakeRepos().get("shurik236").get(0);

        repo.setHtml_url("");
        mRepoPresenter.openRepoClicked(repo);
        verify(mView).showMessage(R.string.msg_url_repos_no_validate);
    }

}
