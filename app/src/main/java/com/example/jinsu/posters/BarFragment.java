package com.example.jinsu.posters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by jinsu on 2018-03-25.
 */

public class BarFragment extends Fragment {
    public BarFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("my_bar","bar create@@@@@");


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bar, container, false);

        Log.d("my_bar","bar create view@@");
        return layout;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        //화면이 실제로 보일 떄
        if(isVisibleToUser)
        {
                startActivity(new Intent(super.getActivity(), ScanActivity.class));

        }
        else
        {

        }
    }


}
