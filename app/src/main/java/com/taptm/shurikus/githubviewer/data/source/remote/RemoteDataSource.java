package com.taptm.shurikus.githubviewer.data.source.remote;


import android.support.annotation.NonNull;

import com.taptm.shurikus.githubviewer.data.GitUserList;
import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.source.DataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {
    @Override
    public void searchUsers(String strSearch, @NonNull final LoadUsersCallback callback) {
        RestClient restClient = new RestClient();

        final Call<GitUserList> call = restClient.searchUsers(strSearch);
        call.enqueue(new Callback<GitUserList>() {
            @Override
            public void onResponse(Call<GitUserList> call, Response<GitUserList> response) {
                GitUserList gitUserList = response.body();
                if(gitUserList.getItems() == null && gitUserList.getItems().size() == 0){
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onUsersLoaded(gitUserList.getItems());
            }

            @Override
            public void onFailure(Call<GitUserList> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getRepos(@NonNull String user, @NonNull final LoadReposCallback callback) {
        RestClient restClient = new RestClient();

        final Call<List<Repo>> call = restClient.listRepos(user);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                callback.onReposLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });


    }
}
