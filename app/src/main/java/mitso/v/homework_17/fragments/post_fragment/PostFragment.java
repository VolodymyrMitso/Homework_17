package mitso.v.homework_17.fragments.post_fragment;

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
import mitso.v.homework_17.api.models.Post;
import mitso.v.homework_17.api.response.PostListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.PostInfoFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class PostFragment extends BaseFragment implements IPostHandler {

    private static final String LOG_TAG = "AlbumFragment";

    private RecyclerView        mRecyclerView_Post;
    private PostAdapter         mPostAdapter;
    private ArrayList<Post>     mPostList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.post_fragment, container, false);

        int id =  getArguments().getInt("id");

        Log.e(LOG_TAG, "id: " + String.valueOf(id));

        if (CheckConnection.checkConnection(mMainActivity)) {

        Api.getPostsByUser(id, new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                Log.d(LOG_TAG, "onSuccess");

                PostListResponse postListResponse = (PostListResponse) object;
                ArrayList<Post> postArrayList = postListResponse.getPosts();
                mPostList = postArrayList;

                Log.e(LOG_TAG, "postArrayList.size:" + postArrayList.size());

                Log.e(LOG_TAG, "postArrayList.first:" + postArrayList.get(0));
                Log.e(LOG_TAG, "postArrayList.last:" + postArrayList.get(9));


                mRecyclerView_Post = (RecyclerView) rootView.findViewById(R.id.rv_Posts_PF);
                mPostAdapter = new PostAdapter(mPostList);
                mRecyclerView_Post.setAdapter(mPostAdapter);
                mRecyclerView_Post.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                mRecyclerView_Post.addItemDecoration(new SpacingDecoration(spacingInPixels));

                mPostAdapter.setPostHandler(PostFragment.this);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Log.d(LOG_TAG, "onFailure=" + errorMessage);
            }
        });

        } else
            Toast.makeText(mMainActivity, getResources().getString(R.string.no_connection), Toast.LENGTH_SHORT).show();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mPostAdapter.checkPostHandler())
            mPostAdapter.releasePostHandler();
    }

    @Override
    public void postOnClick(Post post) {

        PostInfoFragment postInfoFragment = new PostInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        postInfoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, postInfoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}