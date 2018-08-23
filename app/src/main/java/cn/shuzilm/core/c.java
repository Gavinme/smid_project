/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package cn.shuzilm.core;

import android.content.Context;

class c implements Runnable {
    final /* synthetic */ Main a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ Listener e;

    c(Main main, Context context, String str, String str2, Listener listener) {
        this.a = main;
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = listener;
    }
@Override
    public void run() {
        try {
            String a = Main.instance.setCfgFromAssets(this.b, this.c, this.d);
            if (this.e != null) {
                this.e.handler(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
