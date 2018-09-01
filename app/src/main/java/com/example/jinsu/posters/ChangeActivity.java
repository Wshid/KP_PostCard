package com.example.jinsu.posters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.jinsu.posters.Data.RestClient;
import com.example.jinsu.posters.Data.RetroService;
import com.example.jinsu.posters.Model.Gift;
import com.example.jinsu.posters.Model.MyUser;
import com.example.jinsu.posters.databinding.ActivityChangeBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinsu on 2018-03-27.
 */

public class ChangeActivity extends Activity {
    private ActivityChangeBinding binding;
    private RecyclerView.Adapter adapter;
    private ArrayList<ChangeItem> change_items = new ArrayList<>();
    private RetroService retroService;
    private RestClient<RetroService> connect;
    private MyUser user;
    private ArrayList<Gift> total_gift = new ArrayList<>();
   // private ChangeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("my_change","onCreate()");
        //viewModel = new ChangeViewModel(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change);
        binding.setChange(this);
        getUser();
        Connect();
        initData();



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

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("my_change","onResume()");
    }




   //내부DB에서 user데이터 불러오기
    private void getUser()
    {
        SharedPreferences mPrefs = getSharedPreferences("user",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User","");
        user = gson.fromJson(json,MyUser.class);
    }

    public void initData()
    {

        Call<ArrayList<Gift>> call = retroService.getGift();

        call.enqueue(new Callback<ArrayList<Gift>>() {
            @Override
            public void onResponse(Call<ArrayList<Gift>> call, Response<ArrayList<Gift>> response) {

                total_gift=response.body();
                Log.d("my_change",total_gift.get(1).getGift_title());
                setRecyclerView();

            }

            @Override
            public void onFailure(Call<ArrayList<Gift>> call, Throwable t) {
                Log.e("my_change",t.getMessage());
            }
        });

    }


    public void Connect()
    {
        connect = new RestClient<>();
        retroService = connect.getClient(RetroService.class);
    }

    public void onPolicyClick(View v)
    {
        startActivity(new Intent(this, PolicyActivity.class));
    }

    public void onBtnChClick(View v)
    {
        int cnt=0;

        for(ChangeItem changeItem : change_items)
        {
            if(changeItem.isCheked()==true)
            {
                Log.d("my_change",changeItem.getGift_key());
                cnt++;
                switch (cnt)
                {
                    case 1:
                    {
                        user.chGift_1(changeItem.getGift_key());
                        break;
                    }
                    case 2:
                    {
                        user.chGift_2(changeItem.getGift_key());
                        break;
                    }
                    case 3:
                    {
                        user.chGift_3(changeItem.getGift_key());
                        break;
                    }
                    case 4:
                    {
                        user.chGift_4(changeItem.getGift_key());
                        break;
                    }
                    case 5:
                    {
                        user.chGift_5(changeItem.getGift_key());
                        break;
                    }
                }
            }
        }
        //5개보다 많이 한 경우
        if(cnt > 5)
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
            SharedPreferences mPrefs = getSharedPreferences("user",MODE_PRIVATE);
            Gson gson = new Gson();

            SharedPreferences.Editor prefEditor = mPrefs.edit();
            String json = gson.toJson(user);
            prefEditor.putString("User",json);
            prefEditor.commit();
            Call<String> call = retroService.updateGift(user);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("my_change","update연결 성공");

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("my_change","update연결 실패");

                }
            });
            finish();
        }


    }

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
        Log.d("my_change",user.getGift_1() + "/" + user.getGift_2() + "/" + user.getGift_3() + "/"
        + user.getGift_4() + "/" +user.getGift_5() + "/" );
        for(Gift gift : total_gift)
        {

            String key = gift.getGift_idx();
            if(key.equals(user.getGift_1()) || key.equals(user.getGift_2()) || key.equals(user.getGift_3())
                    || key.equals(user.getGift_4()) || key.equals(user.getGift_5()))
            {
                String image_name = gift.getGift_image().replace("\r","");
                change_items.add(new ChangeItem(gift.getGift_title(),
                        gift.getGift_content(),
                        gift.getGift_limit(),
                        getResources().getIdentifier(image_name,"drawable",getPackageName()),
                        true,
                        gift.getGift_idx()
                ));

            }
        }

        for(Gift gift : total_gift)
        {
            String key = gift.getGift_idx();
            if(!key.equals(user.getGift_1()) && !key.equals(user.getGift_2()) && !key.equals(user.getGift_3())
                    && !key.equals(user.getGift_4()) && !key.equals(user.getGift_5()))
            {
                String image_name = gift.getGift_image().replace("\r","");
                change_items.add(new ChangeItem(gift.getGift_title(),
                        gift.getGift_content(),
                        gift.getGift_limit(),
                        getResources().getIdentifier(image_name,"drawable",getPackageName()),
                        false,
                        gift.getGift_idx()
                ));

            }
        }
        /*change_items.add(new ChangeItem("우체국 택배","5% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                getResources().getIdentifier("post","drawable",getPackageName()),true,""));*/
        /*change_items.add(new ChangeItem("GS25","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources(R.drawable.gs),true,""));
        change_items.add(new ChangeItem("애슐리","에이드 증정(2인 이상 식사 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.ashley),true,""));
        change_items.add(new ChangeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback),true,""));
        change_items.add(new ChangeItem("CGV","35% 할인(1만원 이상 결제 시)","사용 제한 없음",
                getResources().getDrawable(R.drawable.cgv),false,""));
        change_items.add(new ChangeItem("매드포갈릭","15% 할인(3만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.mad),false,""));
        change_items.add(new ChangeItem("배스킨라빈스","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.baskin),false,""));
        change_items.add(new ChangeItem("스타벅스","20% 할인(1만원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.starbucks),false,""));
        change_items.add(new ChangeItem("CU","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.cu),false,""));
        change_items.add(new ChangeItem("서브웨이","세트 업그레이드","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.subway),false,""));
        change_items.add(new ChangeItem("투썸플레이스","15% 할인(1만원 이상 결제 시)","월 5회 사용가능",
                getResources().getDrawable(R.drawable.twosome),false,""));
        change_items.add(new ChangeItem("KFC","10% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.kfc),false,""));
        change_items.add(new ChangeItem("피자헛","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.pizzahut),false,""));
        change_items.add(new ChangeItem("롯데월드","50% 할인(자유이용권 이용 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.lotteworld),false,""));
        change_items.add(new ChangeItem("인터파크 쇼핑","10% 할인(5만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.interpark),false,""));
        change_items.add(new ChangeItem("Sum Cafe","10% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.sm),false,""));*/

        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.

        adapter.notifyDataSetChanged();
    }
}
