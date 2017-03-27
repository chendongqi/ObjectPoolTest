package com.example.chendongqi.objectpooltest;


import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by chendongqi on 17-3-20.
 * 用Stack管理对象的对象池实现
 */

public class StackObjectPool implements ObjectPool {

    private final static String TAG = "StackObjectPoll";

    private static final int MAX_SIZE = 10;// 栈中可容纳的最大对象数量
    private int mActiveNum = 0;
    private int mIdleNum = 0;
    private PoolableObjectFactory mFactory;// 工厂的示例，用于操作对象的生产和销毁等生命周期
    private final Stack mStack = new Stack();// StackObjectPool中用Stack来管理对象，先进后出

    public StackObjectPool(PoolableObjectFactory factory) {
        mFactory = factory;
    }

    @Override
    public synchronized Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
        BigObject obj = null;
        // 栈里是空的，则创建一个对象
        if(mStack.isEmpty() == true) {
            if(mFactory == null) {
                throw new NoSuchElementException();
            } else {
                obj = (BigObject) mFactory.makeObject();
            }
        } else {
            obj = (BigObject) mStack.pop();
            mIdleNum--;// 栈内可用对象减1
        }

        mFactory.activateObject(obj);// 激活对象
        mActiveNum++;// 外借的激活对象加1
        return obj;
    }

    @Override
    public synchronized void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {

    }

    @Override
    public synchronized void returnObject(Object arg0) throws Exception {
        if(mFactory == null) {
            throw new NoSuchElementException();
        } else {
            if(mStack == null) {
                throw new IllegalStateException();
            } else {
                if(mStack.size() >= MAX_SIZE) {// 栈中现有的数量超过了最大限制
                    Log.d(TAG, "stack is full and returned object will not push into stack!");
                } else {
                    mFactory.inactivate(arg0);
                    mStack.push(arg0);
                    mIdleNum++;// 栈内可用对象加1
                    mActiveNum--;// 外借的激活对象减1
                }
            }
        }
    }

    @Override
    public synchronized int getNumActive() {
        return mActiveNum;
    }

    @Override
    public synchronized int getNumIdle() {
        return mIdleNum;
    }

    @Override
    public synchronized void clear() throws Exception, UnsupportedOperationException {
        if(mStack != null) {
            Object obj;
            while(mStack.isEmpty() ==false) {
                obj = mStack.pop();
                mFactory.destroyObject(obj);
            }
        }
    }

    @Override
    public synchronized void close() {
        try {
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
