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

import com.blogspot.sslaia.achidanews.R;
import com.blogspot.sslaia.achidanews.databinding.NewsItemBinding;
import com.blogspot.sslaia.achidanews.helpers.AppUtils;
import com.blogspot.sslaia.achidanews.model.News;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends PagedListAdapter<News, RecyclerView.ViewHolder> {

    private Context context;
    private boolean showImages;

    public NewsAdapter(Context context, boolean showImages) {
        super(News.DIFF_CALLBACK);
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

        public void bindTo(News news) {

            String byline = news.getFields().getAuthor() == null
                    || news.getFields().getAuthor().isEmpty() ? context.getString(R.string.not_available) : news.getFields().getAuthor();

            if (showImages) {
                if (news.getFields().getImage() == null
                        || news.getFields().getImage().isEmpty()) {
                    binding.newsImage.setVisibility(View.GONE);
                } else {
                    Picasso.get().load(news.getFields().getImage())
                            .resize(356, 200).centerCrop().into(binding.newsImage);
                }
            } else {
                binding.newsImage.setVisibility(View.GONE);
            }

            binding.newsDate.setText(String.format(context.getString(R.string.date_format),
                    AppUtils.getDate(news.getPubDate()),
                    AppUtils.getTime(news.getPubDate())));
            binding.newsAuthor.setText(byline);
            binding.newsTitle.setText(news.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newsUrl = news.getUrl();

                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    int guColor = Color.parseColor("#0b224d");
                    builder.setToolbarColor(guColor);
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(newsUrl));
                }
            });
        }
    }
}
