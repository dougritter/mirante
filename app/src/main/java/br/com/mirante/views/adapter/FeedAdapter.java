package br.com.mirante.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.mirante.R;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.info_text);
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
        holder.mTextView.setText(mDataset[position]);

    }

    @Override public int getItemCount() {
        return mDataset.length;
    }
}