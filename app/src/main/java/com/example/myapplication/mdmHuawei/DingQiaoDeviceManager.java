package com.example.myapplication.mdmHuawei;

import com.example.myapplication.MyApplication;
import com.tdtech.devicemanager.ContainerPolicy;
import com.tdtech.devicemanager.DevicePolicyManager;
import com.tdtech.devicemanager.LocationPolicy;
import com.tdtech.devicemanager.PeripheralPolicy;
import com.tdtech.devicemanager.TelephonyPolicy;

/**
 * Created by wangzhaosheng on 2020-01-02
 * Description
 */
public class DingQiaoDeviceManager implements IDeviceManager {

    private DevicePolicyManager mDevicePolicyManager = DevicePolicyManager.getInstance(MyApplication.getContext());
    private PeripheralPolicy mPeripheralPolicy = mDevicePolicyManager.getPeripheralPolicy();
    private TelephonyPolicy telephonyPolicy = mDevicePolicyManager.getTelephonyPolicy();
    private LocationPolicy locationPolicy = mDevicePolicyManager.getLocationPolicy();
    ContainerPolicy containerPolicy = mDevicePolicyManager.getContainerPolicy();

    @Override
    public boolean enableCamera(boolean enable) {
        return mPeripheralPolicy.enableCamera(enable);


    }

    @Override
    public boolean isCameraEnabled() {
        return mPeripheralPolicy.isCameraEnabled();
    }

    @Override
    public boolean enableSms(boolean enable) {
        return telephonyPolicy.enableSms(enable);
    }

    @Override
    public boolean isSmsEnabled() {
        return telephonyPolicy.isSmsEnabled();
    }


    /**
     * 这里跟普通理解不同  todo 和产品定
     * 1．通过MDM disable wifi后，只有mdm的客户端能打开、关闭wifi,用户不能手动打开、关闭wifi；
     * 2. 通过MDM enable wifi后， wifi进入到disable前的状态（若disable前wifi打开，则重新enable后，wifi打开； 若disable前wifi关闭，则重新enable后wifi为关闭状态），并且用户和mdm客户端均可打开或关闭wifi。
     *
     * @param enable
     * @return
     */
    @Override
    public boolean enableWifi(boolean enable) {
        return mPeripheralPolicy.enableWifi(enable);
    }

    /**
     * 这里跟普通理解不同  todo 和产品定
     *
     * @return true: 开启；false: 关闭
     */
    @Override
    public boolean isWifiEnabled() {
        return mPeripheralPolicy.isWifiEnabled();
    }

    /**
     * 鼎桥没有这个接口  未实现
     *
     * @return
     */
    @Override
    public boolean enableExternalStorage(boolean enable) {
        return false;
    }

    /**
     * 鼎桥没有这个接口  未实现
     *
     * @return
     */
    @Override
    public boolean isEnableExternalStorage() {
        return false;
    }

    /**
     * 1.入参为true，使能并开启wifi热点，可以手动关闭热点，当wifi后台静默打开时，无法开启wifi热点；
     * 2.入参为false，去使能并关闭wifi热点，手动无法开启热点。
     *
     * @return
     */
    @Override
    public boolean enableWlanAp(boolean enable) {
        return mPeripheralPolicy.enableWifiAp(enable);
    }

    @Override
    public boolean isWifiApEnabled() {
        return mPeripheralPolicy.isWifiApEnabled();
    }

    @Override
    public boolean enableSystemBrowser(boolean enable) {
        // TODO: 2020-01-04 鼎桥没有相关接口
        return false;
    }

    @Override
    public boolean enableMicrophone(boolean enable) {
        // TODO: 2020-01-04 鼎桥没有相关接口
        return false;
    }

    /**
     * 没有接口 todo
     * @return
     */
    @Override
    public boolean isEnableMicrophone() {
        return false;
    }

