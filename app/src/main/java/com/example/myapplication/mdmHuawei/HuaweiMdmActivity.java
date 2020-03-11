package com.example.myapplication.mdmHuawei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.tdtech.devicemanager.ContainerPolicy;
import com.tdtech.devicemanager.DevicePolicyManager;
import com.tdtech.devicemanager.LocationPolicy;
import com.tdtech.devicemanager.PeripheralPolicy;
import com.tdtech.devicemanager.TelephonyPolicy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ga.mdm.PolicyManager;

/**
 * todo   !!!  用这里的功能 需要manifest 配置   取消注释
 */

public class HuaweiMdmActivity extends AppCompatActivity {

    //鼎桥api-----
    private DevicePolicyManager mDevicePolicyManager = DevicePolicyManager.getInstance(MyApplication.getContext());
    private PeripheralPolicy mPeripheralPolicy = mDevicePolicyManager.getPeripheralPolicy();
    private TelephonyPolicy telephonyPolicy = mDevicePolicyManager.getTelephonyPolicy();
    private LocationPolicy locationPolicy = mDevicePolicyManager.getLocationPolicy();
    ContainerPolicy containerPolicy = mDevicePolicyManager.getContainerPolicy();
    //---------


    public static final String TAG = HuaweiMdmActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huawei_mdm);
    }
    IDeviceManager dingqiao = new DingQiaoDeviceManager();
    IDeviceManager bubiao = new BuBiaoDeviceManager();
    //部标api
    PolicyManager mPolicyManager = PolicyManager.getInstance();


    public void click62(View view) {

        boolean ding = dingqiao.enableCamera(true);
        boolean bu = bubiao.enableCamera(true);
        System.out.println("鼎桥返回值:"+ ding+"  部标返回值"+ bu);

    }

    public void click63(View view) {
        boolean ding = this.dingqiao.enableCamera(false);
        boolean bu = this.bubiao.enableCamera(false);
        System.out.println("鼎桥返回值:"+ ding+"  部标返回值"+ bu);
    }

    public void click65(View view) {
        boolean ding = dingqiao.isCameraEnabled();
        boolean bu = dingqiao.isCameraEnabled();
        System.out.println("获取鼎桥是否允许返回值:"+ ding+"  部标返回值"+ bu);
    }

    public void click68(View view) {
        boolean b = bubiao.enableWlanAp(true);

    }

    public void click69(View view) {
        boolean b = bubiao.enableWlanAp(false);

    }

    public void click72(View view) {
        //test
//            String json = "{\"name\":\"ZDTEST\",\"apn\":\"zzddAPN\",\"proxy\":\"\",\"port\":\"\",\"mmsproxy\":\"\",\"mmsport\":\"\",\"user\":\"\",\"server\":\"\",\"password\":\"\",\"mmsc\":\"\",\"type\":\"default,supl\",\"mcc\":\"460\",\"mnc\":\"02\",\"numeric\":\"46002\",\"authtype\":\"-1\"}";
            String json = "{\"name\":\"ZDTEST\",\"apn\":\"zzddAPN\",\"proxy\":\"\",\"port\":\"\",\"mmsproxy\":\"\",\"mmsport\":\"\",\"user\":\"\",\"server\":\"\",\"password\":\"\",\"mmsc\":\"\",\"type\":\"default,supl\",\"mcc\":\"460\",\"mnc\":\"00\",\"numeric\":\"46000\",\"authtype\":\"-1\"}";
            ApnInfo apnInfo = JsonUtil.fromJson(json, ApnInfo.class);
        Map<String, String> map = apnInfo.toMap();
        JSONObject jo = null;
        try {
            jo = toJson(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int apnId = mPolicyManager.createApn(jo.toString());
        boolean ret = mPolicyManager.setCurrentApn(apnId);
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "setCurrentApn: " + apnId + ", " + ret);
        }

    }

    /**
     * 将map格式数据转化为json格式数据
     * @param apnInfo map格式数据（key需要与公安部接口中字段一致，
     *                参考emm_android工程中的ApnInfo.java中的map字段）
     * @return json格式字符串
     */
    private JSONObject toJson(Map<String, String> apnInfo) throws JSONException {
        JSONObject config = new JSONObject();
        config.put("name", apnInfo.get("name"));
        config.put("apn", apnInfo.get("apn"));
        config.put("mcc", apnInfo.get("mcc"));
        config.put("mnc", apnInfo.get("mnc"));
        config.put("numeric", apnInfo.get("numeric"));
        config.put("user", apnInfo.get("user"));
        config.put("password", apnInfo.get("password"));
        config.put("server", apnInfo.get("server"));
        config.put("proxy", apnInfo.get("proxy"));
        config.put("port", apnInfo.get("port"));
        config.put("mmsport", apnInfo.get("mmsport"));
        config.put("mmsproxy", apnInfo.get("mmsproxy"));
        config.put("mmsc", apnInfo.get("mmsc"));
        config.put("authtype", apnInfo.get("authtype"));
        config.put("type", apnInfo.get("type"));
        config.put("protocol", apnInfo.get("protocol"));
        config.put("roaming_protocol", apnInfo.get("roaming_protocol"));
        config.put("mvno_type", apnInfo.get("mvno_type"));
        return config;
    }

    public void click73(View view) {

        addApn();
    }

    public void addApn() {
        //{"name":"CMNET","apn":"cmnet","proxy":"","port":"","mmsproxy":"","mmsport":"","user":"","server":"","password":"","mmsc":"","type":"default,supl","mcc":"460","mnc":"02","numeric":"46002","bearer":"0","authtype":"-1"}
        try {
//            String apnInfo = "{\"password\":\"\",\"server\":\"\",\"mmsc\":\"\",\"mnc\":\"00\",\"mmsport\":\"\",\"numeric\":\"46000\",\"mcc\":\"460\",\"type\":\"default,supl\",\"authtype\":\"0\",\"proxy\":\"\",\"protocol\":\"\",\"port\":\"\",\"roaming_protocol\":\"\",\"name\":\"zzddAPN\",\"mvno_type\":\"\",\"user\":\"\",\"apn\":\"ApnTest\",\"mmsproxy\":\"\"}";

            String apnInfo = "{\"password\":\"\",\"server\":\"\",\"mmsc\":\"\",\"mnc\":\"00\",\"mmsport\":\"\",\"numeric\":\"46000\",\"mcc\":\"460\",\"type\":\"default,supl\",\"authtype\":\"0\",\"proxy\":\"\",\"protocol\":\"\",\"port\":\"\",\"roaming_protocol\":\"\",\"name\":\"王\",\"mvno_type\":\"\",\"user\":\"\",\"apn\":\"王apn\",\"mmsproxy\":\"\"}";
            int id = mPolicyManager.createApn(apnInfo);
            Log.e(TAG, "add id: " + id);

            boolean ret = mPolicyManager.setCurrentApn(id);
            Log.e(TAG, "set current" + ret);

            int apnId = mPolicyManager.getCurrentApn();
            Log.e(TAG, "get current id: " + apnId);

            queryApn();
        } catch (Exception e) {
            Log.e(TAG, "addApn Exception: " + e);
            e.printStackTrace();
        }
    }

    public void queryApn() {
        try {
            List<Integer> idList = mPolicyManager.getApnList();
            if (idList != null) {
                for (int i = 0; i < idList.size(); i++) {
                    int apnId = idList.get(i);
                    String apnInfo = mPolicyManager.getApnInfo(apnId);
                    Log.e(TAG, "apnId: " + apnId + ", " + apnInfo);
                }
            } else {
                Log.e(TAG, "idList is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "idList Exception: " + e);
            e.printStackTrace();
        }
    }

    public void click74(View view) {
        deleteMyapn();
    }

    private void deleteMyapn() {
        try {
            List<Integer> idList = mPolicyManager.getApnList();
            if (idList != null) {
                for (int i = 0; i < idList.size(); i++) {
                    int apnId = idList.get(i);
                    String apnInfo = mPolicyManager.getApnInfo(apnId);
                    Log.e(TAG, "apnId: " + apnId + ", " + apnInfo);
                    if(apnId!=15 && apnId!=16&&apnId!=17){//默认的不删除  默认的需要自己获取  每个手机卡不一样
                        boolean b = mPolicyManager.deleteApn(apnId);
                        Log.e(TAG, "删除是否成功 " + b+ "删除id"+ apnId);
                    }
                }
            } else {
                Log.e(TAG, "idList is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "idList Exception: " + e);
            e.printStackTrace();
        }
    }

    public void click75(View view) {

        queryApn();
    }


    public void click80(View view) {
        bubiao.enableLocationService(true);
    }



    public void click81(View view) {
        bubiao.enableLocationService(false);
    }
    public void click82(View view) {
        bubiao.forceLocationService(true);
    }

    public void click83(View view) {
        bubiao.enableLocationService(true);
        bubiao.forceLocationService(true);
    }

    public boolean forceEnableMobileNetWork(int a)  {
        //0：强制开启终端的移动数据网络，且不允许用户关闭；
        //1：允许用户自主控制终端移动数据网络的开关。

//        0:强制关闭
//        1:强制打开
//        2.跟随用户
//        int mode = force ? 0 : 1;
        boolean ret = mPolicyManager.setDataConnectivityPolicies(a);
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "setDataConnectivityPolicies: " + ret);
        }
        return ret;
    }

    public void click84(View view) {
        forceEnableMobileNetWork(0);

    }

    public void click85(View view) {
        forceEnableMobileNetWork(1);
    }
    public void click86(View view) {
        forceEnableMobileNetWork(2);
    }

    public void setWifiDisabled(int a)  {
        //0=禁止终端使用无线网络；
        //	1=只允许终端进行无线网络指纹扫描，但无法进行无线网络连	接；
        //    2=允许终端使用无线网络；
//        int mode = disabled ? 0 : 2;
        boolean ret = mPolicyManager.setWlanPolicies(a);
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "setWlanPolicies: " + ret);
        }
    }

    public void click87(View view) {
        setWifiDisabled(0);
    }
    public void click88(View view) {
        setWifiDisabled(1);
    }
    public void click89(View view) {
        setWifiDisabled(2);
    }

    public void click90(View view) {
        mPeripheralPolicy.openWifiOnBGSlient();
    }

    public void click91(View view) {
//        mode：功能模式
//        0：不允许终端使用蓝牙；
//        1：仅允许与准许蓝牙连接列表内的蓝牙设备建立蓝牙连接，列表	可根据目标设备的蓝牙物理地址进行定义；
//        2：允许终端使用蓝牙。
//        BluetoothInfoList：仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下：[{"Mac":"00-11-22-33-44-55"},{"Mac":"00-11-22-33-44-55"}]。
        mPolicyManager.setBluetoothPolicies(2, null);
    }


    public void click92(View view) {

        String json = "{\"Mac\":\"28:33:34:05:7C:FC\"}";
        String json2 ="{\"Mac\":\"0C:8F:FF:91:48:7F\"}";
        String json3 = "[{\"Mac\":\"28:33:34:05:7C:FC\"},{\"Mac\":\"0C:8F:FF:91:48:7F\"}]";//todo list格式不行
//        String json = "{\"Mac\":\"28:33:34:05:7C:FD\"}";todo 输错不行
// TODO: 2020-02-24 这个多次set的情况,不会叠加,会重置
//        mPolicyManager.setBluetoothPolicies(2, null);
        mPolicyManager.setBluetoothPolicies(1, new String[] {json2});
    }

    public void click93(View view) {
        String[] bluetoothPolicies = mPolicyManager.getBluetoothPolicies();
        System.out.println(bluetoothPolicies);
    }

    public void click94(View view) {
//        LocationPolicy locationPolicy = mDevicePolicyManager.getLocationPolicy();
//        int locationPolicy1 = locationPolicy.getLocationPolicy();
        locationPolicy.openLocationService(true);
    }


    public void click95(View view) {
//        LocationPolicy locationPolicy = mDevicePolicyManager.getLocationPolicy();
        locationPolicy.openLocationService(false);
    }

    public void click117(View view) {
        locationPolicy.enableLocationService(true);
    }

    public void click118(View view) {
        locationPolicy.enableLocationService(false);
    }

    public void click111(View view) {
        telephonyPolicy.setAirplaneMode(0);
    }


    public void click112(View view) {
        telephonyPolicy.setAirplaneMode(1);
    }


    public void click114(View view) {
        telephonyPolicy.setAirplaneMode(2);
    }


    public void click115(View view) {
        telephonyPolicy.setAirplaneMode(3);
    }


    public void click116(View view) {
        telephonyPolicy.setAirplaneMode(-1);
    }

    public void click113(View view) {
        int airplaneMode = telephonyPolicy.getAirplaneMode();
        System.out.println("telephonyPolicy"+airplaneMode);
    }


    public void click119(View view) {
        mPeripheralPolicy.enableBluetooth(true);

    }
    public void click120(View view) {
        mPeripheralPolicy.enableBluetooth(false);

    }

    public void click121(View view) {
//        String json2 ="{\"Mac\":\"0C:8F:FF:91:48:7F\"}";
        mPeripheralPolicy.addDeviceToBTWhiteList("0C:8F:FF:91:48:7F");
    }

    public void click122(View view) {
        mPeripheralPolicy.enableBluetoothWhiteList(true);
    }

    public void click126(View view) {
        mPeripheralPolicy.enableBluetoothWhiteList(false);
    }

    public void click123(View view) {
        boolean bluetoothWhiteListEnabled = mPeripheralPolicy.isBluetoothWhiteListEnabled();
        Toast.makeText(this,bluetoothWhiteListEnabled?"允许":"禁止",1).show();
    }

    public void click124(View view) {
        mPeripheralPolicy.deleteDeviceFromBTWhiteList("0C:8F:FF:91:48:7F");
    }

    public void click125(View view) {
        List<String> strings = mPeripheralPolicy.queryAllBTWhiteList();
        System.out.println(strings);
        Iterable a = getAb();
        int[] a1 = getA();
        for (int i : a1) {

        }
        for (int i : a1) {

        }
    }


    int [] getA(){
        return new int [5];
    }
    ArrayList getAb(){
        Hashtable<String ,Object> aa=null;
        Set<Map.Entry<String, Object>> entries = aa.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        Map.Entry<String, Object> next = iterator.next();
        Object value = next.getValue();
        Collection<Object> values = aa.values();
        Set<String> strings = aa.keySet();
        return new ArrayList();
    }
}
