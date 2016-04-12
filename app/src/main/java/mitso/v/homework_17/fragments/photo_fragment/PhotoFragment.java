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

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Photo;
import mitso.v.homework_17.api.response.PhotoListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class PhotoFragment extends BaseFragment {

    private static final String LOG_TAG = "PHOTO FRAGMENT";

    private RecyclerView        mRecyclerView_Photo;
    private PhotoAdapter        mPhotoAdapter;
    private ArrayList<Photo>    mPhotoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.photo_frgament, container, false);

        int id =  getArguments().getInt("album id");

        if (CheckConnection.checkConnection(mMainActivity)) {

            Toast.makeText(mMainActivity, getResources().getString(R.string.connecting), Toast.LENGTH_SHORT).show();

            Api.getPhotosByAlbum(id, new ConnectCallback() {
                @Override
                public void onSuccess(Object object) {

                    PhotoListResponse photoListResponse = (PhotoListResponse) object;
                    ArrayList<Photo> photoArrayList = photoListResponse.getPhotos();
                    mPhotoList = photoArrayList;

                    Log.e(LOG_TAG, String.valueOf(photoArrayList.size()));
                    Log.e(LOG_TAG, photoArrayList.get(0).toString());
                    Log.e(LOG_TAG, photoArrayList.get(photoArrayList.size() - 1).toString());


                    mRecyclerView_Photo = (RecyclerView) rootView.findViewById(R.id.rv_Photos_PF);
                    mPhotoAdapter = new PhotoAdapter(mPhotoList);
                    mRecyclerView_Photo.setAdapter(mPhotoAdapter);
                    mRecyclerView_Photo.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                    mRecyclerView_Photo.addItemDecoration(new SpacingDecoration(spacingInPixels));

                    Log.d(LOG_TAG, "onSuccess");
                    Toast.makeText(mMainActivity, getResources().getString(R.string.success), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable throwable, String errorMessage) {
                    Log.d(LOG_TAG, "onFailure");
                    Toast.makeText(mMainActivity, getResources().getString(R.string.failure), Toast.LENGTH_SHORT).show();
                }
            });

        } else
            Toast.makeText(mMainActivity, getResources().getString(R.string.no_connection), Toast.LENGTH_SHORT).show();

        return rootView;
    }
}
