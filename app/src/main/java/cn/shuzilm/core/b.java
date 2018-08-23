/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package cn.shuzilm.core;

import android.content.Context;

class b implements Runnable {
    final /* synthetic */ Main a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ Listener f;

    b(Main main, Context context, String str, String str2, String str3, Listener listener) {
        this.a = main;
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = listener;
    }
@Override
    public void run() {
        try {
            String a = Main.instance.a(this.b, this.c, this.d, this.e);
            if (this.f != null) {
                this.f.handler(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
