package com.picotto86.laciana;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by picot_000 on 04/12/2014.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<FeedListRowHolder> {

    private List<FeedItem> feedItemList;
    private Context mContext;

    public MyRecyclerAdapter(NotizieFragment context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context.getActivity();
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        FeedListRowHolder mh = new FeedListRowHolder(v);

        return mh;
    }



    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        FeedItem feedItem = feedItemList.get(i);

        //Picasso.with(mContext).load(feedItem.getThumbnail())
          //      .error(R.drawable.placeholder)
            //    .placeholder(R.drawable.placeholder)
              //  .into(feedListRowHolder.thumbnail);


        feedListRowHolder.title.setText(Html.fromHtml(feedItem.getTitle()));
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
}