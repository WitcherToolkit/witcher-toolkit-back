package fr.meya.witcher.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class ObjectUtils {

    // Constructeur en private pour éviter les instantiations.
    private ObjectUtils() {}

    public static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(java.beans.PropertyDescriptor::getName)
                .filter(propertyName -> {
                    try {
                        return source.getClass()
                                .getMethod("get" + StringUtils.capitalize(propertyName))
                                .invoke(source) == null;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toArray(String[]::new);
    }
}
