package br.com.mirante.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.mirante.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.postTitle) TextView mPostTitle;
        @Bind(R.id.postText) TextView mPostText;
        @Bind(R.id.postImage) ImageView mPostImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public FeedAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.feed_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPostTitle.setText(mDataset[position]);

    }

    @Override public int getItemCount() {
        return mDataset.length;
    }
}