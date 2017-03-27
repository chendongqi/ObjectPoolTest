package com.example.chendongqi.objectpooltest;

/**
 * Created by chendongqi on 17-3-18.
 * 用户生产分配对象的工厂类的接口
 */

public interface PoolableObjectFactory {

    Object makeObject() throws Exception;// 创建对象
    void destroyObject(Object arg0) throws Exception;// 销毁对象
    void activateObject(Object arg0) throws Exception;// 激活对象
    void inactivate(Object arg0) throws Exception;// 取消激活对象
    boolean validateObject(Object arg0) throws Exception;// 验证对象是否存活

}
