package com.example.myapplication;

import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Administrator
 * 2019/5/15.
 * MyVpnService
 */
public class MyVpnService  extends VpnService {

    private ParcelFileDescriptor descriptor;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private boolean isRun;
    private Thread vpnThread;
    private int mtuSize = 1024;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            establishVPN();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据配置创建VPN代理  获取到文件描述符 建立VPN连接
     * @throws Exception
     */
    private void establishVPN() throws Exception {
        Builder builder = new Builder();
        // 设置最大缓存
        builder.setMtu(mtuSize);
        // 设置服务名
        builder.setSession(getString(R.string.app_name));

//        // 设置虚拟主机的地址
//        // 开启了服务后所有能进入该服务的数据报都会转发到该地址
        builder.addAddress("192.168.0.6", 24);
//
//        // 设置需要拦截的路由地址
//        // 设置了该值后，这个地址下的所有数据都被转发到虚拟主机进行处理。
//        builder.addRoute(routeAddress routePort);

        // 设置域名解析服务器地址。
        // 可添加多个，系统根据需要进行选择使用
        builder.addDnsServer("8.8.8.8");
        builder.addDnsServer("8.8.4.4");
        builder.addDnsServer("208.67.222.222");
        builder.addDnsServer("114.114.114.114");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 添加需要拦截的应用
            // 如果应用未安装时 这里会拋出未找到应用的异常。
            builder.addAllowedApplication("com.UCMobile");
            builder.addAllowedApplication("com.example.myapplication");
//            builder.addDisallowedApplication("com.example.myapplication");
        }
        // 调用方法建立连接，并把返回的包描述符实例赋值给全局变量
        descriptor = builder.establish();
    }

}
