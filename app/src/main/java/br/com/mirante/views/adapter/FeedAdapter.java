package br.com.mirante.views.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mirante.BR;
import br.com.mirante.R;
import br.com.mirante.databinding.FeedItemBinding;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder> {
    private String[] mDataset;

    public static class FeedItemViewHolder extends RecyclerView.ViewHolder {
        private FeedItemBinding mBinding;

        public FeedItemViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }

        public void setBinding(FeedItemBinding binding) {
            this.mBinding = binding;
        }

    }

    public FeedAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        return new FeedItemViewHolder(itemView);

    }

    @Override public void onBindViewHolder(FeedItemViewHolder holder, int position) {
//        Post user = users.get(position);
//        holder.getBinding().setVariable(BR.post, new Post());
//        holder.getBinding().executePendingBindings();

    }

    @Override public int getItemCount() {
        return mDataset.length;
    }

}