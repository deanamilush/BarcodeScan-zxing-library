package com.example.grcodescan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    TextView tam_tv_format, tam_tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tam_tv_format = (TextView) findViewById(R.id.am_tv_format);
        tam_tv_content = (TextView) findViewById(R.id.am_tv_content);
    }
    public void scanNow(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
//        IntentIntegrator.initiateScan();
        integrator.initiateScan();

//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES );
//        integrator.setPrompt("Scan a Barcode");
//        integrator.setResultDisplayDuration(0);
//        integrator.setWide();
//        integrator.setCameraId(0);
//        integrator.initiateScan();

        //integrator.setScanningRectangle(10,30);
    }
    //   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null){
            if (result.getContents() == null) {
                Toast.makeText(this, "Hasil Tidak Ketemu", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    JSONObject object = new JSONObject(result.getContents());
                    tam_tv_format.setText(object.getString("format"));
                    tam_tv_content.setText(object.getString("content"));
                }catch (JSONException e) {
                    e.printStackTrace();
                    tam_tv_content.setText(result.getContents());
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
//                try {
//                    JSONObject object = new JSONObject(result.getContents());
//                    tam_tv_format.setText(object.getString("format"));
//                    tam_tv_content.setText(object.getString("content"));
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
//                }


            }
        }else{
            super.onActivityResult(requestCode, resultCode, intent);
        }

    }
}