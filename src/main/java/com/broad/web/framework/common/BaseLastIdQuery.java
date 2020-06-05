package com.broad.web.framework.common;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Data;

@Data
public class BaseLastIdQuery implements Serializable {

	private static final long serialVersionUID = -2125401667482699167L;

	/**
	 * 最后一条记录的id
	 */
	private Long lastId;

	/** 每页记录数 */
	private int pageSize = 20;

	@JSONField(serialize = false)
	public Date getNow() {
		return new Date();
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
