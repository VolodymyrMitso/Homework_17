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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Post;
import mitso.v.homework_17.api.response.PostListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.PostInfoFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class PostFragment extends BaseFragment implements IPostHandler {

    private final String        LOG_TAG = Constants.POST_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Post;
    private PostAdapter         mPostAdapter;
    private ArrayList<Post>     mPostList;

    private boolean             isHandlerSet;

    private Integer             userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.post_fragment, container, false);

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(mMainActivity.getResources().getString(R.string.s_post_list));

        isHandlerSet = false;

        try {
            userId = getArguments().getInt(Constants.USER_ID_BUNDLE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
        }

        if (userId != null && userId != 0) {

            if (CheckConnection.checkConnection(mMainActivity)) {

                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_connecting), Toast.LENGTH_SHORT).show();

                Api.getPostsByUser(userId, new ConnectCallback() {
                    @Override
                    public void onSuccess(Object object) {

                        PostListResponse postListResponse = (PostListResponse) object;
                        ArrayList<Post> postArrayList = postListResponse.getPosts();
                        mPostList = postArrayList;

                        Log.e(LOG_TAG, String.valueOf(postArrayList.size()));
                        Log.e(LOG_TAG, postArrayList.get(0).toString());
                        Log.e(LOG_TAG, postArrayList.get(postArrayList.size() - 1).toString());

                        mRecyclerView_Post = (RecyclerView) rootView.findViewById(R.id.rv_Posts_PF);
                        mPostAdapter = new PostAdapter(mPostList);
                        mRecyclerView_Post.setAdapter(mPostAdapter);
                        mRecyclerView_Post.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                        int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                        mRecyclerView_Post.addItemDecoration(new SpacingDecoration(spacingInPixels));

                        mPostAdapter.setPostHandler(PostFragment.this);
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
            mPostAdapter.releasePostHandler();
    }

    @Override
    public void postOnClick(Post _post) {

        PostInfoFragment postInfoFragment = new PostInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.POST_BUNDLE_KEY, _post);
        postInfoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, postInfoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}