    @Override
    public boolean enableChangeSystemTime(boolean enable) {
        // TODO: 2020-01-04 没有相关接口
        return false;
    }

    /**
     * todo  没有接口
     * @return
     */
    @Override
    public boolean isEnableChangeSystemTime() {
        return false;
    }

    /**
     * 是否允许定位
     *
     * @param enable
     * @return
     */
    @Override
    public boolean enableLocationService(boolean enable) {
        return locationPolicy.enableLocationService(enable);
    }

    /**
     * boolean isForceOpenLocation
     * true 强制打开 false 取消强制打开
     *
     * @param isForceOpenLocation
     * @return
     */
    @Override
    public boolean forceLocationService(boolean isForceOpenLocation) {
        return locationPolicy.forceLocationService(isForceOpenLocation);
    }

    @Override
    public boolean isLocationServiceEnabled() {
        return locationPolicy.isLocationServiceEnabled();
    }

    @Override
    public boolean isLocationServiceForced() {
        return locationPolicy.isLocationServiceForced();
    }

    /**
     * todo 开发者选项不能禁用,usb功能禁用可以达到效果,允许USB：允许使用USB，包括PTP和MTP等禁止USB：禁止使用USB功能，仅支持充电。
     *
     * @param enable
     * @return
     */

    @Override
    public boolean enableUsb(boolean enable) {
        return mPeripheralPolicy.enableUsb(enable);
    }

    @Override
    public boolean isUsbEnabled() {
        return mPeripheralPolicy.isUsbEnabled();
    }

    @Override
    public boolean enableBluetooth(boolean enable) {
        return mPeripheralPolicy.enableBluetooth(enable);
    }

    @Override
    public boolean isBluetoothEnabled() {
        return mPeripheralPolicy.isBluetoothEnabled();
    }

    /**
     * todo 鼎桥没有这个接口
     *
     * @param enable
     * @return
     */
    @Override
    public boolean enableFactoryReset(boolean enable) {
        return false;
    }

    // TODO: 2020-01-06  没有接口 
    @Override
    public boolean isEnableFactoryReset() {
        return false;
    }

    @Override
    public boolean enableSecSimcard(boolean enable) {
        return mPeripheralPolicy.enableSecSimcard(enable);
    }

    @Override
    public boolean isSecSimcardEnabled() {
        return mPeripheralPolicy.isSecSimcardEnabled();
    }

    /**
     * todo 鼎桥没有这个接口
     *
     * @param enable
     * @return
     */
    @Override
    public boolean enableUseApn(boolean enable) {
        return false;
    }

    @Override
    public boolean isEnableUseApn() {
        return false;//无
    }

    @Override
    public boolean enableTelePhone(boolean enable) {
        return telephonyPolicy.enableTelephone(enable);
    }

    @Override
    public boolean isTelephoneEnabled() {
        return telephonyPolicy.isTelephoneEnabled();
    }

    /**
     * enableNfc:  使能并打开NFC
     * disableNfc: 去使能Nfc并关闭NFC，NFC功能被禁用
     *
     * @return
     */
    @Override
    public boolean enableNfc(boolean enable) {
        return enable ? mPeripheralPolicy.enableNfc() : mPeripheralPolicy.disableNfc();
    }

    /**
     * todo 鼎桥没有这个接口
     *
     * @return
     */
    @Override
    public boolean forceOpenNfc() {
        return false;
    }

    /**
     * true: NFC功能可以打开；false: NFC功能被禁用
     *
     * @return
     */
    @Override
    public boolean isNfcAllowed() {
        return mPeripheralPolicy.isNfcAllowed();
    }

    @Override
    public void enableSwitchingSystem(boolean enable) {
        if (enable) {
            containerPolicy.enableSwitching();
        } else {
            containerPolicy.disableSwitching();
        }
    }

    @Override
    public boolean isEnableSwitchingSystem() {
       // 无接口
        return false;
    }


}
