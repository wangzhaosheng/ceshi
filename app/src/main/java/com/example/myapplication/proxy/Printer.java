package com.example.myapplication.proxy;

/**
 * Created by wangzhaosheng on 2020-01-11
 * Description
 */
public class Printer implements IPrinter, ISay {
    public void print(){
        System.out.println("打印！");
    }

    @Override
    public void woShuo() {
        System.out.println("我说啊！");
    }
}
