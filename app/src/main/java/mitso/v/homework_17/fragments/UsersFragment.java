package mitso.v.homework_17.fragments;

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
import mitso.v.homework_17.fragments.interfaces.IUserHandler;
import mitso.v.homework_17.fragments.users_fragment.SpacingDecoration;
import mitso.v.homework_17.fragments.users_fragment.UserAdapter;

public class UsersFragment extends BaseFragment implements IUserHandler {

    private static final String LOG_TAG = "UsersFragment";

    private RecyclerView        mRecyclerView_Users;
    private UserAdapter         mUserAdapter;
    private ArrayList<User>     mUsersList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.users_fragment, container, false);

        Api.getUsers(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                Log.d(LOG_TAG, "onSuccess");

                UserListResponse userListResponse = (UserListResponse) object;
                ArrayList<User> userArrayList = userListResponse.getUsers();
                mUsersList = userArrayList;

                Log.e(LOG_TAG, "userArrayList.size: " + userArrayList.size());
                for (int i = 0; i < userArrayList.size(); i++)
                    Log.e(LOG_TAG, userArrayList.get(i).toString());

                mRecyclerView_Users = (RecyclerView) rootView.findViewById(R.id.rv_Users_UF);
                mUserAdapter = new UserAdapter(mUsersList);
                mRecyclerView_Users.setAdapter(mUserAdapter);
                mRecyclerView_Users.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                mRecyclerView_Users.addItemDecoration(new SpacingDecoration(spacingInPixels));

                mUserAdapter.setIUserHandler(UsersFragment.this);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Log.d(LOG_TAG, "onFailure=" + errorMessage);
            }
        });

        return rootView;
    }

    @Override
    public void userOnClick(int position) {

        Toast.makeText(mMainActivity, mUsersList.get(position).toString(), Toast.LENGTH_SHORT).show();

        InfoFragment infoFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("info", mUsersList.get(position).toString());
        bundle.putInt("id", mUsersList.get(position).getId());
        infoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_FragmentContainer_AM, infoFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();

    }
}