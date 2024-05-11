package com.example.semestrovkacourse2sem2oris.util;

import java.lang.reflect.Field;

public class ObjectCopier {

    public static <T> void copyNotNullFields(T source, T destination) {
        Field[] fields = source.getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);

                Object value = null;
                value = field.get(source);

                if (value != null && field.get(destination) == null) {
                    field.set(destination, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
