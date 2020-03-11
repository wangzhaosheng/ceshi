package com.example.myapplication.factory;

import com.example.myapplication.AA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhaosheng on 2019-11-26
 * Description
 */
public class SmsFactory implements IFactory<List<AA>> {
    @Override
    public List<AA> getMsgId() {
        List<AA> aas = new ArrayList<>();
//        aas.add(new AA(5));
        return aas;
    }
}
