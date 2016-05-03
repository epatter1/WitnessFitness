package edu.ggc.epatter1.witnessfitness.RecyclerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ggc.epatter1.witnessfitness.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SortListActivityFragment extends Fragment {

    public SortListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort_list, container, false);
    }


}
