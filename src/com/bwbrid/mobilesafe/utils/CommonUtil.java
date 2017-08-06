package com.bwbrid.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.util.AttributeSet;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.activity.BaseActivity;
import com.bwbrid.mobilesafe.common.CommonConstant;

public class CommonUtil {

    public enum Action {
        PreActivity, NextActivity
    }

    /**
     * カスタマイズVIEWの属性名から属性値を取得
     * 
     * @param attrs VIEWの属性定義
     * @param attribute 属性名
     * @return 取得後の属性値
     */
    public static String getStringByLayoutXmlAttr(AttributeSet attrs, String attribute) {

        int resId = attrs.getAttributeResourceValue(CommonConstant.NAME_SPACE, attribute, 0);
        String result = null;

        if (resId != 0) {
            result = MyApplication.getContext().getResources().getString(resId);
        } else {
            result = attrs.getAttributeValue(CommonConstant.NAME_SPACE, attribute);
        }
        return result;
    }

    /**
     * 文字列暗号化
     * 
     * @param password 文字列
     * @return 暗号化後文字列
     */
    public static String encoder(String password) {

        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(getSalt(password).getBytes());
            for (byte b : bs) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = zeroPadding(hexString, 2);
                }
                sb.append(hexString);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * zero padding
     * 
     * @param target 対象文字列
     * @param paddingLength パディング後の文字長
     * @return 処理後文字列
     */
    public static String zeroPadding(String target, int paddingLength) {
        if (target.length() >= paddingLength) {
            return target;
        }

        StringBuffer sb = new StringBuffer();
        int length = paddingLength - target.length();
        for (int i = 0; i < length; i++) {
            sb.append(CommonConstant.STR_ZERO);
        }

        sb.append(target);
        return sb.toString();
    }

    public static String getSalt(String str) {
        return CommonConstant.MD5_SALT + str;
    }

    /**
     * activity遷移
     * 
     * @param activity 対象アクティビティ
     * @param activityClass 遷移先アクティビティ
     * @param type 遷移タイプ （GotoNextActivity | GotoPreActivity）
     */
    public static void gotoActivity(BaseActivity activity, Class<?> activityClass, Action type) {

        Intent intent = new Intent(MyApplication.getContext(), activityClass);
        activity.startActivity(intent);
        activity.finish();

        switch (type) {
            case NextActivity:
                activity.overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
                break;
            case PreActivity:
                activity.overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
                break;
            default:
        }
    }

    /**
     * activity遷移
     * 
     * @param activity 対象アクティビティ
     * @param activityClass 遷移先アクティビティ
     * @param isFinish 遷移後アクティビティが終了するかどうか
     */
    public static void gotoNextActivity(BaseActivity activity, Class<?> activityClass,
            boolean isFinish) {

        Intent intent = new Intent(MyApplication.getContext(), activityClass);
        activity.startActivity(intent);

        if (isFinish) {
            activity.finish();
        }

        activity.overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
    }
}
