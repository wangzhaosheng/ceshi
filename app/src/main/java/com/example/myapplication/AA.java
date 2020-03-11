package com.example.myapplication;

/**
 * Administrator
 * 2019/5/5.
 * AA
 */
public abstract class AA {


    public int a;
    public AA aa;

    public AA(int a) {
        this.a = a;
        aa = get();

    }

    @Override
    public String toString() {
        return "AA{" +
                "a=" + a +
                '}';
    }

    abstract AA get();

}
