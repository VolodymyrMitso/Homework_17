package mitso.v.homework_17.fragments.album_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView_AlbumTitle;

    public AlbumViewHolder(View itemView) {
        super(itemView);

        mTextView_AlbumTitle = (TextView) itemView.findViewById(R.id.tv_AlbumTitle_AC);
    }

    public TextView getTextView_AlbumTitle() {
        return mTextView_AlbumTitle;
    }
}