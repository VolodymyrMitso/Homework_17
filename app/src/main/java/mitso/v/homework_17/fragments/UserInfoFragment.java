package mitso.v.homework_17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.user.User;
import mitso.v.homework_17.fragments.album_fragment.AlbumFragment;
import mitso.v.homework_17.fragments.post_fragment.PostFragment;
import mitso.v.homework_17.fragments.todo_fragment.TodoFragment;

public class UserInfoFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTextView_UserInfo;

    private Button mButton_ToDo;
    private Button mButton_Album;
    private Button mButton_Post;

//    private int id;

    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.user_info_fragment, container, false);

//        String string =  getArguments().getString("info");
//        int id =  getArguments().getInt("id");
//        this.id = id;
        user = (User) getArguments().getSerializable("user");

        mTextView_UserInfo = (TextView) rootView.findViewById(R.id.tv_UserInfo_UIF);
        mTextView_UserInfo.setText(user.toString());

        Toast.makeText(mMainActivity, String.valueOf(user.getId()), Toast.LENGTH_SHORT).show();

        mButton_ToDo = (Button) rootView.findViewById(R.id.btn_Todo_UIF);
        mButton_ToDo.setOnClickListener(this);

        mButton_Album = (Button) rootView.findViewById(R.id.btn_Album_UIF);
        mButton_Album.setOnClickListener(this);

        mButton_Post = (Button) rootView.findViewById(R.id.btn_Post_UIF);
        mButton_Post.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", user.getId());

        switch (v.getId()) {
            case R.id.btn_Todo_UIF:

                TodoFragment todoFragment = new TodoFragment();
                todoFragment.setArguments(bundle);
                updateFragment(todoFragment);

                break;
            case R.id.btn_Album_UIF:

                AlbumFragment albumFragment = new AlbumFragment();
                albumFragment.setArguments(bundle);
                updateFragment(albumFragment);

                break;
            case R.id.btn_Post_UIF:

                PostFragment postFragment = new PostFragment();
                postFragment.setArguments(bundle);
                updateFragment(postFragment);

                Toast.makeText(mMainActivity, "aaa", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void updateFragment(BaseFragment baseFragment) {
        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, baseFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
