package mitso.v.homework_17.fragments.photo_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private ArrayList<Photo>    mPhotoList;

    public PhotoAdapter(ArrayList<Photo> photoList) {
        this.mPhotoList = photoList;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        final Photo photo = mPhotoList.get(position);

        holder.getTextView_PhotoTitle().setText(photo.getTitle());
        holder.getTextView_PhotoUrl().setText(photo.getUrl());
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_card, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}
