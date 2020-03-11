package com.example.myapplication.mdmHuawei;

import ga.mdm.PolicyManager;

/**
 * Created by wangzhaosheng on 2020-01-02
 * Description
 */
public class BuBiaoDeviceManager implements IDeviceManager {

    /**
     * 允许使用  当有两种值的时候  1代表允许   多个值的时候1可能代表其他意思
     */
    private static final int ENABLE = 1;
    /**
     * 禁止使用
     */
    private static final int DISNABLE = 0;


    PolicyManager mPolicyManager = PolicyManager.getInstance();


    @Override
    public boolean enableCamera(boolean enable) {
        //        mode：功能模式
        //        0：不允许使用终端的摄像机；
        //        1：允许使用终端的摄像机。
        return mPolicyManager.setCameraPolicies(enable ? ENABLE : DISNABLE);
    }

    @Override
    public boolean isCameraEnabled() {
        return mPolicyManager.getCameraPolicies() == ENABLE;
    }


    /**
     * mode：功能模式
     * 0：禁用短信功能；
     * 1：允许短信功能。
     * regExp：正则表达式，当mode=1时有效；  todo 未知参数
     *
     * @return
     */
    @Override
    public boolean enableSms(boolean enable) {
        return mPolicyManager.setSmsPolicies(enable ? ENABLE : DISNABLE, "");
    }

    /**
     * 是否允许使用短信
     *
     * @return
     */
    @Override
    public boolean isSmsEnabled() {
        //0 禁用  1 允许
        return mPolicyManager.getSmsPolicies() == ENABLE;
    }

    /**
     * mode：功能模式
     * 	0=禁止终端使用无线网络；
     * 	1=只允许终端进行无线网络指纹扫描，但无法进行无线网络连	接；
     *  2=允许终端使用无线网络；
     * @param enable
     * @return
     */
    @Override
    public boolean enableWifi(boolean enable) {
        return mPolicyManager.setWlanPolicies(enable? 2:0);
    }

    /**
     *  @return mode：功能模式
     * 	0=禁止终端使用无线网络；
     * 	1=只允许终端进行无线网络指纹扫描，但无法进行无线网络连	接；
     *  2=允许终端使用无线网络；
     *
     */
    @Override
    public boolean isWifiEnabled() {
        return mPolicyManager.getWlanPolicies() == 2;
    }

    /**
     * mode：功能模式
     * 0：不允许终端对扩展存储进行读、写。
     * 1：仅允许终端对扩展存储进行读操作。
     * 3：允许终端对扩展存储进行读、写操作。
     * 该接口仅当工作区挂载了SD卡或者U盘时有效，如工作区无法挂载SD卡或者U盘时（例如通过定制使工作区无法挂载SD卡或者U盘），调用该接口没有效果。
     */
    @Override
    public boolean enableExternalStorage(boolean enable) {
        return mPolicyManager.setExternalStoragePolicies(enable? 3 : 0);
    }
    /**
     * mode：功能模式
     * 0：不允许终端对扩展存储进行读、写。
     * 1：仅允许终端对扩展存储进行读操作。
     * 3：允许终端对扩展存储进行读、写操作。
     * 该接口仅当工作区挂载了SD卡或者U盘时有效，如工作区无法挂载SD卡或者U盘时（例如通过定制使工作区无法挂载SD卡或者U盘），调用该接口没有效果。
     */
    @Override
    public boolean isEnableExternalStorage() {
        return mPolicyManager.getExternalStoragePolicies() == 3;
    }

    /**
     * mode：功能模式
     * 0：禁止终端使用网络共享功能；
     * 1：允许终端启用网络共享功能，但只允许列表中指定MAC地址的设备接入。
     * 2：允许终端使用网络共享功能；
     *
     * macInfoList：仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下："00-11-22-33-44-55"。
     * @param enable true 允许  false 禁止
     * @return
     */
    @Override
    public boolean enableWlanAp(boolean enable) {
        return mPolicyManager.setWlanApPolicies(enable? 2: 0,null);
    }

    /**
     * todo  根本看不懂  测试一下
     * 返回值为当前网络共享管控策略状态
     * string[0]：功能模式，参见setWlanApPolicies方法的mode参数。
     * string[1]至string[n-1]：仅当mode=1时返回允许接入的特定MAC地址信息，参见setWlanApPolicies方法的macInfoList参数
     * @return
     */
    @Override
    public boolean isWifiApEnabled() {
        // TODO: 2020-01-06
        String[] wlanApPolicies = mPolicyManager.getWlanApPolicies();
        return false;
    }

