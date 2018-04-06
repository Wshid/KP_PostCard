package com.example.jinsu.posters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinsu on 2018-03-25.
 */

public class ListFragment extends Fragment {
    public ListFragment()
    {

    }
    private LinearLayout list;
    private RelativeLayout relative;
    TextView total_money;
    Date today;
    View v;
    ViewGroup vg;
    set_list sl;
    String example = "[{'card_key' : '123456', 'list_place':'학식','list_cost':3000,'list_date':'2018-03-01'}," +
            "{'card_key' : '123456', 'list_place':'청운','list_cost':6000,'list_date':'2017-11-02'}," +
            "{'card_key' : '123456', 'list_place':'도스마스','list_cost':2500,'list_date':'2017-08-03'}," +
            "{'card_key' : '123456', 'list_place':'설쌈','list_cost':6500,'list_date':'2018-04-04'}]";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vg = container;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //날짜 포맷설정
        SimpleDateFormat change_dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"); //날짜 포맷설정
        long now = System.currentTimeMillis();
        today= new Date(now);
        DecimalFormat fm = new DecimalFormat("###,###");
        int total = 0;
        v = inflater.inflate(R.layout.fragment_list,container,false); //findbyid쓰기위해
        total_money = (TextView) v.findViewById(R.id.total_money);
        Button month_one = (Button) v.findViewById(R.id.month_one); //1개월
        Button month_three = (Button) v.findViewById(R.id.month_three); //3개월
        Button month_six = (Button)v.findViewById(R.id.month_six); //6개월
        Button month_self = (Button) v.findViewById(R.id.month_self); //직접설정
        sl=new set_list(0);

        month_one.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                sl=new set_list(1);
            }
        });
        month_three.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){

                sl=new set_list(3);
            }
        });
        month_six.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                sl=new set_list(6);

            }
        });
        month_self.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){

            }
        });

        return v;
    }
    public class set_list{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //날짜 포맷설정
        SimpleDateFormat change_dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"); //날짜 포맷설정
        long now = System.currentTimeMillis();
        Date today = new Date(now);
        DecimalFormat fm = new DecimalFormat("###,###");
        int total = 0;
        public set_list(int month_point){
            try {
                total=0;
                int count=0;
                JSONArray jarray = new JSONArray(example);   // JSONArray 생성
                list = (LinearLayout)v.findViewById(R.id.list_linearlayout); //결제내역뜨는 list
                list.removeAllViews();
                for (int i = 0; i < jarray.length(); i++) {
                    RelativeLayout use_layout = new RelativeLayout(getActivity());//결제내역 내 레이아웃
                    use_layout.setMinimumHeight(300);
                    use_layout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)); //레이아웃 크기
                    TextView use_place = new TextView(getActivity()); //결제장소 textview
                    TextView use_cost = new TextView(getActivity()); //결제비용 textview
                    TextView use_date = new TextView(getActivity()); //결제날짜 textview



                    RelativeLayout.LayoutParams date_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    date_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.CENTER_VERTICAL);
                    use_place.setLayoutParams(date_params);

                    RelativeLayout.LayoutParams place_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    place_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_TOP);
                    place_params.addRule(RelativeLayout.ABOVE,use_cost.getId());
                    place_params.addRule(RelativeLayout.RIGHT_OF,use_date.getId());
                    use_place.setLayoutParams(place_params);

                    RelativeLayout.LayoutParams cost_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    cost_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_BOTTOM);
                    cost_params.addRule(RelativeLayout.BELOW,use_place.getId());
                    cost_params.addRule(RelativeLayout.RIGHT_OF,use_date.getId());
                    cost_params.setMargins(0,100,0,0);
                    use_cost.setLayoutParams(cost_params);

                    JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                    Date list_date = dateFormat.parse(jObject.getString("list_date"));

                    if(month_point != 0 && (today.getTime() - list_date.getTime())/(1000*60*60*24)>=month_point*30)
                        continue;
                    use_layout.addView(use_date);   //layout에 각 항목 추가
                    use_layout.addView(use_cost);
                    use_layout.addView(use_place);
                    list.addView(use_layout);   // linear layout에 relative layout추가

                    String card_key = jObject.getString("card_key");
                    String list_place = jObject.getString("list_place");
                    int list_cost = jObject.getInt("list_cost");
                    use_place.setText(list_place); //결제내역에 뜰 String
                    use_cost.setText(""+list_cost);
                    use_date.setText(change_dateFormat.format(list_date));
                    total+=list_cost;
                }

                total_money.setText(fm.format(total));
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            catch(ParseException e){
                e.printStackTrace();
            }
        }
    }
}
