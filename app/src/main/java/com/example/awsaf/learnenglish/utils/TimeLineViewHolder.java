package com.example.awsaf.learnenglish.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.awsaf.learnenglish.R;
import com.github.vipulasri.timelineview.TimelineView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_timeline_date)
    TextView mDate;
    @BindView(R.id.text_timeline_title)
    TextView mMessage;
    @BindView(R.id.time_marker)
    TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        mTimelineView.initLine(viewType);
    }
}
