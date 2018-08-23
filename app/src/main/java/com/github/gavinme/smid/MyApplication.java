/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.github.gavinme.smid;

import android.app.Application;
import android.content.Context;
import cn.shuzilm.core.Main;

/**{@link Main#init(Context)}
 * Created by GanQuan on 2017/10/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Main.init(this);
    }

}
