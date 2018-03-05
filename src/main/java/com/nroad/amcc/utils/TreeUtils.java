package com.nroad.amcc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static <T> void createTree(List<T> list, T root, String keyFieldName, String parentKeyFieldName, String subFieldName) {
        Field keyField = ReflectUtils.getField(keyFieldName, root);
        Field parentKeyField = ReflectUtils.getField(parentKeyFieldName, root);
        Field subField = ReflectUtils.getField(subFieldName, root);
        find(list, root, keyField, parentKeyField, subField);
    }

    public static <T, E> List<E> getKeys(List<T> list, T root, String keyFieldName, String parentKeyFieldName) {
        Field keyField = ReflectUtils.getField(keyFieldName, root);
        Field parentKeyField = ReflectUtils.getField(parentKeyFieldName, root);
        List<E> keys = new ArrayList<>();
        E value = ReflectUtils.getValueByGetMethod(keyField, root);
        keys.add(value);
        findKeys(list, keys, root, keyField, parentKeyField);
        return keys;
    }

    private static <T> void find(List<T> list, T parent, Field keyField, Field parentKeyField, Field subField) {
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);
        if (subs != null) {
            ReflectUtils.setValueByField(subField, parent, subs);
            for (T sub : subs) {
                find(list, sub, keyField, parentKeyField, subField);
            }
        }
    }

    private static <T, E> List<E> findKeys(List<T> list, List<E> keys, T parent, Field keyField, Field parentKeyField) {
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);

        List<E> subKeys = getSubKeys(list, parent, keyField, parentKeyField);
        if (subs != null) {
            keys.addAll(subKeys);
            for (T sub : subs) {
                findKeys(list, keys, sub, keyField, parentKeyField);
            }
        }
        return keys;
    }


    private static <T> List<T> getSubs(List<T> list, T parent, Field keyField, Field parentKeyField) {
        List<T> subs = null;
        for (T t : list) {
            Object keyFieldValue = ReflectUtils.getValueByField(keyField, parent);
            Object parentFieldValue = ReflectUtils.getValueByField(parentKeyField, t);
            if (keyFieldValue.equals(parentFieldValue)) {
                if (subs == null) {
                    subs = new ArrayList<>();
                }
                subs.add(t);
            }
        }
        return subs;
    }


    private static <T, E> List<E> getSubKeys(List<T> list, T parent, Field keyField, Field parentKeyField) {
        List<E> subs = null;
        for (T t : list) {
            Object keyFieldValue = ReflectUtils.getValueByField(keyField, parent);
            Object parentFieldValue = ReflectUtils.getValueByField(parentKeyField, t);
            if (keyFieldValue.equals(parentFieldValue)) {
                if (subs == null) {
                    subs = new ArrayList<>();
                }
                Object key = ReflectUtils.getValueByField(keyField, t);
                subs.add((E) key);
            }
        }
        return subs;
    }
}