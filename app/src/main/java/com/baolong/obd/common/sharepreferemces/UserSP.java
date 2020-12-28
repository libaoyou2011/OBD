package com.baolong.obd.common.sharepreferemces;


import android.content.SharedPreferences;

import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.Utils;

import static android.content.Context.MODE_PRIVATE;

public class UserSP {
    private static final String USER_SERVER = "server";
    private static final String USER_SP_KEY_CURRENT_CAR = "current_car";
    private static final String USER_SP_KEY_CURRENT_CAR_BRAND = "current_car_brand";
    private static final String USER_SP_KEY_CURRENT_CAR_TYPE = "current_car_type";
    private static final String USER_SP_KEY_CURRENT_SERVER_URL = "current_server_url";
    private static final String USER_SP_KEY_HEAD = "head";
    private static final String USER_SP_KEY_ID = "id";
    private static final String USER_SP_KEY_ISLOGIN = "isLogin";
    private static final String USER_SP_KEY_NAME = "name";
    private static final String USER_SP_KEY_NIKE_NAME = "nick_name";
    private static final String USER_SP_KEY_PWD = "pwd";
    private static final String USER_SP_KEY_TEL = "tel";
    private static final String USER_SP_KEY_TOKEN = "token";
    private static final String USER_SP_NAME = "user";
    private static final String USER_SP_ORG_ID = "organizationId";
    private static final String USER_SP_MAP_ZOOM = "map_zoom";

    public static void clearData() {
        /*SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE);
//        String string = sharedPreferences.getString("tel", (String) null);
        String userName = sharedPreferences.getString("name", (String) null);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
//        edit.putString("tel", string);
        edit.putString("name", userName);
        edit.commit();*/

        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("pwd", null);
        localEditor.commit();
    }

    public static String getUserCar() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("current_car", null);
    }

    public static String getUserCarBrand() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("current_car_brand", null);
    }

    public static String getUserCarType() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("current_car_type", null);
    }

    public static String getUserHead() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("head", null);
    }

    public static String getUserId() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("id", null);
    }

    public static String getUserName() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("name", null);
    }

    public static String getUserNikeName() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("nick_name", null);
    }

    public static String getUserPhone() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("tel", null);
    }

    public static String getUserPwd() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("pwd", null);
    }

    public static String getUserServerUrl() {
        return BaseApplication.getInstance().getSharedPreferences("server", MODE_PRIVATE).getString("current_server_url", null);
    }

    public static String getUserToken() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("token", null);
    }

    public static String getStationLocalHost() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("station_local_host", (String)null);
    }

    public static String getAddressCenterLat() {
        //return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("address_center_lat", (String)"31.8684804238");
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).getString("address_center_lat", (String)"32.8684804238");

    }

    public static String getAddressCenterLng() {
        //return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getString("address_center_lng", (String)"117.1766054997");
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getString("address_center_lng", (String)"103.1766054997");

    }

    public static String getMapZoom() {
        //return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getString(USER_SP_MAP_ZOOM, (String)"11");
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getString(USER_SP_MAP_ZOOM, (String)"4");
    }

    public static String getCookie() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getString("cookie", (String)"");
    }

    public static boolean getSendMessage() {
        return BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE ).getBoolean("sendMessage", false);
    }

    public static void setUser(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("name", paramString1).putString("id", paramString2).putString("organizationId", paramString3).putString("token", paramString4).putString("tel", paramString5);
        localEditor.commit();
    }

    public static void setUserCar(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("current_car", paramString);
        localEditor.commit();
    }

    public static void setUserCarBrand(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("current_car_brand", paramString);
        localEditor.commit();
    }

    public static void setUserCarType(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("current_car_type", paramString);
        localEditor.commit();
    }

    public static void setUserID(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("id", paramString);
        localEditor.commit();
    }

    public static void setUserName(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("name", paramString);
        localEditor.commit();
    }

    public static void setUserNickName(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("nick_name", paramString);
        localEditor.commit();
    }

    public static void setUserPhone(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("tel", paramString);
        localEditor.commit();
    }

    public static void setUserPwd(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("pwd", paramString);
        localEditor.commit();
    }

    public static void setUserServerUrl(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("server", MODE_PRIVATE).edit();
        localEditor.putString("current_server_url", paramString);
        localEditor.commit();
    }

    public static void setUserToken(String paramString) {
        SharedPreferences.Editor localEditor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        localEditor.putString("token", paramString);
        localEditor.commit();
    }

    public static void setStationLocalHost(final String s) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putString("station_local_host", s);
        edit.apply();
    }

    public static void setAddressCenterLat(final String s) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putString("address_center_lat", s);
        edit.apply();
    }

    public static void setAddressCenterLng(final String s) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putString("address_center_lng", s);
        edit.apply();
    }

    public static void setMapZoom(final String s) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putString(USER_SP_MAP_ZOOM, s);
        edit.apply();
    }

    public static void setCookie(final String s) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putString("cookie", s);
        edit.apply();
    }

    public static void setSendMessage(final boolean is) {
        final SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        edit.putBoolean("sendMessage", is);
        edit.apply();
    }
}
