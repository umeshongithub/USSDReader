package com.times.ussd.ui;

import com.times.ussd.db.UssdDataSource;
import com.times.ussd.dto.Ussd;
import com.times.ussd.ui.adapters.DeductionsAdapter;
import com.times.ussd.views.DividerItemDecoration;

import java.util.List;


/**
 * Created by umesh on 27/11/15.
 */
public class DeductionsFragment extends RecyclerViewFragment {

    @Override
    public void onResume() {
        super.onResume();
        fetchUssdData();
    }

    private void fetchUssdData() {
        UssdDataSource datasource = new UssdDataSource(getActivity());
        datasource.open();

        List<Ussd> ussdList = datasource.getAllUssds();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(new DeductionsAdapter(ussdList));
        hideProgressBar();
        if (ussdList.size() == 0)
            setEmptyView();

    }

}