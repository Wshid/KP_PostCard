package com.example.jinsu.posters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DesignActivity extends AppCompatActivity implements ColorPickerDialogListener{
    ImageView bgcard;
    private Uri myphoto;
    private FrameLayout layout_card;
    private Button complete_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        TextView Color = (TextView) findViewById(R.id.Color_change);
        TextView photo = (TextView) findViewById(R.id.photo);
        layout_card = (FrameLayout)findViewById(R.id.layout_card);
        bgcard = (ImageView) findViewById(R.id.card_background);
        complete_btn = (Button)findViewById(R.id.btn_complete);

        final Activity design = this;
        Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog.newBuilder().setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowPresets(false)
                        .setDialogId(1)
                        .setColor(android.graphics.Color.WHITE)
                        .setShowAlphaSlider(false)
                        .show(design);

            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_card.buildDrawingCache();
                Bitmap bit = layout_card.getDrawingCache();
                File f = new File(Environment.getExternalStorageDirectory()+"/card.png");
                OutputStream out = null;
                try {
                    f.createNewFile();//파일생성
                    out = new FileOutputStream(f);
                    bit.compress(Bitmap.CompressFormat.PNG, 100, out);
                    /*//이미지 바로 갤러리에 올리는 작업
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(f);
                    mediaScanIntent.setData(contentUri);
                    sendBroadcast(mediaScanIntent);*/
                    //bitmap = 갤러리또는 리소스에서 불러온 비트맵 파일에 포맷
                    Log.e("yapower","이미지 성공");
                    Toast.makeText(getApplicationContext(), "download ok", Toast.LENGTH_SHORT).show();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("yapower","이미지 실패");

                }
                finish();
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 1: {
                myphoto = data.getData();
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(myphoto, "image/*");
                intent.putExtra("outputX", 310);
                intent.putExtra("outputY", 200);
                intent.putExtra("aspectX", 310);
                intent.putExtra("aspectY", 200);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                intent.putExtra("noFaceDetection", true);
                startActivityForResult(intent, 2);
                break;
            }
            case 2: {
                final Bundle extras = data.getExtras();
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PostersCardImage/" + System.currentTimeMillis() + ".jpg";

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    bgcard.setImageBitmap(photo);
                    break;
                }

            }
        }
    }


    public void onColorSelected(int dialogId, final int color){
        bgcard.setImageResource(android.R.color.transparent);
        final int invertColor = ~color;
        final String hexColor = String.format("%X",color);
        String hexInverterColor = String.format("%X",invertColor);
        if(BuildConfig.DEBUG){
    //        Toast.makeText(this,"id "+dialogId+" c: "+hexColor+" i:"+hexInverterColor, Toast.LENGTH_SHORT).show();
        }
        bgcard.setBackgroundColor(color);

    }
    public void onDialogDismissed(int dialogId){

    }

}
