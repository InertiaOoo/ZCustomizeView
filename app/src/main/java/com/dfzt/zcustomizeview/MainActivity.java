package com.dfzt.zcustomizeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dfzt.zcustomizeview.bottom_navi.ZBottomNavi;

public class MainActivity extends AppCompatActivity {

    private ZBottomNavi zBottomNavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zBottomNavi = findViewById(R.id.zBottomNavi);
        zBottomNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG,","onClick");
            }
        });
    }
}
