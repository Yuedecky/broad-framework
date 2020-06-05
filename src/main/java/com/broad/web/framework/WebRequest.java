package com.broad.web.framework;

import com.broad.web.framework.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebRequest<T> extends BaseRequest {

	private static final long serialVersionUID = 7078534495747233451L;
	
	public T data;
	
	private String bdUserCode;
	
	private String bdUserName;
	
	private String remark;
	
}
