package mitso.v.homework_17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Comment;
import mitso.v.homework_17.fragments.utils.Constants;

public class CommentInfoFragment extends BaseFragment {

    private TextView    mTextView_CommentInfo;

    private Comment     mComment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.comment_info_fragment, container, false);

        try {
            mComment = (Comment) getArguments().getSerializable(Constants.COMMENT_BUNDLE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mMainActivity, getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
        }

        if (mComment != null) {

            mTextView_CommentInfo = (TextView) rootView.findViewById(R.id.tv_CommentInfo_CIF);
            mTextView_CommentInfo.setText(mComment.toString());
        }

        return rootView;
    }
}
