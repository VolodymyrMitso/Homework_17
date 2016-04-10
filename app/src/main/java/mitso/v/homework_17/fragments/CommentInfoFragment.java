package mitso.v.homework_17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Comment;

public class CommentInfoFragment extends BaseFragment {

    private TextView mTextView_CommentInfo;

    private Button mButton_Comment;

    Comment comment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.comment_info_fragment, container, false);

        comment = (Comment) getArguments().getSerializable("comment");

        mTextView_CommentInfo = (TextView) rootView.findViewById(R.id.tv_CommentInfo_CIF);
        mTextView_CommentInfo.setText(comment.toString());

        return rootView;
    }
}
