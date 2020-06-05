package com.broad.web.framework.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Data;

@Data
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 3790201671454878140L;

	private String bdUserCode;
	
	private String bdUserName;
	
	private String remark;
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this,SerializerFeature.WriteDateUseDateFormat);
	}

	@JSONField(serialize = false)
	public String getJson() {
		return JSONObject.toJSONString(this);
	}
}
