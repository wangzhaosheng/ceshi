package com.example.myapplication.mdmHuawei;

/**
 * Created by wangzhaosheng on 2020-01-02
 * Description 华为双系统手机mdm接口
 * 实现类两个:鼎桥接口和部标接口
 */
public interface IDeviceManager {


    /**
     * 是否允许使用摄像头
     *
     * @return <code>false</code>调用失败
     * 允许Camera：允许使用Camera
     * 禁止Camera：禁止使用Camera功能。
     * enable：true: 允许；false: 禁止
     */

    boolean enableCamera(boolean enable);

    /**
     * 查询是否允许使用Camera
     *
     * @return
     */
    boolean isCameraEnabled();

    /**
     * 对短信功能进行控制
     *
     * @param enable 为true时，允许使用短信功能；反之，禁止使用短信功能。
     * @return true：成功 false：失败
     */
    boolean enableSms(boolean enable);


    /**
     * 查询短信功能是否允许使用
     *
     * @return true if enable
     */
    boolean isSmsEnabled();


    /**
     * 是否禁用wifi
     *
     * @param enable
     * @return true: 成功   false:失败
     */
    boolean enableWifi(boolean enable);

    /**
     * 是否禁用wifi
     *
     * @return true: enable   false:disable
     */
    boolean isWifiEnabled();

    /**
     * 是否禁用外置存储
     *
     * @param enable true 是  false 否
     * @return true 成功  false 失败
     */
    boolean enableExternalStorage(boolean enable);

    /**
     * 是否允许使用外置sd卡
     *
     * @return true 是  false 否
     */
    boolean isEnableExternalStorage();

    /**
     * 是否允许启用热点(该设备开热点,其他设备连接)
     *
     * @param enable true 允许  false 禁止
     */
    boolean enableWlanAp(boolean enable);




    boolean isWifiApEnabled();

    /**
     * 是否可以用系统原生浏览器   todo 鼎桥没有 部标有禁用应用
     *
     * @param enable
     * @return
     */
    boolean enableSystemBrowser(boolean enable);


    /**
     * 是否可以用麦克风
     *
     * @param enable
     * @return
     */
    boolean enableMicrophone(boolean enable);

    /**
     * 获取是否可以用麦克风
     *
     * @return
     */
    boolean isEnableMicrophone();


    /**
     * 是否允许修改系统时间
     *
     * @param enable
     * @return
     */
    boolean enableChangeSystemTime(boolean enable);
    /**
     * 获取是否允许修改系统时间
     *
     * @return
     */
    boolean isEnableChangeSystemTime();


    /**
     * 是否允许定位
     *
     * @param enable
     * @return
     */
    boolean enableLocationService(boolean enable);

    /**
     * 强制开启定位
     * boolean isForceOpenLocation
     * true 强制打开 false 取消强制打开   todo 部标需要注意是否正确
     *
     * @param
     * @return
     */
    boolean forceLocationService(boolean isForceOpenLocation);

    /**
     *获取是否允许定位
     * @return
     */
    boolean isLocationServiceEnabled();


    /**
     * 获取是否是强制定位
     * @return
     */
    boolean isLocationServiceForced();


    /**
     * 开发者选项,usb调试
     * ,数据传输
     * todo 跟产品确认
     *
     * @param enable
     * @return
     */
    boolean enableUsb(boolean enable);


    /**
     * 是否可以开发者选项,usb调试,数据传输
     * @return
     */
    boolean isUsbEnabled();


    /**
     * 是否允许使用蓝牙
     *
     * @param enable
     * @return
     */
    boolean enableBluetooth(boolean enable);

    /**
     * 获取是否允许使用蓝牙
     *
     * @return
     */
    boolean isBluetoothEnabled();

    /**
     * 是否允许恢复出厂
     *
     * @return
     */
    boolean enableFactoryReset(boolean enable);

    /**
     * 获取是否允许恢复出厂
     *
     * @return
     */
    boolean isEnableFactoryReset();

    /**
     * 是否允许使用第二种sim卡
     *
     * @param enable
     * @return
     */
    boolean enableSecSimcard(boolean enable);


    /**
     * 获取 是否允许使用第二种sim卡
     * @return
     */
    boolean isSecSimcardEnabled();

    /**
     * 是否允许使用apn
     *
     * @param enable
     * @return
     */
    boolean enableUseApn(boolean enable);

    /**
     * 获取是否允许使用apn
     *
     * @return
     */
    boolean isEnableUseApn();


    /**
     * 是否允许使用电话
     *
     * @param enable
     * @return
     */
    boolean enableTelePhone(boolean enable);


    /**
     * 获取是否允许使用电话
     * @return
     */
    boolean isTelephoneEnabled();


    /**
     * 是否允许使用nfc
     *
     * @param enable
     * @return
     */
    boolean enableNfc(boolean enable);

    /**
     * 强制开启nfc
     *
     * @return
     */
    boolean forceOpenNfc();


    /**
     * 查询Nfc功能是否被禁用
     * true: NFC功能可以打开；false: NFC功能被禁用
     *
     * @return
     */
    boolean isNfcAllowed();


    /**
     * 是否允许切换系统
     * 双系统mate20手机 有该功能
     * enable true 允许   false 禁止
     * 鼎桥接口没有返回值  所以用void
     */

    void enableSwitchingSystem(boolean enable);
    /**
     * 是否允许切换系统
     * 双系统mate20手机 有该功能
     * enable true 允许   false 禁止
     * 鼎桥接口没有返回值  所以用void
     */

    boolean isEnableSwitchingSystem();
}