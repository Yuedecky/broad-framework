package com.broad.web.framework.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -3192088695075442965L;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this,SerializerFeature.WriteDateUseDateFormat);
	}

	@JSONField(serialize = false)
	public String getJson() {
		return JSONObject.toJSONString(this);
	}
}
