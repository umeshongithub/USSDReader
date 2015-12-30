package com.times.ussd.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.times.ussd.R;
import com.times.ussd.dto.Ussd;
import com.times.ussd.utils.DateUtils;

import java.util.List;

public class DeductionsAdapter extends RecyclerView.Adapter<DeductionsAdapter.ViewHolder> {
    private List<Ussd> mUssdList;

    public DeductionsAdapter(List<Ussd> ussdList) {
        this.mUssdList = ussdList;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public DeductionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_deduction, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.balance.setText("Rs. " + mUssdList.get(position).getBalance());
        viewHolder.time.setText(DateUtils.getFormattedTime(mUssdList.get(position).getTimeStamp()));
        viewHolder.date.setText(DateUtils.getFormattedDate(mUssdList.get(position).getTimeStamp()));
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView time, balance, date;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            balance = (TextView) itemLayoutView.findViewById(R.id.balance);
            time = (TextView) itemLayoutView.findViewById(R.id.time);
            date = (TextView) itemLayoutView.findViewById(R.id.date);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mUssdList.size();
    }
}