package com.example.chendongqi.objectpooltest;

import java.util.NoSuchElementException;

/**
 * Created by chendongqi on 17-3-18.
 * 对象池提供管理对象的接口
 */

public interface ObjectPool {

    // 从对象池中借一个对象
    Object borrowObject() throws  Exception, NoSuchElementException, IllegalStateException;

    // 添加独享到管理模型中
    void addObject() throws Exception, IllegalStateException,
            UnsupportedOperationException;

    // 将对象归还到对象池中
    void returnObject(Object arg0) throws Exception;

    // 获取对象池中激活对象的数量
    int getNumActive();

    // 获取空闲的对象数量
    int getNumIdle();

    // 清理池中的对象
    void clear() throws Exception, UnsupportedOperationException;

    // 关闭对象池
    void close();

}
