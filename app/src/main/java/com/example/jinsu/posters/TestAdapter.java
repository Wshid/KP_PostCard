package com.example.jinsu.posters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by miyon on 2018-04-05.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_A = 0;
    public static final int VIEW_TYPE_B = 1;

    //private ArrayList<Test> mItems;
    // private ArrayList<MyData2> mDataset;
    private ArrayList<Object> mDataset;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_A) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_view, parent, false);
            return new AHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_view, parent, false);

            return new BHolder(v);
        }
    }



    public TestAdapter(ArrayList<Object> myDataset) {
        mDataset=myDataset;
    }


    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position) instanceof MyData2) {
            return VIEW_TYPE_A;
        } else {
            return VIEW_TYPE_B;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // item 넣어주고, 사용자가 스크롤링할때 호출되는 메소드
        // MyData에 있는 것들을 알맞게 넣어준다
        //Test test = mItems.get(position);



        if (holder instanceof AHolder) {
            MyData2 my = (MyData2)mDataset.get(position);
            ((AHolder) holder).mTextView.setText(my.text);
            ((AHolder) holder).mTextView2.setText(my.text2);
            ((AHolder) holder).mTextView3.setText(my.title);
            ((AHolder) holder).mImageView.setImageResource(my.img);
        } else {
            MyData3 my2 = (MyData3)mDataset.get(position);
            ((BHolder) holder).title.setText(my2.title);
            ((BHolder) holder).hint.setText(my2.hint);
            ((BHolder) holder).txt1.setText(my2.num1);
            ((BHolder) holder).txt2.setText(my2.num2);
            ((BHolder) holder).txt3.setText(my2.num3);
            ((BHolder) holder).im1.setImageResource(my2.im1);
            ((BHolder) holder).im2.setImageResource(my2.im2);
            ((BHolder) holder).im3.setImageResource(my2.im3);
            PieDataSet dataSet = new PieDataSet(my2.yValues,"");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            if(position == 0) {
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            }
            else
            {
                dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
            }

            PieData data = new PieData((dataSet));
            data.setValueTextSize(10f);
            //data.setDrawValues(false);
            data.setValueTextColor(Color.BLACK);

            Legend legend = ((BHolder) holder).pieChart.getLegend();
            legend.setEnabled(false);

            ((BHolder) holder).pieChart.setData(data);


        }
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class AHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;
        public TextView mTextView2;
        public TextView mTextView3;

        public AHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.imageview);
            mTextView = (TextView) view.findViewById(R.id.textview);
            mTextView2 = (TextView) view.findViewById(R.id.textview2);
            mTextView3 = (TextView) view.findViewById(R.id.title);
        }
    }

    public class BHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView hint;
        TextView txt1;
        TextView txt2;
        TextView txt3;
        PieChart pieChart;
        ImageView im1;
        ImageView im2;
        ImageView im3;

        public BHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.charttitle);
            hint = (TextView) view.findViewById(R.id.charthint);
            txt1 = (TextView) view.findViewById(R.id.chart1a1);
            txt2 = (TextView) view.findViewById(R.id.chart1a2);
            txt3 = (TextView) view.findViewById(R.id.chart1a3);
            im1 = (ImageView)view.findViewById(R.id.im1);
            im2 = (ImageView)view.findViewById(R.id.im2);
            im3 = (ImageView)view.findViewById(R.id.im3);

            pieChart = (PieChart)view.findViewById(R.id.piechart);

            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5,10,5,5);

            pieChart.setDragDecelerationFrictionCoef(0.95f);

            pieChart.setDrawHoleEnabled(false);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setTransparentCircleRadius(61f);
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);

            pieChart.setTransparentCircleColor(Color.WHITE);
            pieChart.setTransparentCircleAlpha(110);

            pieChart.setHoleRadius(58f);
            pieChart.setTransparentCircleRadius(61f);

            pieChart.setDrawCenterText(true);
            pieChart.setCenterTextSize(15);
            pieChart.setCenterText("총금액");//여기 sum한거 넣어야지

            pieChart.setRotationAngle(0);
            // enable rotation of the chart by touch
            pieChart.setRotationEnabled(true);
            pieChart.setHighlightPerTapEnabled(true);


            //yValues.add(new PieEntry(40f));
            //yValues.add(new PieEntry(40f));

       /* Description description = new Description();
        description.setText("세계 국가"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);
*/
            pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션
            ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

            yValues.add(new PieEntry(23f)); //여기 순위대로 파싱받아서 넣기
            yValues.add(new PieEntry(14f));
            yValues.add(new PieEntry(35f));





        }
    }


}

class MyData2{
    //우리가 넣어줄 데이터들 저장된 곳
    public String text;
    public String text2;
    public String title;
    public int img;
    private int itemViewType;

    public int getItemViewType() {
        return itemViewType;
    }

    public MyData2(String text, String text2, String title, int img, int itemViewType) {
        this.text = text;
        this.text2 = text2;
        this.title = title;
        this.img = img;
        this.itemViewType = itemViewType;
    }
}

class MyData3
{
    public String title;
    public String num1;
    public String num2;
    public String num3;
    public String hint;
    private int itemViewType;
    ArrayList<PieEntry> yValues;
    public int im1;
    public int im2;
    public int im3;

    public MyData3(String title, String num1, String num2, String num3, String hint,
                   ArrayList<PieEntry> yValues, int im1, int im2, int im3) {
        this.title = title;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.hint = hint;
        this.yValues = yValues;
        this.im1 = im1;
        this.im2 = im2;
        this.im3 = im3;

    }
}
