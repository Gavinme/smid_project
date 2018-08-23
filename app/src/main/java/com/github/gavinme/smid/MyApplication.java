/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.github.gavinme.smid;

import android.app.Application;
import cn.shuzilm.core.Main;

/**
 * Created by GanQuan on 2017/10/12.
 */

public class MyApplication extends Application {
    private static final String SHU_ZI_LM_KEY =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMUBVv"
                    + "+BdK8bzgV8iTEe25zWhQabmsC8RCo4TAMW79i6ReUymlcmAvTjxq5pxKFyfvRmdsdOL9RDEQlB+6Z/nP8CAwEAAQ==";

    @Override
    public void onCreate() {
        super.onCreate();
        Main.init(this, SHU_ZI_LM_KEY);
    }

}
