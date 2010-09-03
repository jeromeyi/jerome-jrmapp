package com.jrmapp.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author л�� E-mail:xieyi@kebao.cn
 * @version ����ʱ�䣺Jul 24, 2008 3:11:22 PM
 * @��˵�� }��javabean�����Կ�������
 */
public class BeanUtil {

	/**
	 * ֱ�ӵ���org.apache.commons.beanutils.BeanUtils�Ķ������Կ������� ��Դ��������Կ�����Ŀ������
	 * 
	 * @param destination
	 *            Ŀ�Ķ���
	 * @param origin
	 *            Դ����
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyBeanToBeanWithBeanutils(Object destination,
			Object origin) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtils.copyProperties(destination, origin);
	}

	/**
	 * ��Դ��������Կ�����Ŀ������
	 * 
	 * @param destination
	 *            Ŀ�Ķ���
	 * @param origin
	 *            Դ����
	 * @throws Exception
	 */
	public static void copyBeanToBean(Object destination, Object origin)
			throws Exception {
		Method[] method1 = origin.getClass().getMethods();
		Method[] method2 = destination.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(origin, objs1);// ����origin����Ӧ��get�ķ�����objs1�����ŵ��ø÷����Ĳ���,������û�в��������ĳ���Ϊ0
							method2[j].invoke(destination, objs2);// ����destination����Ӧ��set�ķ�����objs2�����ŵ��ø÷����Ĳ���
							continue;
						}
					}
				}
			}
		}
	}
	


	/**
	 * ��Դ��������Կ�����Ŀ������,���Դ���������ֵΪ�գ��򲻿���
	 * 
	 * @param destination
	 *            Ŀ�Ķ���
	 * @param origin
	 *            Դ����
	 * @throws Exception
	 */
	public static void copyB2BNoNullValue(Object destination, Object origin)
			throws Exception {
		Method[] method1 = origin.getClass().getMethods();
		Method[] method2 = destination.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(origin, objs1);// ����origin����Ӧ��get�ķ�����objs1�����ŵ��ø÷����Ĳ���,������û�в��������ĳ���Ϊ0
							if (null ==objs2[0])
								continue;
							method2[j].invoke(destination, objs2);// ����destination����Ӧ��set�ķ�����objs2�����ŵ��ø÷����Ĳ���
							continue;
						}
					}
				}
			}
		}
	}

}
