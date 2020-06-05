package com.broad.web.framework.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class CollectionConvertUtil<T,K extends T> {


	/**
	 *
	 * @param tList
	 * @param kList
	 * @return
	 */
	public List<K> convert2Child(List<T> tList,List<K> kList){
		if(null == kList) {
			kList = new ArrayList<K>();
		}
		for (T t : tList) {
			kList.add(JSONObject.parseObject(JSONObject.toJSONString(t), new TypeReference<K>() {}));
		}
		return kList;
	}

}

