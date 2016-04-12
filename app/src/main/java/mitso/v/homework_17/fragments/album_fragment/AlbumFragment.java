package mitso.v.homework_17.fragments.album_fragment;

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
import mitso.v.homework_17.api.models.Album;
import mitso.v.homework_17.api.response.AlbumListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.photo_fragment.PhotoFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class AlbumFragment extends BaseFragment implements IAlbumHandler {

    private final String        LOG_TAG = Constants.ALBUM_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Album;
    private AlbumAdapter        mAlbumAdapter;
    private ArrayList<Album>    mAlbumList;

    private boolean             isHandlerSet;

    private Integer userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.album_fragment, container, false);

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(mMainActivity.getResources().getString(R.string.s_album_list));

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

                Api.getAlbumsByUser(userId, new ConnectCallback() {
                    @Override
                    public void onSuccess(Object object) {

                        AlbumListResponse albumListResponse = (AlbumListResponse) object;
                        ArrayList<Album> albumArrayList = albumListResponse.getAlbums();
                        mAlbumList = albumArrayList;

                        Log.e(LOG_TAG, String.valueOf(albumArrayList.size()));
                        Log.e(LOG_TAG, albumArrayList.get(0).toString());
                        Log.e(LOG_TAG, albumArrayList.get(albumArrayList.size() - 1).toString());


                        mRecyclerView_Album = (RecyclerView) rootView.findViewById(R.id.rv_Albums_AF);
                        mAlbumAdapter = new AlbumAdapter(mAlbumList);
                        mRecyclerView_Album.setAdapter(mAlbumAdapter);
                        mRecyclerView_Album.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                        int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                        mRecyclerView_Album.addItemDecoration(new SpacingDecoration(spacingInPixels));

                        mAlbumAdapter.setAlbumHandler(AlbumFragment.this);
                        isHandlerSet = true;

                        Log.d(LOG_TAG, "onSuccess");
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d(LOG_TAG, "onFailure");
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
            mAlbumAdapter.releaseAlbumHandler();
    }

    @Override
    public void albumOnClick(int _id) {

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ALBUM_ID_BUNDLE_KEY, _id);
        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, photoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}