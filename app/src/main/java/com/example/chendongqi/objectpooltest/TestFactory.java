package com.example.chendongqi.objectpooltest;

/**
 * Created by chendongqi on 17-3-18.
 * 用于生产销毁等和对象生命周期相关的处理
 */

public class TestFactory implements PoolableObjectFactory {

    // 分配一个BigObject类型的对象
    @Override
    public Object makeObject() throws Exception {
        BigObject bo = new BigObject();
        return bo;
    }

    // 销毁传入的对象
    @Override
    public void destroyObject(Object arg0) throws Exception {
        arg0 = null;
    }

    // 激活传入的对象
    @Override
    public void activateObject(Object arg0) throws Exception {
        if(arg0 != null) {
            ((BigObject) arg0).setActive(true);
        }
    }

    // 取消激活传入的对象
    @Override
    public void inactivate(Object arg0) throws Exception {
        if(arg0 != null) {
            ((BigObject) arg0).setActive(false);
        }
    }

    // 验证传入对象的有效性（是否处于激活可用状态）
    @Override
    public boolean validateObject(Object arg0) throws Exception {
        if(arg0 != null) {
            return ((BigObject) arg0).isActive();
        } else {
            return false;
        }
    }
}