    /**
     * 部标用的是禁用某个app来实现
     * mode：应用名单类型
     * 0：黑名单(应用包名列表中的所有项都不允许运行)；
     * 1：白名单（应用包名列表中的项如已安装，则强制运行）。  todo 这里会有强制运行  需要与产品协商    注意非白即黑的情况是否存在
     * （无法强制运行，会对白名单中的应用做保活处理）
     * appPackageNames：应用包名列表；当appPackageNames为空时，取消所有已设定的应用。
     * @return
     */
    @Override
    public boolean enableSystemBrowser(boolean enable) {
        mPolicyManager.setRunAppPolicies(enable? 1:0, new String[]{"com.huawei.browser.ChromeTabbedActivity"});
        return false;
    }

    @Override
    public boolean enableMicrophone(boolean enable) {
        return mPolicyManager.setMicrophonePolicies(enable? 1: 0);
    }

    @Override
    public boolean isEnableMicrophone() {
        return mPolicyManager.getMicrophonePolicies() ==1;
    }

    /**
     * todo 有待验证:boolean setUserTimeMgrPolicies(int mode)
     *
     * mode：功能模式
     * 0：不允许用户或应用修改本机时间及时间来源，并强制同步移动网络时间；
     * 1：允许用户或应用修改本机时间，以及设定时间来源。
     * （此接口对电信SIM卡无效，因为电信SIM卡必须强制同步网络时间）
     * @param enable
     * @return
     */
    @Override
    public boolean enableChangeSystemTime(boolean enable) {
        return mPolicyManager.setUserTimeMgrPolicies(enable?1:0);
    }

    /**
     * todo 有待验证:boolean setUserTimeMgrPolicies(int mode)
     *
     * mode：功能模式
     * 0：不允许用户或应用修改本机时间及时间来源，并强制同步移动网络时间；
     * 1：允许用户或应用修改本机时间，以及设定时间来源。
     * （此接口对电信SIM卡无效，因为电信SIM卡必须强制同步网络时间）
     * @return
     */
    @Override
    public boolean isEnableChangeSystemTime() {
        return mPolicyManager.getUserTimeMgrPolicies()==1;
    }

    /**
     * 有歧义:boolean setGpsPolicies(int mode)
     * mode：功能模式
     * 0：禁止终端使用定位服务；
     * 1：强制终端开启定位服务，且不允许关闭；
     * 2：不对定位服务的开关和使用进行控制。
     * 返回值:true: 成功；false: 失败
     * @param enable
     * @return
     */
    @Override
    public boolean enableLocationService(boolean enable) {
        return mPolicyManager.setGpsPolicies(enable? 2: 0);
    }

    /**
     * 有歧义:boolean setGpsPolicies(int mode)
     * mode：功能模式
     * 0：禁止终端使用定位服务；
     * 1：强制终端开启定位服务，且不允许关闭；
     * 2：不对定位服务的开关和使用进行控制。
     * 返回值:true: 成功；false: 失败
     * @return
     */
    @Override
    public boolean forceLocationService(boolean isForceOpenLocation) {
        //todo 部标需要注意是否正确
        return mPolicyManager.setGpsPolicies(isForceOpenLocation? 1: 2);
    }

    /**
     * 有歧义:boolean setGpsPolicies(int mode)
     * mode：功能模式
     * 0：禁止终端使用定位服务；
     * 1：强制终端开启定位服务，且不允许关闭；
     * 2：不对定位服务的开关和使用进行控制。
     * 返回值:true: 成功；false: 失败
     * @return  只要不是禁用就算可以用
     */
    @Override
    public boolean isLocationServiceEnabled() {
        return !(mPolicyManager.getGpsPolicies()==0);
    }

    /**
     * 有歧义:boolean setGpsPolicies(int mode)
     * mode：功能模式
     * 0：禁止终端使用定位服务；
     * 1：强制终端开启定位服务，且不允许关闭；
     * 2：不对定位服务的开关和使用进行控制。
     * 返回值:true: 成功；false: 失败
     * @return
     */
    @Override
    public boolean isLocationServiceForced() {
        return mPolicyManager.getGpsPolicies()==1;
    }

    /**
     *mode：功能模式
     *  0：不允许终端通过USB接口进行数据传输，仅允许充电模式；
     *  1：不控制USB接口的工作模式，支持MTP模式 、PTP模式、HOST 模式进行数据传输与调试模式。
     * @param enable
     * @return
     */
    @Override
    public boolean enableUsb(boolean enable) {
        return mPolicyManager.setUsbDataPolicies(enable? 1:0);
    }

