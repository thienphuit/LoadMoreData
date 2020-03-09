package com.example.loadmoredatarest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<Posts> mItemList;
    public RecyclerviewAdapter(Context context, List<Posts> mItemList){
        this.mItemList = mItemList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 :mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView mTextViewContent;
        @BindView(R.id.itemTv)
        TextView tvItem;
        @BindView(R.id.memberId)
         TextView mTextViewMember;
        @BindView(R.id.titleName)
         TextView mTextviewTitle;
        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
           // tvItem = itemView.findViewById(R.id.itemTv);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
           // progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        String memberID = mItemList.get(position).getId();
        String userId = mItemList.get(position).getUserId();
        String title = mItemList.get(position).getTitle();
        String content = mItemList.get(position).getBody();
        viewHolder.tvItem.setText(userId);
        viewHolder.mTextViewMember.setText(memberID);
        viewHolder.mTextviewTitle.setText(title);
        viewHolder.mTextViewContent.setText(content);

    }
}
