package com.example.chendongqi.objectpooltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ObjectPoolTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestFactory factory = new TestFactory();
        StackObjectPool pool = new StackObjectPool(factory);

        BigObject object = null;
        // 从对象池中借一个对象出来
        try {
            object = (BigObject) pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 验证对象状态后使用
        try {
            if(factory.validateObject(object)) {
                Log.d(TAG, "object borrowed from pool, and its active status is " + object.isActive());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 归还对象
        try {
            pool.returnObject(object);
        } catch (Exception e) {

        } finally {
            pool.close();
        }

    }
}
