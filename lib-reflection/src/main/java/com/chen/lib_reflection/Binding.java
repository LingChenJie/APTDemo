package com.chen.lib_reflection;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Created by Chen on 2022/3/22.
 * Describe:
 */
public class Binding {

    /**
     * 通过反射绑定View视图Id
     * @param activity
     */
    public static void bind(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                try {
                    field.setAccessible(true);
                    field.set(activity, activity.findViewById(bindView.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
