package com.example.jinsu.posters;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jinsu on 2018-03-25.
 */

public class PayFragment extends android.support.v4.app.Fragment {

    public PayFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_pay, container, false);
        return layout;
    }

}
