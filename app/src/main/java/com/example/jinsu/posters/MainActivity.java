package com.example.jinsu.posters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jinsu.posters.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView navi;
    private TextView mTextMessage;
    private ViewPager page;
    private PageAdapter adapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //권한 획득
        //접근 거절 상태일 경우
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            //사용자가 이전에 거절한 이력이 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA)) {

            }
            //사용자가 다시 보지 않기에 체크하고 거절한 이력이 있는 경우
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMain(this);
        mTextMessage = (TextView) findViewById(R.id.message);

        adapter = new PageAdapter(getSupportFragmentManager());

        //binding.page.setAdapter(new PageAdapter(getSupportFragmentManager()));
        binding.page.setAdapter(adapter);

//        binding.page.setCurrentItem(0);

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi_home:
                        binding.page.setCurrentItem(0);
                        return true;
                    case R.id.navi_send:
                        binding.page.setCurrentItem(1);
                        return true;
                    case R.id.navi_barcode:
                        binding.page.setCurrentItem(2);
                        return true;
                    case R.id.navi_bonus:
                        binding.page.setCurrentItem(3);
                        return true;
                    case R.id.navi_list:
                        binding.page.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });

        //페이지 변할 때 콜백메소드
        binding.page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        binding.navigation.setSelectedItemId(R.id.navi_home);
                        break;
                    case 1:
                        binding.navigation.setSelectedItemId(R.id.navi_send);
                        break;
                    case 2:
                        binding.navigation.setSelectedItemId(R.id.navi_barcode);
                        break;
                    case 3:
                        binding.navigation.setSelectedItemId(R.id.navi_bonus);
                        break;
                    case 4:
                        binding.navigation.setSelectedItemId(R.id.navi_list);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        BottomNavigationViewHelper.disableShiftMode(binding.navigation);
    }








}
