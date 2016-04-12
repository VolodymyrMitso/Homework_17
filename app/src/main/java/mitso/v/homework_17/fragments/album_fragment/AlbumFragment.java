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
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class AlbumFragment extends BaseFragment implements IAlbumHandler {

    private static final String LOG_TAG = "AlbumFragment";

    private RecyclerView        mRecyclerView_Album;
    private AlbumAdapter        mAlbumAdapter;
    private ArrayList<Album>    mAlbumList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.album_fragment, container, false);

        int id =  getArguments().getInt("id");

        if (CheckConnection.checkConnection(mMainActivity)) {

            Api.getAlbumsByUser(id, new ConnectCallback() {
                @Override
                public void onSuccess(Object object) {
                    Log.d(LOG_TAG, "onSuccess");

                    AlbumListResponse albumListResponse = (AlbumListResponse) object;
                    ArrayList<Album> albumArrayList = albumListResponse.getAlbums();
                    mAlbumList = albumArrayList;

                    Log.e(LOG_TAG, "albumArrayList.size:" + albumArrayList.size());

                    Log.e(LOG_TAG, "albumArrayList.first:" + albumArrayList.get(0));
                    Log.e(LOG_TAG, "albumArrayList.last:" + albumArrayList.get(9));


                    mRecyclerView_Album = (RecyclerView) rootView.findViewById(R.id.rv_Albums_AF);
                    mAlbumAdapter = new AlbumAdapter(mAlbumList);
                    mRecyclerView_Album.setAdapter(mAlbumAdapter);
                    mRecyclerView_Album.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                    mRecyclerView_Album.addItemDecoration(new SpacingDecoration(spacingInPixels));

                    mAlbumAdapter.setAlbumHandler(AlbumFragment.this);
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

        if (mAlbumAdapter.checkAlbumHandler())
            mAlbumAdapter.releaseAlbumHandler();
    }

    @Override
    public void albumOnClick(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("album id", id);

        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, photoFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}