package com.broad.web.framework.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebQuery<T> extends BaseQuery{

	private static final long serialVersionUID = 7078534495747233451L;
	
	public T data;
	
}
