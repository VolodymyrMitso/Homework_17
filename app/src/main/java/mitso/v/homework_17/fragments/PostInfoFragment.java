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
import mitso.v.homework_17.api.models.Post;
import mitso.v.homework_17.fragments.comment_fragment.CommentFragment;

public class PostInfoFragment extends BaseFragment {

    private TextView mTextView_PostInfo;

    private Button mButton_Comment;

    Post post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.post_info_fragment, container, false);

        post = (Post) getArguments().getSerializable("post");

        mTextView_PostInfo = (TextView) rootView.findViewById(R.id.tv_PostInfo_PIF);
        mTextView_PostInfo.setText(post.toString());

        Toast.makeText(mMainActivity, String.valueOf(post.getId()), Toast.LENGTH_SHORT).show();

        mButton_Comment = (Button) rootView.findViewById(R.id.btn_Comment_PIF);
        mButton_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("post id", post.getId());
                CommentFragment commentFragment = new CommentFragment();
                commentFragment.setArguments(bundle);

                mMainActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_FragmentContainer_AM, commentFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });

        return rootView;
    }
}
