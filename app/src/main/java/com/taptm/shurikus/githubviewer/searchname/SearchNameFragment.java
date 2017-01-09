package com.taptm.shurikus.githubviewer.searchname;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taptm.shurikus.githubviewer.R;

import static dagger.internal.Preconditions.checkNotNull;

public class SearchNameFragment extends Fragment implements SearchNameContract.View {

    private SearchNameContract.Presenter mPresenter;

    public static SearchNameFragment newInstance() {
        return new SearchNameFragment();
    }

    @Override
    public void setPresenter(@NonNull SearchNameContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_name, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
