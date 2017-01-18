package com.taptm.shurikus.githubviewer.repo;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.Repo;

import java.util.ArrayList;
import java.util.List;

import static dagger.internal.Preconditions.checkNotNull;

public class ReposFragment extends Fragment implements ReposContract.View {

    private ReposContract.Presenter mPresenter;

    private static String mUserName;

    private RepoAdapter mRepoAdapter;

    private ProgressBar mProgressBar;

    public static ReposFragment newInstance(String userName) {
        mUserName = userName;
        return new ReposFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepoAdapter = new RepoAdapter(new ArrayList<Repo>(0), mRepoItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repos, container, false);
        ListView listView = (ListView) root.findViewById(R.id.list_repos);
        listView.setAdapter(mRepoAdapter);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.openReposFromUserName(mUserName);
    }

    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showRepos(List<Repo> repos) {
        mRepoAdapter.replaceData(repos);
    }

    @Override
    public void openRepoUrl(Repo repo) {
        String url = repo.getHtml_url();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void showMessage(@StringRes int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    RepoItemListener mRepoItemListener = new RepoItemListener() {
        @Override
        public void onRepoClick(Repo clickedRepo) {
            mPresenter.openRepoClicked(clickedRepo);
        }
    };

    private static class RepoAdapter extends BaseAdapter{

        private List<Repo> mRepos;

        private RepoItemListener mRepoItemListener;

        public RepoAdapter(List<Repo> mRepos, RepoItemListener listener) {
            this.mRepos = mRepos;
            this.mRepoItemListener = listener;
        }

        public void replaceData(List<Repo> repos) {
            setList(repos);
            notifyDataSetChanged();
        }

        private void setList(List<Repo> tasks) {
            mRepos = checkNotNull(tasks);
        }

        @Override
        public int getCount() {
            return mRepos.size();
        }

        @Override
        public Repo getItem(int position) {
            return mRepos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View rowView = view;
            if(rowView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.item_repos, parent, false);
            }

            final Repo repo = getItem(position);

            TextView textNameRepo = (TextView) rowView.findViewById(R.id.text_repo_name);
            textNameRepo.setText(repo.getName());

            TextView textDescriptionRepo = (TextView) rowView.findViewById(R.id.text_repo_description);
            textDescriptionRepo.setText(repo.getDescription());

            TextView textContStars = (TextView) rowView.findViewById(R.id.text_count_starts);
            textContStars.setText(String.valueOf(repo.getStargazers_count()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRepoItemListener.onRepoClick(repo);
                }
            });

            return rowView;
        }
    }

    public interface RepoItemListener {

        void onRepoClick(Repo clickedRepo);

    }
}
