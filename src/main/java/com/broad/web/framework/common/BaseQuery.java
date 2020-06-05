package com.broad.web.framework.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.broad.web.framework.utils.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Data;

/**
 * 查询基类
 * @author yuwenjie
 *
 */
@Data
public class BaseQuery implements Serializable{

	private static final long serialVersionUID = 3858364004994046479L;

	/** 起始页 */
	private int pageNo = 1;

	/** 每页记录数 */
	private int pageSize = 20;

	@JSONField(serialize=false)
	public Date getNow() {
		return new Date();
	}

	@JSONField(serialize=false)
	public Integer getStart() {
		if (pageNo < 0 || pageNo < 0) {
			return 0;
		} else {
			return ((pageNo - 1) * pageSize);
		}
	}

	private List<String> orders;
	
	public BaseQuery pushOrder(String order){
		if(StringUtils.isBlank(order)) return this;
		order = order.trim();
		if(!order.contains(" asc") && !order.contains(" desc")){
			return this;
		}
		if(null == orders) {
			orders = new ArrayList<String>();
		}
		orders.add(order);
		return this;	
	}

	@JSONField(serialize=false)
	public String getOrders() {
		if(CollectionUtils.isNotEmpty(this.orders)){
			return CollectionUtil.joinString(this.orders, ",");
		}
		return "id desc";
	}
	
	@JSONField(serialize=false)
	public String getNoDefaultOrders() {
		if(CollectionUtils.isNotEmpty(this.orders)){
			return CollectionUtil.joinString(this.orders, ",");
		}
		return "";
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this,SerializerFeature.WriteDateUseDateFormat);
	}
}
