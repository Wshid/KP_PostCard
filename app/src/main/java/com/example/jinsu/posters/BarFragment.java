package com.example.jinsu.posters;

import android.app.Activity;
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

    private boolean hint = false;

    public BarFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("my_bar","onCreate()");



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bar, container, false);

        Log.d("my_bar","onCreateView()");
        if(hint)
        {
            Log.d("my_bar","hint");
            startActivity(new Intent(getContext(), ScanActivity.class));
        }

        return layout;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("my_bar","onResume()");

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("my_bar","onsetUserVisibleHint()");
        //화면이 실제로 보일 떄
        if(isVisibleToUser)
        {
            hint = true;
            Log.d("my_bar","실제로 보일 때");
           if(getActivity() != null)
           {
               startActivity(new Intent(getContext(), ScanActivity.class));
           }
        }

    }

    @Override
    public void onAttach(Activity activity)
    {
        Log.d("my_bar","onAttach()");
        super.onAttach(activity);
    }



}
