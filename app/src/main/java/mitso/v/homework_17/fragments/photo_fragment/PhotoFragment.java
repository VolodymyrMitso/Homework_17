package mitso.v.homework_17.fragments.photo_fragment;

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
import mitso.v.homework_17.api.models.Photo;
import mitso.v.homework_17.api.response.PhotoListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class PhotoFragment extends BaseFragment {

    private final String        LOG_TAG = Constants.PHOTO_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Photo;
    private PhotoAdapter        mPhotoAdapter;
    private ArrayList<Photo>    mPhotoList;

    private Integer             albumId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.photo_frgament, container, false);

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(mMainActivity.getResources().getString(R.string.s_photo_list));

        try {
            albumId = getArguments().getInt(Constants.ALBUM_ID_BUNDLE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
        }

        if (albumId != null && albumId != 0) {

            if (CheckConnection.checkConnection(mMainActivity)) {

                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_connecting), Toast.LENGTH_SHORT).show();

                Api.getPhotosByAlbum(albumId, new ConnectCallback() {
                    @Override
                    public void onSuccess(Object object) {

                        PhotoListResponse photoListResponse = (PhotoListResponse) object;
                        ArrayList<Photo> photoArrayList = photoListResponse.getPhotos();
                        mPhotoList = photoArrayList;

                        Log.e(LOG_TAG, String.valueOf(photoArrayList.size()));
                        Log.e(LOG_TAG, photoArrayList.get(0).toString());
                        Log.e(LOG_TAG, photoArrayList.get(photoArrayList.size() - 1).toString());

                        mRecyclerView_Photo = (RecyclerView) rootView.findViewById(R.id.rv_Photos_FF);
                        mPhotoAdapter = new PhotoAdapter(mPhotoList);
                        mRecyclerView_Photo.setAdapter(mPhotoAdapter);
                        mRecyclerView_Photo.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                        int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                        mRecyclerView_Photo.addItemDecoration(new SpacingDecoration(spacingInPixels));

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
}
