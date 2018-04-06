package com.example.jinsu.posters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
                if(event.getAction() == MotionEvent.ACTION_DOWN ||
                        event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    binding.btnOk.setVisibility(View.INVISIBLE);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    binding.btnOk.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }


    public void onPolicyClick(View v)
    {
        startActivity(new Intent(ChangeActivity.super.getBaseContext(), PolicyActivity.class));
    }
    public void onBtnChClick(View v)
    {
        if(true)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("불가능 합니다. 혜택 정책을 보고 오세요")
                    .setTitle("경고")
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNeutralButton("정책 보기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            alert.show();
        }
        else
        {

        }
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
        change_items.add(new ChangeItem("우체국 택배","5% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.post),true));
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
        change_items.add(new ChangeItem("CU","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.cu),false));
        change_items.add(new ChangeItem("서브웨이","세트 업그레이드","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.subway),false));
        change_items.add(new ChangeItem("투썸플레이스","15% 할인(1만원 이상 결제 시)","월 5회 사용가능",
                getResources().getDrawable(R.drawable.twosome),false));
        change_items.add(new ChangeItem("맥도날드","15% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.mcdonald),false));
        change_items.add(new ChangeItem("피자헛","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.pizzahut),false));
        change_items.add(new ChangeItem("롯데월드","50% 할인(자유이용권 이용 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.lotteworld),false));
        change_items.add(new ChangeItem("인터파크 쇼핑","10% 할인(5만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.interpark),false));
        change_items.add(new ChangeItem("Sum Cafe","10% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.sm),false));
        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.

        adapter.notifyDataSetChanged();
    }
}
