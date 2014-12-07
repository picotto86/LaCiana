package com.picotto86.laciana;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by picot_000 on 04/12/2014.
 */
public class FeedListRowHolder extends RecyclerView.ViewHolder {

    protected TextView title;


    public FeedListRowHolder(View view) {
        super(view);

        this.title = (TextView) view.findViewById(R.id.title);
    }

}