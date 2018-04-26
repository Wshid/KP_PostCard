package com.example.jinsu.posters;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinsu.posters.databinding.FragmentListBinding;

import java.util.ArrayList;

/**
 * Created by jinsu on 2018-03-25.
 */

public class ListFragment extends Fragment {
    private FragmentListBinding binding;
    private RecyclerView.Adapter adapter;
    private ArrayList<Listitem> myList;

    public ListFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false);
        myList = new ArrayList<>();
        //이미지뷰 둥글게 하기

        binding.setList(this);

        setRecyclerView();
        return binding.getRoot();

    }

    public void setRecyclerView()
    {
        // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
        // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
        // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        binding.listRecycle.setHasFixedSize(true);

        // RecyclerView에 Adapter를 설정해줍니다.
        adapter = new ListAdapter(myList);
        binding.listRecycle.setAdapter(adapter);

        //가로 또는 세로 스크롤 목록 형식
        binding.listRecycle.setLayoutManager(new LinearLayoutManager(super.getContext()));
        setData();

    }

    public void oneClick(View v)
    {

    }
    public void threeClick(View v)
    {

    }
    public void sixClick(View v)
    {

    }
    public void setData()
    {
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","입금 2,600 원",
                "잔액 332,500 원",true));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        myList.add(new Listitem("2018.04.06 12시16분", "GS25 숭실대점","출금 2,600 원",
                "잔액 332,500 원",false));
        adapter.notifyDataSetChanged();
    }
}
