package com.shahriar.appusagemonitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.shahriar.appusagemonitor.GatherInfo.main;


/**
 * A simple {@link Fragment} subclass.
 */
public class Year extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        main("year", getView(), this.getActivity());
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onResume() {
       main("year", getView(), this.getActivity());
        super.onResume();
    }
}
