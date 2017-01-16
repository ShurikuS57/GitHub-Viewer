package com.taptm.shurikus.githubviewer.searchname;


import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.source.remote.FakeData;
import com.taptm.shurikus.githubviewer.data.User;
import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchNamePresenterTest {

    private List<User> users;

    @Mock
    private Repository mRepository;

    @Mock
    private SearchNameContract.View mView;

    @Captor
    private ArgumentCaptor<DataSource.LoadUsersCallback> mLoadUsersCallbackCaptor;

    private SearchNamePresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new SearchNamePresenter(mRepository, mView);

        when(mView.isActive()).thenReturn(true);

        users = FakeData.getFakeUsers();

    }

    @Test
    public void searchEmptyString_CallViewToDisplay() throws Exception {
        users.clear();

        mPresenter.searchUser("");
        verify(mView).showMessage(R.string.msg_search_not_be_empty);
    }

    @Test
    public void searchNoEmptyStringFromRepository_CallViewToDisplay(){
        mPresenter.searchUser("s");

        verify(mRepository).searchUsers(eq("s"), mLoadUsersCallbackCaptor.capture());
        mLoadUsersCallbackCaptor.getValue().onUsersLoaded(users);

        verify(mView).showUsers(users);
    }

    @Test
    public void openEmptyReposUrl_CallViewToDisplay(){
        User user = users.get(0);
        user.setLogin(null);

        mPresenter.openRepos(user);
        verify(mView).showMessage(R.string.msg_no_reference_repository);

        user.setRepos_url("");
        verify(mView).showMessage(R.string.msg_no_reference_repository);
    }

    @Test
    public void openNoEmptyReposUrl_CallViewToDisplay(){
        User user = users.get(0);

        mPresenter.openRepos(user);
        verify(mView).openRepoActivity(user.getLogin());
    }

}
