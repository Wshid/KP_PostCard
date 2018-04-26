/*
package com.example.jinsu.posters.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.jinsu.posters.ChangeItem;
import com.example.jinsu.posters.Data.RestClient;
import com.example.jinsu.posters.Data.RetroService;
import com.example.jinsu.posters.Model.Gift;
import com.example.jinsu.posters.Model.MyUser;
import com.example.jinsu.posters.R;
import com.example.jinsu.posters.Util.PolicyInterface;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ChangeViewModel extends BaseObservable {
    private Context context;
    private MyUser user;
    private ArrayList<ChangeItem> change_items;
    private RetroService retroService;
    private RestClient<RetroService> connect;
    private ArrayList<Gift> total_gift;
    private PolicyInterface policyInterface;

    public ChangeViewModel(@NonNull Context context)
    {
        this.context = context;
        this.policyInterface= (PolicyInterface) context;
        change_items = new ArrayList<>();
        setData();
    }

    private void setData()
    {
        change_items.add(new ChangeItem("우체국 택배","5% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                context.getResources().getDrawable(R.drawable.post),true,""));
        change_items.add(new ChangeItem("GS25","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                context.getResources().getDrawable(R.drawable.gs),true,""));
        change_items.add(new ChangeItem("애슐리","에이드 증정(2인 이상 식사 시)","월 1회 사용가능",
                context.getResources().getDrawable(R.drawable.ashley),true,""));
        change_items.add(new ChangeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                context. getResources().getDrawable(R.drawable.outback),true,""));
        change_items.add(new ChangeItem("CGV","35% 할인(1만원 이상 결제 시)","사용 제한 없음",
                context.getResources().getDrawable(R.drawable.cgv),false,""));
        change_items.add(new ChangeItem("매드포갈릭","15% 할인(3만원 이상 결제 시)","월 3회 사용가능",
                context.getResources().getDrawable(R.drawable.mad),false,""));
        change_items.add(new ChangeItem("배스킨라빈스","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                context.getResources().getDrawable(R.drawable.baskin),false,""));
        change_items.add(new ChangeItem("스타벅스","20% 할인(1만원 이상 결제 시)","1일 1회 사용가능",
                context.getResources().getDrawable(R.drawable.starbucks),false,""));
        change_items.add(new ChangeItem("CU","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                context.getResources().getDrawable(R.drawable.cu),false,""));
        change_items.add(new ChangeItem("서브웨이","세트 업그레이드","1일 1회 사용가능",
                context. getResources().getDrawable(R.drawable.subway),false,""));
        change_items.add(new ChangeItem("투썸플레이스","15% 할인(1만원 이상 결제 시)","월 5회 사용가능",
                context.getResources().getDrawable(R.drawable.twosome),false,""));
        change_items.add(new ChangeItem("KFC","10% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                context.getResources().getDrawable(R.drawable.kfc),false,""));
        change_items.add(new ChangeItem("피자헛","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                context. getResources().getDrawable(R.drawable.pizzahut),false,""));
        change_items.add(new ChangeItem("롯데월드","50% 할인(자유이용권 이용 시)","월 1회 사용가능",
                context. getResources().getDrawable(R.drawable.lotteworld),false,""));
        change_items.add(new ChangeItem("인터파크 쇼핑","10% 할인(5만원 이상 결제 시)","월 1회 사용가능",
                context.getResources().getDrawable(R.drawable.interpark),false,""));
        change_items.add(new ChangeItem("Sum Cafe","10% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                context.getResources().getDrawable(R.drawable.sm),false,""));
        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.

        adapter.notifyDataSetChanged();
    }

    private void getUser()
    {

        SharedPreferences mPrefs = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User","");
        user = gson.fromJson(json,MyUser.class);
        Log.d("my_change",user.getCard_key());
    }

    public void onPolicyClick(View v)
    {
        policyInterface.onPolicyClick(v);
    }

    public ArrayList<ChangeItem> getChange_items() {
        return change_items;
    }

    public void onBtnChClick(View v) {

    }


}
*/
