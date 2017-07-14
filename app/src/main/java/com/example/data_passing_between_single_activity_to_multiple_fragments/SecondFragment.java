package com.example.data_passing_between_single_activity_to_multiple_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements FragmentCommunicator {

    private TextView textView1;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        textView1 = (TextView) view.findViewById(R.id.textView1);

        if (getActivity() instanceof TempActivity) {
            ((TempActivity) getActivity()).fragmentCommunicators.add(this);
        }

        return view;
    }

    @Override
    public void passDataToFragment(String data) {

        textView1.setText(data);
    }
}