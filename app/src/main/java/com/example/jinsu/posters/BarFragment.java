package com.example.jinsu.posters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by jinsu on 2018-03-25.
 */

public class BarFragment extends Fragment {
    public BarFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("my_bar","bar create@@@@@");


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bar, container, false);
        IntentIntegrator integrator = new IntentIntegrator(super.getActivity());
        integrator.setCaptureActivity(ActivityScanner.class);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
        Log.d("my_bar","bar create view@@");
        return layout;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // 결과값 출력
        new AlertDialog.Builder(super.getContext())
                .setTitle(R.string.app_name)
                .setMessage(result.getContents() + " [" + result.getFormatName() + "]")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }
}
