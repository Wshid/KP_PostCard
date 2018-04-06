package com.example.jinsu.posters;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.journeyapps.barcodescanner.CaptureActivity;

/**
 * Created by User on 2016-09-14.
 */
public class ActivityScanner extends CaptureActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 이거하면, 카메라화면이 안보임
        //setContentView(R.layout.barcode_scan_activity);

        // 상단 타이틀 뷰
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       // barcode_scan_title_view title_view = new barcode_scan_title_view(this);
        //this.addContentView(title_view, layoutParams);
    }
}
