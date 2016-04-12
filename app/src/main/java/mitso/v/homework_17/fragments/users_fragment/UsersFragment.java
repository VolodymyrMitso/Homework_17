package mitso.v.homework_17.fragments.users_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.user.User;
import mitso.v.homework_17.api.response.UserListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.UserInfoFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class UsersFragment extends BaseFragment implements IUserHandler {

    private final String        LOG_TAG = Constants.USER_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Users;
    private UserAdapter         mUserAdapter;
    private ArrayList<User>     mUsersList;

    private boolean             isHandlerSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.users_fragment, container, false);

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(mMainActivity.getResources().getString(R.string.s_user_list));

        isHandlerSet = false;

        if (CheckConnection.checkConnection(mMainActivity)) {

            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_connecting), Toast.LENGTH_SHORT).show();

            Api.getUsers(new ConnectCallback() {
                @Override
                public void onSuccess(Object object) {

                    UserListResponse userListResponse = (UserListResponse) object;
                    ArrayList<User> userArrayList = userListResponse.getUsers();
                    mUsersList = userArrayList;

                    Log.e(LOG_TAG, String.valueOf(userArrayList.size()));
                    Log.e(LOG_TAG, userArrayList.get(0).toString());
                    Log.e(LOG_TAG, userArrayList.get(userArrayList.size() - 1).toString());

                    mRecyclerView_Users = (RecyclerView) rootView.findViewById(R.id.rv_Users_UF);
                    mUserAdapter = new UserAdapter(mUsersList);
                    mRecyclerView_Users.setAdapter(mUserAdapter);
                    mRecyclerView_Users.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                    int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                    mRecyclerView_Users.addItemDecoration(new SpacingDecoration(spacingInPixels));

                    mUserAdapter.setUserHandler(UsersFragment.this);
                    isHandlerSet = true;

                    Log.d(LOG_TAG, "onSuccess");
                    Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_success), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.d(LOG_TAG, "onFailure");
                    Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_failure), Toast.LENGTH_SHORT).show();
                    Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
                }
            });

        } else
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_no_connection), Toast.LENGTH_SHORT).show();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (isHandlerSet)
            mUserAdapter.releaseUserHandler();
    }

    @Override
    public void userOnClick(User _user) {

        UserInfoFragment userInfoFragment = new UserInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.USER_BUNDLE_KEY, _user);
        userInfoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_FragmentContainer_AM, userInfoFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
    }
}