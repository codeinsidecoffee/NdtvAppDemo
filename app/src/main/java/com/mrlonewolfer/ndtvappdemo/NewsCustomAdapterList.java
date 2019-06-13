package com.mrlonewolfer.ndtvappdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsCustomAdapterList extends RecyclerView.Adapter<NewsCustomAdapterList.newsViewHolder> {

    private List<NewsBean> newsBeanArrayList;
    Context context;

    public NewsCustomAdapterList(Context context, List<NewsBean> newsBeanArrayList, OnNewsClickListener listener) {
        this.newsBeanArrayList = newsBeanArrayList;
        this.listener = listener;
        this.context=context;
    }

    public interface OnNewsClickListener{
        void onUserClick(ArrayList<NewsBean> newsBeanArrayList);
    }
   OnNewsClickListener listener;


    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_row_item,viewGroup,false);
        newsViewHolder viewHolder = new newsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
        final NewsBean news=newsBeanArrayList.get(position);
        holder.txtTitle.setText(news.getTitle());
        holder.txtDescription.setText(news.getDescription());
        Picasso.with(context)
                .load(news.getStoryImage())
                .into(holder.imgThumb);

    }

    @Override
    public int getItemCount() {
        return newsBeanArrayList.size();
    }

    public class newsViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txtDescription;
        ImageView imgThumb;

        public newsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            imgThumb=itemView.findViewById(R.id.imgThumb);

        }
    }

}
