package com.example.jinsu.posters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

/**
 * Created by jinsu on 2018-03-25.
 */

public class BonusFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter testAdatper;
    private ArrayList<Object> myDataset;
  //  Button button;

    public BonusFragment()
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bonus, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(super.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        testAdatper = new TestAdapter(myDataset);

        mRecyclerView.setAdapter(testAdatper);
        setData();
        return layout;
    }

    public void setData()
    {
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(23f)); //여기 순위대로 파싱받아서 넣기
        yValues.add(new PieEntry(14f));
        yValues.add(new PieEntry(35f));

        ArrayList<PieEntry> yValues2 = new ArrayList<PieEntry>();

        yValues2.add(new PieEntry(45f)); //여기 순위대로 파싱받아서 넣기
        yValues2.add(new PieEntry(27f));
        yValues2.add(new PieEntry(15f));

        myDataset.add(new MyData3("이달 총 혜택 사용순위","롯데리아 2,300원",
                "스타벅스 1,800원","GS25 1,400원" +
                "","포스트카드를 통해 고객님이 이번 달 받은 혜택목록입니다.",yValues,
                R.drawable.one,
                R.drawable.two,
                R.drawable.three));

        myDataset.add(new MyData3("이달 총 지출 순위","GS25 25,300원",
                "맥도날드 17,500원","CU 13,200원" +
                "","포스트카드 혜택 가맹점 중, 고객님이 가장 많이 지출하신 목록입니다",yValues2,
                R.drawable.one2,
                R.drawable.two2,
                R.drawable.three2));

        myDataset.add(new MyData2("이번달 지출 10,000원","1만원이상 결제 시 10% 할인",
                "  고객님께 스타벅스를 추천합니다!", R.drawable.starbucks,0));
        myDataset.add(new MyData2("이번달 지출 15,800원","3만원이상 결제 시 15% 할인",
                "  오늘은 롯데리아 어때요?", R.drawable.lotteria,0));
        myDataset.add(new MyData2("이번달 지출 7,000원","5만원이상 결제 시 10% 할인",
                "  GS25시 오랜만에 방문하세요~", R.drawable.gs,0));
        testAdatper.notifyDataSetChanged();
    }
}