    /**
     *mode：功能模式
     *  0：不允许终端通过USB接口进行数据传输，仅允许充电模式；
     *  1：不控制USB接口的工作模式，支持MTP模式 、PTP模式、HOST 模式进行数据传输与调试模式。
     * @return
     */
    @Override
    public boolean isUsbEnabled() {
        return mPolicyManager.getUsbDataPolicies()==1;
    }

    /**
     * mode：功能模式
     *  0：不允许终端使用蓝牙；
     *  1：仅允许与准许蓝牙连接列表内的蓝牙设备建立蓝牙连接，列表 可根据目标设备的蓝牙物理地址进行定义；
     *  2：允许终端使用蓝牙。
     * BluetoothInfoList：仅当mode=1时有效，数组中每一项为一个JSON格式字符串，格式如下：{"Mac":"00-11-22-33-44-55"}。
     * @param enable
     * @return
     */
    @Override
    public boolean enableBluetooth(boolean enable) {
        return mPolicyManager.setBluetoothPolicies(enable?2:0,null);
    }


    /**
     * todo 有歧义
     * string[0]:功能模式，参见setBluetoothPolicies方法的mode参数。
     * string[1]至string[n]: 仅当mode=1时返回允许连接的特定蓝牙网络信息，参见setBluetoothPolicies方法的BluetoothInfoList参数
     * @return
     */
    @Override
    public boolean isBluetoothEnabled() {

        String[] bluetoothPolicies = mPolicyManager.getBluetoothPolicies();
        return false;
    }

    /**
     * mode：功能模式
     * 0：不允许用户在设置菜单中对终端进行恢复出厂设置的操作；
     * 1：允许用户在设置菜单中对终端进行恢复出厂设置的操作。
     * @param enable
     * @return
     */
    @Override
    public boolean enableFactoryReset(boolean enable) {
        return mPolicyManager.setFactoryResetPolicies(enable?1:0);
    }

    @Override
    public boolean isEnableFactoryReset() {
        return mPolicyManager.getFactoryResetPolicies() == 1;
    }


    /**
     * todo 没有这个接口
     * @param enable
     * @return
     */
    @Override
    public boolean enableSecSimcard(boolean enable) {
        return false;
    }


    /**
     * todo 没有
     * @return
     */
    @Override
    public boolean isSecSimcardEnabled() {
        return false;
    }

    /**
     * mode：功能模式
     * 0：不允许用户增加、删除、修改、查看APN配置以及选择APN；
     * 1：仅允许用户查看APN配置，但不允许其他操作；
     * 2：允许用户增加、删除、修改、查看APN信息，及选择使用的APN。
     * 返回值:true 成功 false 失败
     * @param enable
     * @return
     */
    @Override
    public boolean enableUseApn(boolean enable) {
        return mPolicyManager.setUserApnMgrPolicies(enable?2:0);
    }
    /**
     * mode：功能模式
     * 0：不允许用户增加、删除、修改、查看APN配置以及选择APN；
     * 1：仅允许用户查看APN配置，但不允许其他操作；
     * 2：允许用户增加、删除、修改、查看APN信息，及选择使用的APN。
     * 返回值:true 成功 false 失败
     * @return
     */
    @Override
    public boolean isEnableUseApn() {
        return mPolicyManager.getUserApnMgrPolicies() == 2;
    }

    /**
     * 0禁止  1 允许
     * @param enable
     * @return
     */
    @Override
    public boolean enableTelePhone(boolean enable) {
        return mPolicyManager.setVoicePolicies(enable? 1:0);
    }

    @Override
    public boolean isTelephoneEnabled() {
        return mPolicyManager.getVoicePolicies()==1;
    }

    /**
     * 2 允许
     * 0 禁止
     * 1 强制开启
     * @param enable
     * @return
     */
    @Override
    public boolean enableNfc(boolean enable) {
        return mPolicyManager.setNfcPolicies(enable?2:0);
    }

    @Override
    public boolean forceOpenNfc() {
        return mPolicyManager.setNfcPolicies(1);
    }

    /**
     * mode：功能模式
     * 	0=不允许终端启用NFC功能；
     * 	1=强制终端开启NFC功能；
     * 	2=允许用户自主控制NFC功能的开关。
     * @return 只要不是禁用就算允许 ,强制开启也算作允许
     */
    @Override
    public boolean isNfcAllowed() {
        return !(mPolicyManager.getNfcPolicies() ==0);
    }

    @Override
    public void enableSwitchingSystem(boolean enable) {
        mPolicyManager.setContainerPolicies(enable?1:0);
    }

    @Override
    public boolean isEnableSwitchingSystem() {
        return mPolicyManager.getContainerPolicies() == 1;
    }


}
