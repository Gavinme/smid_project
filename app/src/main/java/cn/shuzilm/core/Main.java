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
    private static final String soLib = "du";
    protected static final Main instance = new Main();
    private static final Lock LOCK = new ReentrantLock();
    private static final ReentrantReadWriteLock e = new ReentrantReadWriteLock();
    static String apiKeyValue = null;
    static final JSONObject customJob = new JSONObject();
    static final JSONObject keyJob = new JSONObject();
    private static JSONObject k = null;
    private static final ThreadLocal sThreadLocal = new ThreadLocal();
    private static Context mContext = null;
    private static String n = null;
    private static JSONObject o = new JSONObject();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private static final String SHU_ZI_LM_KEY =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMUBVv"
                    + "+BdK8bzgV8iTEe25zWhQabmsC8RCo4TAMW79i6ReUymlcmAvTjxq5pxKFyfvRmdsdOL9RDEQlB+6Z/nP8CAwEAAQ==";

    private Main() {
    }

    private String readAssets(Context context, String str) {
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
            a("apiKey", apiKeyValue);
            if (str2 != null) {
                a("mEventCode", str2);
            }
            jSONObject = sThreadLocal.get() != null ? ((JSONObject) sThreadLocal.get()).toString() : null;
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

    private void registerSensorListener(Context context, SensorManager sensorManager, Sensor sensor) {
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
            str2 = (String) setCfgFromAssets(context, "url");
            if (str2 != null) {
                jSONObject.put("url", str2);
            }
        }
        if (jSONObject.isNull("apiKey")) {
            str2 = (String) setCfgFromAssets(context, "apiKey");
            if (str2 != null) {
                jSONObject.put("apiKey", str2);
            }
        }
    }

    private void a(String str, String str2) {
        try {
            JSONObject jSONObject = (JSONObject) sThreadLocal.get();
            if (jSONObject != null) {
                jSONObject.put(str, str2);
                return;
            }
            jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            sThreadLocal.set(jSONObject);
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

    private void setConfig(JSONObject jSONObject, String str, String str2) throws JSONException {
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

    private Object setCfgFromAssets(Context context, String str) {
        Object obj = null;
        try {
            JSONObject jSONObject = k;
            if (jSONObject == null) {
                jSONObject = readAssetsJson(context);
                k = jSONObject;
            }
            obj = jSONObject.opt(str);
        } catch (Exception e) {
        }
        return obj;
    }

    private String readAssets(Context context) {
        return readAssets(context, "20171111_yy.jpg");
    }

    String setCfgFromAssets(Context context, String str, String str2) {
        try {
            setConfig("apiKey", apiKeyValue);//存储key到keyJob
            a(context, keyJob, str);
            setCustomConfig(customJob, str2);
            return query(context, /**apk key*/keyJob.toString(), customJob.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void setCustomConfig(JSONObject jSONObject, String str) throws JSONException {
        setConfig(jSONObject, "custom", str);
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

    private JSONObject readAssetsJson(Context context) throws JSONException {
        String a = readAssets(context, "cn.shuzilm.config.json");
        return a != null ? new JSONObject(a) : null;
    }

    private String d(Context context) {
        String str = null;
        try {
            Object b = instance.setCfgFromAssets(context, "store");
            if (!(b instanceof String)) {
                str = instance.c(context, new JSONObject(b.toString()).getJSONObject("metadata").getString("name"));
            }
        } catch (Exception e) {
        }
        return str;
    }

    private String e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_dna", 0);
        return sharedPreferences != null ? sharedPreferences.getString("device_id", null) : null;
    }

    private void register(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (defaultSensor != null) {
            registerSensorListener(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(3);
        if (defaultSensor != null) {
            registerSensorListener(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(11);
        if (defaultSensor != null) {
            registerSensorListener(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(19);
        if (defaultSensor != null) {
            registerSensorListener(context, sensorManager, defaultSensor);
        }
        defaultSensor = sensorManager.getDefaultSensor(6);
        if (defaultSensor != null) {
            registerSensorListener(context, sensorManager, defaultSensor);
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
            instance.a(applicationContext, str, str2, listener);
            return null;
        } else if (LOCK.tryLock()) {
            String b = instance.setCfgFromAssets(applicationContext, str, str2);
            if (b != null) {
                n = b;
                obj = "1";
            }
            if (b == null) {
                b = n != null ? n : instance.e(applicationContext);
            }
            hashMap.put("device_id", b);
            hashMap.put("valid", obj);
            LOCK.unlock();
            return hashMap;
        } else {
            String e = instance.e(applicationContext);
            if (e == null) {
                instance.a(applicationContext, str, str2, listener);
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
        mContext = context.getApplicationContext();
        try {
            apiKeyValue = SHU_ZI_LM_KEY;
            System.loadLibrary(soLib);
            ((TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE)).listen(instance, LISTEN_SIGNAL_STRENGTHS);
            instance.register(context);
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
            instance.a(applicationContext, str, str2, str3, listener);
            return null;
        } else if (LOCK.tryLock()) {
            hashMap.put("SessionID", instance.a(applicationContext, str, str2, str3));
            hashMap.put("QueryID", n);
            LOCK.unlock();
            return hashMap;
        } else {
            instance.a(applicationContext, str, str2, str3, listener);
            return null;
        }
    }

    public static int setConfig(String str, String str2) throws JSONException {
        instance.setConfig(keyJob, str, str2);
        return 0;
    }

    public static int setData(String str, String str2) throws JSONException {
        instance.setConfig(customJob, str, str2);
        return 0;
    }

    public static int unResListener() {
        ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).listen(instance, 0);
        return 0;
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            onSSChanged(mContext, signalStrength);
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
