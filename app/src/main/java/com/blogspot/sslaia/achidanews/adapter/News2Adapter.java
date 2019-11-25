package com.blogspot.sslaia.achidanews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.sslaia.achidanews.databinding.NewsItemBinding;
import com.blogspot.sslaia.achidanews.model.Article;
import com.squareup.picasso.Picasso;

public class News2Adapter extends PagedListAdapter<Article, RecyclerView.ViewHolder> {

    private Context context;
    private boolean showImages;

    public News2Adapter(Context context, boolean showImages) {
        super(Article.DIFF_CALLBACK);
        this.context = context;
        this.showImages = showImages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            NewsItemBinding itemBinding = NewsItemBinding.inflate(layoutInflater, parent, false);
            NewsItemViewHolder viewHolder = new NewsItemViewHolder(itemBinding);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((NewsItemViewHolder) holder).bindTo(getItem(position));
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {

        private NewsItemBinding binding;

        public NewsItemViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bindTo(Article article) {

            if (showImages) {
                if (article.getImage() == null
                        || article.getImage().isEmpty()) {
                    binding.newsImage.setVisibility(View.GONE);
                } else {
                    Picasso.get().load(article.getImage()).resize(356, 200).centerCrop().into(binding.newsImage);
                }
            } else {
                binding.newsImage.setVisibility(View.GONE);
            }

            if (article.getAuthor() == null || article.getAuthor().isEmpty()) {
                binding.newsAuthor.setVisibility(View.GONE);
            } else {
                binding.newsAuthor.setText(article.getAuthor());
            }

            binding.newsDate.setText(article.getPubDate().substring(0,10));
            binding.newsTitle.setText(article.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newsUrl = article.getUrl();

                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    int guColor = Color.parseColor("#01579b");
                    builder.setToolbarColor(guColor);
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(newsUrl));
                }
            });
        }
    }
}
