package com.github.gavinme.smid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.shuzilm.core.Main;

public class MainActivity extends Activity {
    TextView tv_smid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_smid = findViewById(R.id.tv_smid);
        tv_smid.setText(Main.query(this, null, null));
    }

}
