/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package cn.shuzilm.core;

import android.content.Context;

class a implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    a(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    @Override
    public void run() {
        try {
            Main.setConfig("type", "0");
            Main.setConfig("apiKey", Main.g);
            Main.c.a(this.a, Main.j, this.b);
            Main.c.b(Main.i, this.c);
            Main.run(this.a, Main.j.toString(), Main.i.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
    }
}
