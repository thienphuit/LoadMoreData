package com.example.loadmoredatarest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.model.Article;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieArticleAdapter extends RecyclerView.Adapter<MovieArticleAdapter.ViewHolder> {
    private Context context;
    ArrayList<Article> articleArrayList;
    private final   ClickListener clickListener;
    public interface ClickListener{
        void onPositionClicked(Article article);
    }
    public MovieArticleAdapter(Context context, ArrayList<Article> articleArrayList,ClickListener clickListener) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_row_movie_article,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Article article=articleArrayList.get(position);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.tvAuthorAndPublishedAt.setText("-"+article.getAuthor() +" | "+"Piblishetd At: "+article.getPublishedAt());
        viewHolder.tvDescription.setText(article.getDescription());
        Glide.with(context)
                .load(article.getUrlToImage())
                .into(viewHolder.imgViewCover);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //using WeakReference eliminate a potential memory leak.
        WeakReference<ClickListener> weakReference;
        @BindView(R.id.imgViewCover)
        ImageView imgViewCover;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvAuthorAndPublishedAt)
         TextView tvAuthorAndPublishedAt;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            weakReference = new WeakReference<>(clickListener);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            weakReference.get().onPositionClicked(articleArrayList.get(getAdapterPosition()));
        }
    }
}
