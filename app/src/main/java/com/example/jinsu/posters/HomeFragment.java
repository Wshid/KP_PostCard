package com.example.jinsu.posters;

import android.annotation.TargetApi;
import android.content.Intent;
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

import com.example.jinsu.posters.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private Retrofit retrofit;
    private RetroService retroService;
    private RestClient<RetroService> connect;
    private FragmentHomeBinding binding;
    private GradientDrawable drawable;
    private RecyclerView.Adapter adapter;
    private ArrayList<HomeItem> home_items = new ArrayList<>();
    public HomeFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Connect();
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
        home_items.add(new HomeItem("GS25","10% 할인(5천원 이상 결제 시)","1일 1회 사용가능",
                getResources().getDrawable(R.drawable.gs)));
        home_items.add(new HomeItem("애슐리","에이드 증정(2인 이상 식사 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.ashley)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        home_items.add(new HomeItem("아웃백","30% 할인(3만원 이상 결제 시)","월 1회 사용가능",
                getResources().getDrawable(R.drawable.outback)));
        // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
        adapter.notifyDataSetChanged();
    }

    public void Connect()
    {
       // connect = new RestClient<>();
        //retroService = connect.getClient(RetroService.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://49.142.64.32:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retroService = retrofit.create(RetroService.class);
        getUser();
    }
    public void getUser()
    {
      //  Call<Test2> call = retroService.getRespos("meansoup");
       // Call<List<Test>> call = retroService.get();
        Test t = new Test("멍윤","z");
        Call<Test> call2 = retroService.post(t);

       /* call.enqueue(new Callback<Test2>() {
            @Override
            public void onResponse(Call<Test2> call, Response<Test2> response) {
                Log.e("my_login","서버와의 통신 성공");
                if(response.isSuccessful() && response.body() != null)
                {
                    String str = "response code : " + response.code() + "\nID : " +
                            response.body().login + "\nURL : " + response.body().html_url;
                    Log.e("my_login",str);

                }
            }

            @Override
            public void onFailure(Call<Test2> call, Throwable t) {
                Log.e("my_login","서버와의 통신 실패");
            }
        });*/

        /*call.enqueue(new Callback<List<Test>>() {

            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                Log.i("my_login","서버와의 통신 성공");
                List<Test> data = response.body();

                if(response.isSuccessful() && response.body() != null)
                {
                    String str = ("ID : " +
                            data.get(1)._id);
                    Log.i("gotest",str);

                }
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {

                Log.e("my_login","서버와의 통신 실패 : "+t.getMessage());
            }
        });*/

        call2.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                Log.i("my_login","서버와의 통신 성공");
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                Log.e("my_login","서버와의 통신 실패 : "+t.getMessage());
            }
        });
    }
}
