/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package cn.shuzilm.core;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

class d implements SensorEventListener {
    final /* synthetic */ Main a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ SensorManager c;

    d(Main main, Context context, SensorManager sensorManager) {
        this.a = main;
        this.b = context;
        this.c = sensorManager;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            Main.onSensorChanged(this.b, sensorEvent);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        this.c.unregisterListener(this);
    }
}
