package com.taptm.shurikus.githubviewer.repo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taptm.shurikus.githubviewer.R;

public class ReposFragment extends Fragment implements ReposContract.View {

    private ReposContract.Presenter mPresenter;

    private static String mUrlRepos;

    public static ReposFragment newInstance(String urlRepos) {
        mUrlRepos = urlRepos;
        return new ReposFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repos, container, false);
        return root;
    }

    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
