package com.example.myapplication.mdmHuawei;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * APN信息:
 * name、apn、mcc、mnc、numeric、 user、password、server、proxy、port、
 * mmsport、mmsproxy、mmsc、 authtype、type
 */
public class ApnInfo implements Parcelable {
    @SerializedName("name")
    private String name;

    @SerializedName("apn")
    private String apn;

    @SerializedName("mcc")
    private String mcc;

    @SerializedName("mnc")
    private String mnc;

    @SerializedName("numeric")
    private String numeric;

    @SerializedName("user")
    private String user;

    @SerializedName("apnpassword")
    private String password;

    @SerializedName("server")
    private String server;

    @SerializedName("proxy")
    private String proxy;

    @SerializedName("port")
    private String port;

    @SerializedName("mmsport")
    private String mmsport;

    @SerializedName("mmsproxy")
    private String mmsproxy;

    @SerializedName("mmsc")
    private String mmsc;

    @SerializedName("authtype")
    private String authtype;

    @SerializedName("type")
    private String type;

    @SerializedName("protocol")
    private String protocol;

    @SerializedName("roaming_protocol")
    private String roaming_protocol;

    @SerializedName("mvno_type")
    private String mvno_type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.apn);
        dest.writeString(this.mcc);
        dest.writeString(this.mnc);
        dest.writeString(this.numeric);
        dest.writeString(this.user);
        dest.writeString(this.password);
        dest.writeString(this.server);
        dest.writeString(this.proxy);
        dest.writeString(this.port);
        dest.writeString(this.mmsport);
        dest.writeString(this.mmsproxy);
        dest.writeString(this.mmsc);
        dest.writeString(this.authtype);
        dest.writeString(this.type);
        dest.writeString(this.protocol);
        dest.writeString(this.roaming_protocol);
        dest.writeString(this.mvno_type);
    }

    public ApnInfo() {
    }

    protected ApnInfo(Parcel in) {
        this.name = in.readString();
        this.apn = in.readString();
        this.mcc = in.readString();
        this.mnc = in.readString();
        this.numeric = in.readString();
        this.user = in.readString();
        this.password = in.readString();
        this.server = in.readString();
        this.proxy = in.readString();
        this.port = in.readString();
        this.mmsport = in.readString();
        this.mmsproxy = in.readString();
        this.mmsc = in.readString();
        this.authtype = in.readString();
        this.type = in.readString();
        this.protocol = in.readString();
        this.roaming_protocol = in.readString();
        this.mvno_type = in.readString();
    }

    public static final Creator<ApnInfo> CREATOR = new Creator<ApnInfo>() {
        @Override
        public ApnInfo createFromParcel(Parcel source) {
            return new ApnInfo(source);
        }

        @Override
        public ApnInfo[] newArray(int size) {
            return new ApnInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMmsport() {
        return mmsport;
    }

    public void setMmsport(String mmsport) {
        this.mmsport = mmsport;
    }

    public String getMmsproxy() {
        return mmsproxy;
    }

    public void setMmsproxy(String mmsproxy) {
        this.mmsproxy = mmsproxy;
    }

    public String getMmsc() {
        return mmsc;
    }

    public void setMmsc(String mmsc) {
        this.mmsc = mmsc;
    }

    public String getAuthtype() {
        return authtype;
    }

    public void setAuthtype(String authtype) {
        this.authtype = authtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRoaming_protocol() {
        return roaming_protocol;
    }

    public void setRoaming_protocol(String roaming_protocol) {
        this.roaming_protocol = roaming_protocol;
    }

    public String getMvno_type() {
        return mvno_type;
    }

    public void setMvno_type(String mvno_type) {
        this.mvno_type = mvno_type;
    }

    /**
     * 转化为key-value结构 作为操作APN的参数
     * 注意:此处map中的参数key值必须和第三发API中保持一致，否则会导致设置失败
     * @return map
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("apn", apn);
        map.put("mcc", mcc);
        map.put("mnc", mnc);
        map.put("numeric", numeric);
        map.put("user", user);
        //注意 此处为password
        map.put("password", password);
        map.put("server", server);
        map.put("proxy", proxy);
        map.put("port", port);
        map.put("mmsport", mmsport);
        map.put("mmsproxy", mmsproxy);
        map.put("mmsc", mmsc);
        map.put("authtype", authtype);
        map.put("type", type);
        map.put("protocol", protocol);
        map.put("roaming_protocol", roaming_protocol);
        map.put("mvno_type", mvno_type);
        return map;
    }
}
