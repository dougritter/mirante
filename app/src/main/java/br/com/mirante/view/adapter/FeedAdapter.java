package br.com.mirante.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseObject;

import java.util.List;

import br.com.mirante.BR;
import br.com.mirante.R;
import br.com.mirante.databinding.FeedItemBinding;
import br.com.mirante.model.Post;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder> {
    private List<Post> mDataset;

    public static class FeedItemViewHolder extends RecyclerView.ViewHolder {
        private FeedItemBinding mBinding;

        public FeedItemViewHolder(FeedItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }

    }

    public FeedAdapter(List<Post> myDataset) {
        mDataset = myDataset;
    }

    public List<Post> getDataset() {
        return mDataset;
    }

    public void setDataset(List<Post> dataset) {
        mDataset = dataset;
        notifyDataSetChanged();
    }

    @Override public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.feed_item, parent, false);
        return new FeedItemViewHolder(binding);

    }

    @Override public void onBindViewHolder(FeedItemViewHolder holder, int position) {
        Post post = mDataset.get(position);
        holder.getBinding().setVariable(BR.post, post);
        holder.getBinding().executePendingBindings();

    }

    @Override public int getItemCount() {
        return mDataset.size();
    }

}