package com.broad.web.framework.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;

public class CollectionUtil {

	/**
	 * 
	 * <pre>
	 * 去掉重复的数据（按照list顺序）
	 * </pre>
	 *
	 * @param c
	 * @param lists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> clearRepeat(Class<T> c, List<T>... lists) {
		List<T> newList = new ArrayList<T>();
		for (List<T> list : lists) {
			newList.addAll(list);
		}
		return new ArrayList<T>(new LinkedHashSet<T>(newList));
	}

	/**
	 * 
	 * <pre>
	 * 去掉重复的数据（打乱数据的顺序）
	 * </pre>
	 *
	 * @param c
	 * @param lists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> clearRepeatDisorder(Class<T> c, List<T>... lists) {
		List<T> newList = new ArrayList<T>();
		for (List<T> list : lists) {
			newList.addAll(list);
		}
		return new ArrayList<T>(new LinkedHashSet<T>(newList));
	}

	/**
	 * 
	 * <pre>
	 * 分割list
	 * </pre>
	 *
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static List<String> splitString(String splitStr, String regex) {
		if (StringUtils.isBlank(splitStr) || StringUtils.isBlank(regex))
			return null;
		List<String> list = new ArrayList<String>();
		for (String s : splitStr.split(regex)) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 
	 * <pre>
	 * 分割list
	 * </pre>
	 *
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static Set<String> splitString2Set(String splitStr, String regex) {
		List<String> list = splitString(splitStr, regex);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (String str : list) {
			set.add(str);
		}
		return set;
	}

	/**
	 * 
	 * <pre>
	 * 分割list
	 * </pre>
	 *
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static String joinString(List<String> list, String regex) {
		if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(regex))
			return null;
		StringBuffer str = new StringBuffer();
		for (String s : list) {
			str.append(regex).append(s);
		}
		return str.toString().substring(1);
	}

	/**
	 * 
	 * <pre>
	 * 分割成integer数组
	 * </pre>
	 *
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static List<Integer> splitInteger(String splitStr, String regex) {
		List<String> list1 = splitString(splitStr, regex);
		if (CollectionUtils.isEmpty(list1))
			return null;
		List<Integer> list2 = new ArrayList<Integer>();
		CollectionUtils.collect(list1, new Transformer() {
			public java.lang.Object transform(java.lang.Object input) {
				return new Integer((String) input);
			}
		}, list2);
		return list2;
	}

	/**
	 * 
	 * <pre>
	 * 分割list为long
	 * </pre>
	 *
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static List<Long> splitLong(String splitStr, String regex) {
		List<String> list1 = splitString(splitStr, regex);
		if (CollectionUtils.isEmpty(list1))
			return null;
		List<Long> list2 = new ArrayList<Long>();
		CollectionUtils.collect(list1, new Transformer() {
			public java.lang.Object transform(java.lang.Object input) {
				return new Long((String) input);
			}
		}, list2);
		return list2;
	}

	/**
	 * 拆分集合
	 * 
	 * @param <T>
	 * @param resList 要拆分的集合
	 * @param count   每个集合的元素个数
	 * @return 返回拆分后的各个集合
	 */
	public static <T> List<List<T>> decompose(List<T> resList, int count) {
		if (resList == null || count < 1)
			return null;
		List<List<T>> ret = new ArrayList<List<T>>();
		int size = resList.size();
		if (size <= count) { // 数据量不足count指定的大小
			ret.add(resList);
		} else {
			int pre = size / count;
			int last = size % count;
			// 前面pre个集合，每个大小都是count个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<T>();
				for (int j = 0; j < count; j++) {
					itemList.add(resList.get(i * count + j));
				}
				ret.add(itemList);
			}
			// last的进行处理
			if (last > 0) {
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i < last; i++) {
					itemList.add(resList.get(pre * count + i));
				}
				ret.add(itemList);
			}
		}
		return ret;
	}
}
