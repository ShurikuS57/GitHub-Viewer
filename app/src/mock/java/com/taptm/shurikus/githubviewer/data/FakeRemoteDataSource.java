package com.taptm.shurikus.githubviewer.data;


import android.support.annotation.NonNull;

import com.taptm.shurikus.githubviewer.data.source.DataSource;
import com.taptm.shurikus.githubviewer.data.source.remote.FakeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeRemoteDataSource implements DataSource {

    private List<User> users = new ArrayList<>();

    public FakeRemoteDataSource() {
        users = FakeData.getFakeUsers();
    }

    @Override
    public void searchUsers(String strSearch, @NonNull LoadUsersCallback callback) {
        List<User> findUser = new ArrayList<>();
        for (User user: users){
            if(user.getLogin().contains(strSearch)){
                findUser.add(user);
            }
        }

        if(findUser.size()>0){
            callback.onUsersLoaded(findUser);
        }else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getRepos(@NonNull String userName, @NonNull LoadReposCallback callback) {
        HashMap<String, List<Repo>> usersRepos = FakeData.getFakeRepos();
        List<Repo> repoList = usersRepos.get(userName);
        if(repoList != null){
            callback.onReposLoaded(repoList);
        }else {
            callback.onDataNotAvailable();
        }
    }

}
