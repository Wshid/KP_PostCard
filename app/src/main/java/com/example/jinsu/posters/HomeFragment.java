package com.example.jinsu.posters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinsu.posters.Data.RestClient;
import com.example.jinsu.posters.Data.RetroService;
import com.example.jinsu.posters.Model.MyUser;
import com.example.jinsu.posters.Model.User;
import com.example.jinsu.posters.databinding.FragmentHomeBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RetroService retroService;
    private RestClient<RetroService> connect;
    private FragmentHomeBinding binding;
    private GradientDrawable drawable;
    private RecyclerView.Adapter adapter;
    private ArrayList<HomeItem> home_items = new ArrayList<>();
    private MyUser user;
    public HomeFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Connect();
        initUser();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        //이미지뷰 둥글게 하기
        drawable = (GradientDrawable)getContext().getDrawable(R.drawable.round);
        binding.imageCard.setBackground(drawable);
        binding.imageCard.setClipToOutline(true);

        binding.setHome(this);

        /*binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("homefrag","혜택 바꾸기 버튼 누름");
                startActivity(new Intent(HomeFragment.super.getContext(), ChangeActivity.class));
            }
        });*/
        setRecyclerView();
        return binding.getRoot();

    }

    //혜택 바꾸기 버튼 클릭리스너
    public void onChangeButtonClick(View view)
    {
        Log.e("homefrag","혜택 바꾸기 버튼 누름");
        startActivity(new Intent(super.getContext(), ChangeActivity.class));
    }

    public void onImClick(View v)
    {
        startActivity(new Intent(super.getContext(), DesignActivity.class));
    }


    //RecyclerView 넣기
    public void setRecyclerView()
    {
        // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
        // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
        // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        binding.recycle.setHasFixedSize(true);

        // RecyclerView에 Adapter를 설정해줍니다.
        adapter = new RecycleAdapter(home_items);
        binding.recycle.setAdapter(adapter);

        //가로 또는 세로 스크롤 목록 형식
        binding.recycle.setLayoutManager(new LinearLayoutManager(super.getContext()));
        setData();

    }
    private void setData()
    {
        home_items.clear();
        // RecyclerView 에 들어갈 데이터를 추가합니다.
        home_items.add(new HomeItem("우체국 택배","5% 할인(1만원 이상 결제 시)","월 3회 사용가능",
                getResources().getDrawable(R.drawable.post)));
        home_items.add(new HomeItem("GS25","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.gs)));
        home_items.add(new HomeItem("애슐리","에이드 증정(2인 이상 식사 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.ashley)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        home_items.add(new HomeItem("스타벅스","20% 할인(1만원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.starbucks)));

        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
        adapter.notifyDataSetChanged();
    }

    public void Connect()
    {
        connect = new RestClient<>();
        retroService = connect.getClient(RetroService.class);
    }
    public void initUser()
    {
        Call<User> call = retroService.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = new MyUser(response.body().getCard_key(),response.body().getGift_1(),response.body().getGift_2(),
                        response.body().getGift_3(),response.body().getGift_4(),response.body().getGift_5());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        SharedPreferences mPref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = mPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefEditor.putString("User",json);
        prefEditor.commit();
    }


}
