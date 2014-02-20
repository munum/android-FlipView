package se.emilsjolander.flipview;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;


public class FlippableItemAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mLabels;
    private boolean mSwipeable;
    private FlipAdapter mAdapter;
    private LayoutInflater inflater;

    public FlippableItemAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
        super(context, textViewResourceId, items);
        mLabels = items;
        mContext = context;
        mAdapter = new FlipAdapter(context);
        mAdapter.setCallback((FlipAdapter.Callback) context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLabels.size();
    }

    @Override
    public String getItem(int position) {
        return (String) mLabels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.row, null);
        final FlipView mFlipView = (FlipView) view.findViewById(R.id.flip_view);

        mFlipView.setAdapter(mAdapter);
        mFlipView.setOnFlipListener((FlipView.OnFlipListener) mContext);
        mFlipView.peakNext(false);
        mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
        mFlipView.setOnOverFlipListener((FlipView.OnOverFlipListener) mContext);


        return view;
    }



}