package com.example.jinsu.posters.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jinsu.posters.Model.MyUser;
import com.google.gson.Gson;

public class ChangeViewModel extends BaseObservable {
    private Context context;
    private MyUser user;


    public ChangeViewModel(@NonNull Context context)
    {
        this.context = context;
    }

    private void getUser()
    {

        SharedPreferences mPrefs = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User","");
        user = gson.fromJson(json,MyUser.class);
        Log.d("my_change",user.getCard_key());
    }

}
