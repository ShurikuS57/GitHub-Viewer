package com.taptm.shurikus.githubviewer.data;


import android.support.annotation.NonNull;

import com.taptm.shurikus.githubviewer.data.source.DataSource;

import java.util.ArrayList;
import java.util.List;

public class FakeRemoteDataSource implements DataSource {

    private List<User> users = new ArrayList<>();

    public FakeRemoteDataSource() {
        users = getFakeUsers();
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

    public static List<User> getFakeUsers(){
        List<User> users = new ArrayList<>();

        users.add(new User(22312214, "shurik236", "https://avatars.githubusercontent.com/u/22312214?v=3",
                "https://api.github.com/users/shurik236", "https://github.com/shurik236",
                "https://api.github.com/users/shurik236/repos"));

        users.add(new User(1052889, "shurik4545", "https://avatars.githubusercontent.com/u/1052889?v=3",
                "https://api.github.com/users/shurik4545", "https://github.com/shurik4545",
                "https://api.github.com/users/shurik4545/repos"));

        users.add(new User(3917774, "shumaojie", "https://avatars.githubusercontent.com/u/3917774?v=3",
                "https://api.github.com/users/shumaojie", "https://github.com/shumaojie",
                "https://api.github.com/users/shumaojie/repos"));

        return users;
    }
}
