package com.example.jinsu.posters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.jinsu.posters.databinding.ActivityChangeBinding;

import java.util.ArrayList;

/**
 * Created by jinsu on 2018-03-27.
 */

public class ChangeActivity extends Activity {
    private ActivityChangeBinding binding;
    private RecyclerView.Adapter adapter;
    private ArrayList<ChangeItem> change_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change);
        binding.setChange(this);
        setRecyclerView();

        binding.changeRecycle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


   /* public void onScroll()
    {
        binding.btnOk.setVisibility(View.INVISIBLE);
    }*/
    //RecyclerView 넣기
    public void setRecyclerView()
    {
        // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
        // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
        // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        binding.changeRecycle.setHasFixedSize(true);

        // RecyclerView에 Adapter를 설정해줍니다.
        adapter = new ChangeAdapter(change_items);
        binding.changeRecycle.setAdapter(adapter);

        //가로 또는 세로 스크롤 목록 형식
        binding.changeRecycle.setLayoutManager(new LinearLayoutManager(this));
        setData();

    }

    private void setData()
    {
        change_items.clear();
        // RecyclerView 에 들어갈 데이터를 추가합니다.
        change_items.add(new ChangeItem("GS25","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.gs),true));
        change_items.add(new ChangeItem("애슐리","에이드 증정(2인 이상 식사 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.ashley),false));
        change_items.add(new ChangeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback),false));
        change_items.add(new ChangeItem("CGV","35% 할인(1만원 이상 결제 시)","사용 제한 없음",
                getResources().getDrawable(R.drawable.cgv),false));
        change_items.add(new ChangeItem("매드포갈릭","15% 할인(3만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.mad),false));
        change_items.add(new ChangeItem("배스킨라빈스","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.baskin),false));
        change_items.add(new ChangeItem("스타벅스","20% 할인(1만원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.starbucks),false));
        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.

        adapter.notifyDataSetChanged();
    }
}
