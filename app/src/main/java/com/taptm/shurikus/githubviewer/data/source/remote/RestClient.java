package com.taptm.shurikus.githubviewer.data.source.remote;

import com.taptm.shurikus.githubviewer.data.GitUserList;
import com.taptm.shurikus.githubviewer.data.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RestClient {

    public static final String url = "https://api.github.com";

    public static GitHubAPI mGitHubAPI;

    public interface GitHubAPI{

        @GET("/search/users")
        Call<GitUserList> searchUsers(@Query("q") String strSearch);

        @GET("/users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);

    }


    static {
        setupRestClient();
    }

    private static void setupRestClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGitHubAPI = retrofit.create(GitHubAPI.class);
    }

    public Call<GitUserList> searchUsers(String strSearch){
        return mGitHubAPI.searchUsers(strSearch);
    }

    public Call<List<Repo>> listRepos(String user){
        return mGitHubAPI.listRepos(user);
    }

}
