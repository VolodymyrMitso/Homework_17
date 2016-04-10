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

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Comment;
import mitso.v.homework_17.api.response.CommentListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.CommentInfoFragment;
import mitso.v.homework_17.fragments.album_fragment.SpacingDecoration;

public class CommentFragment extends BaseFragment implements ICommentHandler {

    private static final String LOG_TAG = "AlbumFragment";

    private RecyclerView            mRecyclerView_Comment;
    private CommentAdapter          mCommentAdapter;
    private ArrayList<Comment>      mCommentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.comment_fragment, container, false);

        int id =  getArguments().getInt("post id");

        Api.getCommentsByPost(id, new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                Log.d(LOG_TAG, "onSuccess");

                CommentListResponse commentListResponse = (CommentListResponse) object;
                ArrayList<Comment> commentArrayList = commentListResponse.getComments();
                mCommentList = commentArrayList;

                Log.e(LOG_TAG, "commentArrayList.size:" + commentArrayList.size());

                Log.e(LOG_TAG, "commentArrayList.first:" + commentArrayList.get(0));
                Log.e(LOG_TAG, "commentArrayList.last:" + commentArrayList.get(4));


                mRecyclerView_Comment = (RecyclerView) rootView.findViewById(R.id.rv_Comments_CF);
                mCommentAdapter = new CommentAdapter(mCommentList);
                mRecyclerView_Comment.setAdapter(mCommentAdapter);
                mRecyclerView_Comment.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                mRecyclerView_Comment.addItemDecoration(new SpacingDecoration(spacingInPixels));

                mCommentAdapter.setAlbumHandler(CommentFragment.this);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Log.d(LOG_TAG, "onFailure=" + errorMessage);
            }
        });

        return rootView;
    }

    @Override
    public void commentOnClick(Comment comment) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("comment", comment);

        Toast.makeText(mMainActivity, comment.toString(), Toast.LENGTH_SHORT).show();
        CommentInfoFragment commentInfoFragment = new CommentInfoFragment();
        commentInfoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, commentInfoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}