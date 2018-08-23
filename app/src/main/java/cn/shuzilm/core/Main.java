/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package cn.shuzilm.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class Main extends PhoneStateListener {
    private static final String a = "du";
    private static final boolean b = true;
    protected static final Main c = new Main();
    private static final Lock d = new ReentrantLock();
    private static final ReentrantReadWriteLock e = new ReentrantReadWriteLock();
    static String g = null;
    private static String h = null;
    static final JSONObject i = new JSONObject();
    static final JSONObject j = new JSONObject();
    private static JSONObject k = null;
    private static final ThreadLocal l = new ThreadLocal();
    private static Context m = null;
    private static String n = null;
    private static JSONObject o = new JSONObject();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private boolean f = false;
    private static final String SHU_ZI_LM_KEY =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMUBVv"
                    + "+BdK8bzgV8iTEe25zWhQabmsC8RCo4TAMW79i6ReUymlcmAvTjxq5pxKFyfvRmdsdOL9RDEQlB+6Z/nP8CAwEAAQ==";

    private Main() {
    }

    private String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    open.close();
                    return stringBuffer.toString();
                }
                stringBuffer.append(readLine);
            }
        } catch (IOException e) {
            return null;
        }
    }

    String a(Context context, String str, String str2, String str3) {
        String jSONObject;
        Throwable th;
        Exception exception;
        Exception exception2;
        UnsatisfiedLinkError unsatisfiedLinkError;
        UnsatisfiedLinkError unsatisfiedLinkError2;
        String str4 = null;
        a(context, str, 0);
        try {
            a("type", "2");
            a("pEventCode", str);
            a("apiKey", g);
            if (str2 != null) {
                a("mEventCode", str2);
            }
            jSONObject = l.get() != null ? ((JSONObject) l.get()).toString() : null;
            synchronized(o) {
                try {
                    String jSONObject2 = o.toString();
                    jSONObject = onEvent(context, jSONObject, null, jSONObject2);
                    if (jSONObject != null) {
                        try {
                            a(o, jSONObject2);
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            str4 = jSONObject;
                            th = th3;
                            try {
                                throw th;
                            } catch (Exception e) {
                                exception = e;
                                jSONObject = str4;
                                exception2 = exception;
                            } catch (UnsatisfiedLinkError e2) {
                                unsatisfiedLinkError = e2;
                                jSONObject = str4;
                                unsatisfiedLinkError2 = unsatisfiedLinkError;
                                unsatisfiedLinkError2.printStackTrace();
                                return jSONObject;
                            }
                        }
                    }
                } catch (Throwable th4) {
                }
            }
        } catch (Exception e3) {
            exception = e3;
            jSONObject = null;
            exception2 = exception;
            exception2.printStackTrace();
            return jSONObject;
        } catch (UnsatisfiedLinkError e22) {
            unsatisfiedLinkError = e22;
            jSONObject = null;
            unsatisfiedLinkError2 = unsatisfiedLinkError;
            unsatisfiedLinkError2.printStackTrace();
            return jSONObject;
        }
        return null;
    }

    private void a(Context context, SensorManager sensorManager, Sensor sensor) {
        sensorManager.registerListener(new d(this, context, sensorManager), sensor, 1);
    }

    private void a(Context context, String str, int i) {
        synchronized(o) {
            JSONObject jSONObject = o;
            try {
                if (!jSONObject.has(str)) {
                    jSONObject.put(str, 0);
                }
                jSONObject.put(str, jSONObject.getInt(str) + 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(Context context, String str, String str2, Listener listener) {
        EXECUTOR_SERVICE.execute(new c(this, context, str, str2, listener));
    }

    private void a(Context context, String str, String str2, String str3, Listener listener) {
        EXECUTOR_SERVICE.execute(new b(this, context, str, str2, str3, listener));
    }

    public void a(Context context, JSONObject jSONObject, String str) throws JSONException {
        String str2;
        if (jSONObject.isNull("url")) {
            str2 = (String) b(context, "url");
            if (str2 != null) {
                jSONObject.put("url", str2);
            }
        }
        if (jSONObject.isNull("store")) {
            Object d = null;
            if (str == null) {
                d = d(context);
                if (d == null) {
                    d = (String) b(context, "store");
                }
            } else {
                str2 = str;
            }
            if (d != null) {
                jSONObject.put("store", d);
            }
        }
        if (jSONObject.isNull("apiKey")) {
            str2 = (String) b(context, "apiKey");
            if (str2 != null) {
                jSONObject.put("apiKey", str2);
            }
        }
    }

    private void a(String str, String str2) {
        try {
            JSONObject jSONObject = (JSONObject) l.get();
            if (jSONObject != null) {
                jSONObject.put(str, str2);
                return;
            }
            jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            l.set(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, String str) {
        try {
            Iterator keys = new JSONObject(str).keys();
            while (keys.hasNext()) {
                jSONObject.remove((String) keys.next());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        jSONObject.put(str, str2);
    }

    private boolean a(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()
                    && activeNetworkInfo.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    private Object b(Context context, String str) {
        Object obj = null;
        try {
            JSONObject jSONObject = k;
            if (jSONObject == null) {
                jSONObject = c(context);
                k = jSONObject;
            }
            obj = jSONObject.opt(str);
        } catch (Exception e) {
        }
        return obj;
    }

    private String b(Context context) {
        return a(context, "20171111_yy.jpg");
    }

    String b(Context context, String str, String str2) {
        try {
            setConfig("apiKey", g);
            a(context, j, str);
            b(i, str2);
            return query(context, /**apk key*/j.toString(), i.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void b(JSONObject jSONObject, String str) throws JSONException {
        a(jSONObject, "custom", str);
    }

    private String c(Context context, String str) {
        try {
            Object obj = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA)
                    .metaData.get(str);
            if (obj != null) {
                return obj.toString();
            }
        } catch (Exception e) {
        }
        return null;
    }

    private JSONObject c(Context context) throws JSONException {
        String a = a(context, "cn.shuzilm.config.json");
        return a != null ? new JSONObject(a) : null;
    }

    private String d(Context context) {
        String str = null;
        try {
            Object b = c.b(context, "store");
            if (!(b instanceof String)) {
                str = c.c(context, new JSONObject(b.toString()).getJSONObject("metadata").getString("name"));
            }
        } catch (Exception e) {
        }
        return str;
    }

    private String e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_dna", 0);
        return sharedPreferences != null ? sharedPreferences.getString("device_id", null) : null;
    }

    private void f(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor defaultSensor = sensorManager.getDefaultSensor(9);
        if (defaultSensor != null) {
            a(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(3);
        if (defaultSensor != null) {
            a(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(11);
        if (defaultSensor != null) {
            a(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(19);
        if (defaultSensor != null) {
            a(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(6);
        if (defaultSensor != null) {
            a(context, sensorManager, defaultSensor);
        }
    }

    /**
     * 获取设备的唯一id
     *
     * @param context
     *
     * @return
     */
    public static String getQueryID(Context context) {
        return (String) getQueryID(context, null, null, 0, null).get("device_id");
    }

    public static String getQueryID(Context context, String str, String str2) {
        return (String) getQueryID(context, str, str2, 0, null).get("device_id");
    }

    public static Map getQueryID(Context context, String str, String str2, int i, Listener listener) {
        HashMap hashMap = new HashMap();
        Object obj = "0";
        Context applicationContext = context.getApplicationContext();
        if (i == 1) {
            c.a(applicationContext, str, str2, listener);
            return null;
        } else if (d.tryLock()) {
            String b = c.b(applicationContext, str, str2);
            if (b != null) {
                n = b;
                obj = "1";
            }
            if (b == null) {
                b = n != null ? n : c.e(applicationContext);
            }
            hashMap.put("device_id", b);
            hashMap.put("valid", obj);
            d.unlock();
            return hashMap;
        } else {
            String e = c.e(applicationContext);
            if (e == null) {
                c.a(applicationContext, str, str2, listener);
            }
            hashMap.put("device_id", e);
            hashMap.put("valid", obj);
            return hashMap;
        }
    }

    /**
     * 需要在主进程中调用
     *
     * @param context
     */
    public static void init(Context context) {
        m = context.getApplicationContext();
        try {
            g = SHU_ZI_LM_KEY;
            System.loadLibrary(a);
            ((TelephonyManager) m.getSystemService(Context.TELEPHONY_SERVICE)).listen(c, 256);
            String b = c.b(context);
            if (b != null) {
                h = b;
                setConfig("inner", b);
            }
            c.f(context);
            c.f = true;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
    }

    public static Map onEvent(Context context, String str, String str2, String str3, int i, Listener listener) {
        Map hashMap = new HashMap();
        if (str == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        if (i == 1) {
            c.a(applicationContext, str, str2, str3, listener);
            return null;
        } else if (d.tryLock()) {
            hashMap.put("SessionID", c.a(applicationContext, str, str2, str3));
            hashMap.put("QueryID", n);
            d.unlock();
            return hashMap;
        } else {
            c.a(applicationContext, str, str2, str3, listener);
            return null;
        }
    }

    public static int setConfig(String str, String str2) throws JSONException {
        c.a(j, str, str2);
        return 0;
    }

    public static int setData(String str, String str2) throws JSONException {
        c.a(i, str, str2);
        return 0;
    }

    public static int unResListener() {
        ((TelephonyManager) m.getSystemService(Context.TELEPHONY_SERVICE)).listen(c, 0);
        return 0;
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            onSSChanged(m, signalStrength);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static native String onEvent(Context context, String str, String str2, String str3);

    public static native void onSSChanged(Context context, SignalStrength signalStrength);

    public static native void onSensorChanged(Context context, SensorEvent sensorEvent);

    public static native String query(Context context, String str, String str2);

    public static native String run(Context context, String str, String str2);
}
