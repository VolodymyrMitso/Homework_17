package mitso.v.homework_17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.users_fragment.UsersFragment;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        updateFragment(new UsersFragment());

//        Api.getAlbums(new ConnectCallback() {
//            @Override
//            public void onSuccess(Object object) {
//                UserListResponse userListResponse = (UserListResponse) object;
//                ArrayList<User> userArrayList = userListResponse.getAlbums();
//
//                Log.e(LOG_TAG, "userArrayList.size: " + userArrayList.size());
//
//                for (int i = 0; i < userArrayList.size(); i++)
//                    Log.e(LOG_TAG, userArrayList.get(i).toString());
//            }
//
//            @Override
//            public void onFailure(Throwable throwable, String errorMessage) {
//                Log.d(LOG_TAG, "onFailure=" + errorMessage);
//            }
//        });
//
//        Api.getUser(7, new ConnectCallback() {
//            @Override
//            public void onSuccess(Object object) {
//                User user = (User) object;
//                Log.e(LOG_TAG, user.toString());
//            }
//
//            @Override
//            public void onFailure(Throwable throwable, String errorMessage) {
//
//            }
//        });
    }

    private void updateFragment(BaseFragment baseFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, baseFragment)
                .commitAllowingStateLoss();
    }
}