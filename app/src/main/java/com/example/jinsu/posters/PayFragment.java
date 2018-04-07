package com.example.jinsu.posters;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.text.DecimalFormat;

/**
 * Created by jinsu on 2018-03-25.
 */

public class PayFragment extends android.support.v4.app.Fragment {
    String result;
    public PayFragment()
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
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_pay, container, false);
        result="";
        final EditText send_money = (EditText) layout.findViewById(R.id.send_money);
        EditText send_account = (EditText) layout.findViewById(R.id.account);
        Spinner bankSpinner = (Spinner) layout.findViewById(R.id.spinner);
        ArrayAdapter bankAdapter = ArrayAdapter.createFromResource(super.getActivity(),R.array.bank_name,android.R.layout.simple_spinner_item);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankSpinner.setAdapter(bankAdapter);

        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        send_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s.toString()) && !s.toString().equals(result)){
                    result = decimalFormat.format(Long.parseLong(s.toString().replaceAll(",","")));
                    send_money.setText(result);
                    send_money.setSelection(result.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return layout;
    }
    }



