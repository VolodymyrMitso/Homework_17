package mitso.v.homework_17.fragments.photo_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Photo;
import mitso.v.homework_17.api.response.PhotoListResponse;
import mitso.v.homework_17.fragments.BaseFragment;

public class PhotoFragment extends BaseFragment {

    private static final String LOG_TAG = "PhotoFragment";

    private RecyclerView        mRecyclerView_Photo;
    private PhotoAdapter        mPhotoAdapter;
    private ArrayList<Photo>    mPhotoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.photo_frgament, container, false);

        int id =  getArguments().getInt("album id");

        Api.getPhotosByAlbum(id, new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                Log.d(LOG_TAG, "onSuccess");

                PhotoListResponse photoListResponse = (PhotoListResponse) object;
                ArrayList<Photo> photoArrayList = photoListResponse.getPhotos();
                mPhotoList = photoArrayList;

                Log.e(LOG_TAG, "photoArrayList.size:" + photoArrayList.size());

                Log.e(LOG_TAG, "photoArrayList.first:" + photoArrayList.get(0));
                Log.e(LOG_TAG, "photoArrayList.last:" + photoArrayList.get(49));


                mRecyclerView_Photo = (RecyclerView) rootView.findViewById(R.id.rv_Photos_PF);
                mPhotoAdapter = new PhotoAdapter(mPhotoList);
                mRecyclerView_Photo.setAdapter(mPhotoAdapter);
                mRecyclerView_Photo.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                mRecyclerView_Photo.addItemDecoration(new SpacingDecoration(spacingInPixels));
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Log.d(LOG_TAG, "onFailure=" + errorMessage);
            }
        });

        return rootView;
    }
}
