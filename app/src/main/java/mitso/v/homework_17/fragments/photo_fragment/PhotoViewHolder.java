package mitso.v.homework_17.fragments.photo_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView_PhotoTitle;
    private TextView mTextView_PhotoUrl;

    public PhotoViewHolder(View itemView) {
        super(itemView);

        mTextView_PhotoTitle = (TextView) itemView.findViewById(R.id.tv_PhotoTitle_FC);
        mTextView_PhotoUrl = (TextView) itemView.findViewById(R.id.tv_PhotoUrl_FC);
    }

    public TextView getTextView_PhotoTitle() {
        return mTextView_PhotoTitle;
    }

    public TextView getTextView_PhotoUrl() {
        return mTextView_PhotoUrl;
    }
}