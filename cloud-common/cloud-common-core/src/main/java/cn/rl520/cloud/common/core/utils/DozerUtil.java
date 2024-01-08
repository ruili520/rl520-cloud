package cn.rl520.cloud.common.core.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DozerUtil {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public DozerUtil() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /** @deprecated */
    @Deprecated
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        Iterator var3 = sourceList.iterator();

        while(var3.hasNext()) {
            Object sourceObject = var3.next();
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static void map(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    public static <T> T combineCore(T sourceBean, T targetBean) {
        Class<? extends Object> sourceBeanClass = sourceBean.getClass();
        Class<? extends Object> targetBeanClass = targetBean.getClass();
        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();

        for(int i = 0; i < sourceFields.length; ++i) {
            Field sourceField = sourceFields[i];
            if (!Modifier.isStatic(sourceField.getModifiers())) {
                Field targetField = targetFields[i];
                if (!Modifier.isStatic(targetField.getModifiers())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);

                    try {
                        if (sourceField.get(sourceBean) != null && !"serialVersionUID".equals(sourceField.getName().toString())) {
                            targetField.set(targetBean, sourceField.get(sourceBean));
                        }
                    } catch (IllegalAccessException | IllegalArgumentException var10) {
                        var10.printStackTrace();
                    }
                }
            }
        }

        return targetBean;
    }

}
