package com.itcast.bmwhome;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveUser {
    //保存QQ账号与密码到data.xml
    public static boolean saveUserInfo(Context context, String account, String password){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= sp.edit();
        edit.putString("userName",account);
        edit.putString("pwd",password);
        edit.commit();
        return true;
    }
    //从data.xml中获取储存的QQ账号与密码
    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp =context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String account = sp.getString("userName",null);
        String password = sp.getString("pwd",null);
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("account",account);
        userMap.put("password",password);
        return userMap;
    }
}
