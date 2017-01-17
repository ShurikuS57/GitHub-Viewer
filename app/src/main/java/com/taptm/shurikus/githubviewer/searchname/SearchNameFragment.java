package com.taptm.shurikus.githubviewer.searchname;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.taptm.shurikus.githubviewer.R;
import com.taptm.shurikus.githubviewer.data.User;
import com.taptm.shurikus.githubviewer.repo.ReposActivity;

import java.util.ArrayList;
import java.util.List;

import static dagger.internal.Preconditions.checkNotNull;

public class SearchNameFragment extends Fragment implements SearchNameContract.View {

    private SearchNameContract.Presenter mPresenter;

    private UsersAdapter mListAdapter;

    private EditText mEditTextUser;

    public static SearchNameFragment newInstance() {
        return new SearchNameFragment();
    }

    @Override
    public void setPresenter(@NonNull SearchNameContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new UsersAdapter(getActivity(), new ArrayList<User>(0),  mUserItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_name, container, false);

        mEditTextUser = (EditText) root.findViewById(R.id.editText_search_name);

        ListView listView = (ListView) root.findViewById(R.id.list_result_name);
        listView.setAdapter(mListAdapter);

        ImageButton searchButton = (ImageButton) root.findViewById(R.id.imageButton_search_name);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.searchUser(mEditTextUser.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showUsers(List<User> users) {
        mListAdapter.replaceData(users);
    }

    @Override
    public void clearAdapter() {
        mListAdapter.replaceData(new ArrayList<User>(0));
    }

    @Override
    public void openRepoActivity(String userName) {
        Intent intent = new Intent(getActivity(), ReposActivity.class);
        intent.putExtra(ReposActivity.EXTRA_USER_NAME, userName);
        getActivity().startActivity(intent);
    }

    @Override
    public void showMessage(@StringRes int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_LONG).show();
    }

    UserItemListener mUserItemListener = new UserItemListener() {
        @Override
        public void onUserClick(User clickedUser) {
            mPresenter.openRepos(clickedUser);
        }
    };

    private static class UsersAdapter extends BaseAdapter {

        private Context mContext;

        private List<User> mUsers;

        private UserItemListener mItemListener;

        public UsersAdapter(Context context, List<User> mUsers, UserItemListener mItemListener) {
            this.mContext = context;
            this.mUsers = mUsers;
            this.mItemListener = mItemListener;
        }

        public void replaceData(List<User> users){
            setList(users);
            notifyDataSetChanged();
        }

        public void setList(List<User> users){
            mUsers = checkNotNull(users);
        }

        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public User getItem(int position) {
            return mUsers.get(position);
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
                rowView = inflater.inflate(R.layout.item_user, parent, false);
            }

            final User user = getItem(position);

            TextView textLogin = (TextView) rowView.findViewById(R.id.text_item_login);
            textLogin.setText(user.getLogin());

            ImageView imageUser  = (ImageView) rowView.findViewById(R.id.image_item_user);
            Picasso.with(mContext).load(user.getAvatar_url()).into(imageUser);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onUserClick(user);
                }
            });

            return rowView;
        }
    }

    public interface UserItemListener {

        void onUserClick(User clickedUser);

    }

}
