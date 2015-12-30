package com.times.ussd.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.times.ussd.R;


/**
 * Created by umesh on 27/11/15.
 */
public abstract class RecyclerViewFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private ProgressBar mProgressBar;
    protected boolean mLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    protected void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    protected void setEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);

    }

    protected void setEmptyView(String emptyText) {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setText(emptyText);
            mEmptyView.setVisibility(View.VISIBLE);
        }

    }

    protected void handleResponseError() {
        hideProgressBar();
        mLoading = false;
        setEmptyView(getString(R.string.data_not_available));
    }


}

