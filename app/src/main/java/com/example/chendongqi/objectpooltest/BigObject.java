package com.example.chendongqi.objectpooltest;

/**
 * Created by chendongqi on 17-3-18.
 * 对象池适合管理的通常都是重量级的大对象
 */

public class BigObject {

    private boolean mActive;// 标志对象是否存活

    public BigObject() { // 构造方法
        mActive = true;
        System.out.println("I'm a big object");
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    public boolean isActive() {
        return mActive;
    }
}
