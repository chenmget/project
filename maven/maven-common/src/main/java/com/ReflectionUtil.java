package com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

public class ReflectionUtil {
	
    //mapתbean
	public static Object maptoBean(Class<?> beanClass,Map map) {
		 if (map == null)  
	            return null;  
	        Object obj=new Object();
			try {
				obj = beanClass.newInstance();
				BeanUtils.populate(obj, map);  
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        return obj;  
	}
	
	 public static Map<Object, Object> objectToMap(Object obj) {  
	        if(obj == null)  
	            return null;   
	        Map<Object, Object> map=new BeanMap(obj);  
	        return map;
	    }  
	
	
}
