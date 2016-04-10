package mitso.v.homework_17.fragments.album_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private ArrayList<Album> mAlbumList;
    private IAlbumHandler mAlbumHandler;

    public AlbumAdapter(ArrayList<Album> albumList) {
        this.mAlbumList = albumList;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, final int position) {
        final Album album = mAlbumList.get(position);
        holder.mTextView_AlbumTitle.setText(album.getTitle());
        holder.mTextView_AlbumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlbumHandler.albumOnClick(album.getId());
            }
        });
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    public void setAlbumHandler(IAlbumHandler albumHandler) {
        this.mAlbumHandler = albumHandler;
    }

    public void releaseIUserHAndler() {
        this.mAlbumHandler = null;
    }
}
