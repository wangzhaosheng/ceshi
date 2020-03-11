package com.example.myapplication.diaoyan;

/**
 * Created by wangzhaosheng on 2019-12-09
 * Description
 */
public class TestT<T extends BABA > {

    T t;
    public TestT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
