package com.chen.lib_bindview;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Chen on 2022/3/22.
 * Describe:
 */
public class Binding {

    /**
     * 通过反射调用对应 Activity 的 ActivityBinding 构造方法
     * ActivityBinding 是由注解生成的
     *
     * @param activity
     */
    public static void bind(Activity activity) {
        try {
            Class<?> bindingClass = Class.forName(activity.getClass().getCanonicalName() + "Binding");
            Constructor<?> constructor = bindingClass.getDeclaredConstructor(activity.getClass());
            constructor.newInstance(activity);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
