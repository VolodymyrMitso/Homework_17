package mitso.v.homework_17.fragments.comment_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Comment;
import mitso.v.homework_17.api.response.CommentListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.CommentInfoFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class CommentFragment extends BaseFragment implements ICommentHandler {

    private final String        LOG_TAG = Constants.COMMENT_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Comment;
    private CommentAdapter      mCommentAdapter;
    private ArrayList<Comment>  mCommentList;

    private boolean             isHandlerSet;

    private Integer             postId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.comment_fragment, container, false);

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(mMainActivity.getResources().getString(R.string.s_comment_list));

        isHandlerSet = false;

        try {
            postId = getArguments().getInt(Constants.POST_ID_BUNDLE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
        }

        if (postId != null && postId != 0) {

            if (CheckConnection.checkConnection(mMainActivity)) {

                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_connecting), Toast.LENGTH_SHORT).show();

                Api.getCommentsByPost(postId, new ConnectCallback() {
                    @Override
                    public void onSuccess(Object object) {

                        CommentListResponse commentListResponse = (CommentListResponse) object;
                        ArrayList<Comment> commentArrayList = commentListResponse.getComments();
                        mCommentList = commentArrayList;

                        Log.e(LOG_TAG, String.valueOf(commentArrayList.size()));
                        Log.e(LOG_TAG, commentArrayList.get(0).toString());
                        Log.e(LOG_TAG, commentArrayList.get(commentArrayList.size() - 1).toString());


                        mRecyclerView_Comment = (RecyclerView) rootView.findViewById(R.id.rv_Comments_CF);
                        mCommentAdapter = new CommentAdapter(mCommentList);
                        mRecyclerView_Comment.setAdapter(mCommentAdapter);
                        mRecyclerView_Comment.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                        int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                        mRecyclerView_Comment.addItemDecoration(new SpacingDecoration(spacingInPixels));

                        mCommentAdapter.setCommentHandler(CommentFragment.this);
                        isHandlerSet = true;

                        Log.e(LOG_TAG, "onSuccess");
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        StringWriter errors = new StringWriter();
                        throwable.printStackTrace(new PrintWriter(errors));
                        Log.e(LOG_TAG, "onFailure");
                        Log.e(LOG_TAG, errors.toString());
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_failure), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
                    }
                });

            } else
                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_no_connection), Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (isHandlerSet)
            mCommentAdapter.releaseCommentHandler();
    }

    @Override
    public void commentOnClick(Comment _comment) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.COMMENT_BUNDLE_KEY, _comment);
        CommentInfoFragment commentInfoFragment = new CommentInfoFragment();
        commentInfoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, commentInfoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}