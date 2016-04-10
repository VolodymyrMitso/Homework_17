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

public class InfoFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTextView_UserInfo;
    private Button mButton_ToDo;
    private Button mButton_Album;

//    private int id;

    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.info_fragment, container, false);

//        String string =  getArguments().getString("info");
//        int id =  getArguments().getInt("id");
//        this.id = id;
        user = (User) getArguments().getSerializable("user");

        mTextView_UserInfo = (TextView) rootView.findViewById(R.id.tv_UserInfo_IF);
        mTextView_UserInfo.setText(user.toString());

        Toast.makeText(mMainActivity, String.valueOf(user.getId()), Toast.LENGTH_SHORT).show();

        mButton_ToDo = (Button) rootView.findViewById(R.id.btn_Todo_IF);
        mButton_ToDo.setOnClickListener(this);

        mButton_Album = (Button) rootView.findViewById(R.id.btn_Album_IF);
        mButton_Album.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", user.getId());

        switch (v.getId()) {
            case R.id.btn_Todo_IF:

                TodoFragment todoFragment = new TodoFragment();
                todoFragment.setArguments(bundle);
                updateFragment(todoFragment);

                break;
            case R.id.btn_Album_IF:

                AlbumFragment albumFragment = new AlbumFragment();
                albumFragment.setArguments(bundle);
                updateFragment(albumFragment);

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
