package com.example.pigdice;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TinyDB {
    private SharedPreferences preferences;

    public TinyDB(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    private String getString(String str) {
        return this.preferences.getString(str, "");
    }

    public ArrayList<Integer> getListInt(String str) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (String o : arrayList) {
            arrayList2.add(Integer.parseInt(o));
        }
        return arrayList2;
    }



    public long getLong(String str, long j) {
        return this.preferences.getLong(str, j);
    }

    public float getFloat(String str) {
        return this.preferences.getFloat(str, 0.0f);
    }

    public ArrayList<Double> getListDouble(String str) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Double> arrayList2 = new ArrayList<>();
        for (String o : arrayList) {
            arrayList2.add(Double.parseDouble(o));
        }
        return arrayList2;
    }

    public double getDouble(String str, double d) {
        try {
            return Double.parseDouble(getString(str));
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public int getInt(String str) {
        return this.preferences.getInt(str, 0);
    }
    public ArrayList<Long> getListLong(String str) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Long> arrayList2 = new ArrayList<>();
        for (String o : arrayList) {
            arrayList2.add(Long.parseLong(o));
        }
        return arrayList2;
    }



    public ArrayList<String> getListString(String str) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
    }

    public boolean getBoolean(String str) {
        return this.preferences.getBoolean(str, false);
    }
    public ArrayList<Boolean> getListBoolean(String str) {
        ArrayList<String> listString = getListString(str);
        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (String o : listString) {
            if ((o).equals("true")) {
                arrayList.add(Boolean.TRUE);
            } else {
                arrayList.add(Boolean.FALSE);
            }
        }
        return arrayList;
    }
    public void putInt(String str, int i) {
        checkForNullKey(str);
        this.preferences.edit().putInt(str, i).apply();
    }

    public void putListInt(String str, ArrayList<Integer> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Integer[]) arrayList.toArray(new Integer[0]))).apply();
    }

    public void putLong(String str, long j) {
        checkForNullKey(str);
        this.preferences.edit().putLong(str, j).apply();
    }

    public void putListLong(String str, ArrayList<Long> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Long[]) arrayList.toArray(new Long[0]))).apply();
    }

    public void putFloat(String str, float f) {
        checkForNullKey(str);
        this.preferences.edit().putFloat(str, f).apply();
    }

    public void putDouble(String str, double d) {
        checkForNullKey(str);
        putString(str, String.valueOf(d));
    }

    public void putListDouble(String str, ArrayList<Double> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Double[]) arrayList.toArray(new Double[0]))).apply();
    }

    public void putString(String str, String str2) {
        checkForNullKey(str);
        checkForNullValue(str2);
        this.preferences.edit().putString(str, str2).apply();
    }

    public void putListString(String str, ArrayList<String> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (String[]) arrayList.toArray(new String[0]))).apply();
    }

    public void putBoolean(String str, boolean z) {
        checkForNullKey(str);
        this.preferences.edit().putBoolean(str, z).apply();
    }

    public void putListBoolean(String str, ArrayList<Boolean> arrayList) {
        checkForNullKey(str);
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (Boolean aBoolean : arrayList) {
            if (aBoolean) {
                arrayList2.add("true");
            } else {
                arrayList2.add("false");
            }
        }
        putListString(str, arrayList2);
    }
    public void remove(String str) {
        this.preferences.edit().remove(str).apply();
    }

    public boolean deleteImage(String str) {
        return new File(str).delete();
    }

    public void clear() {
        this.preferences.edit().clear().apply();
    }

    public Map<String, ?> getAll() {
        return this.preferences.getAll();
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);

    }
    public void checkForNullKey(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
    }

    public void checkForNullValue(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
    }





}
