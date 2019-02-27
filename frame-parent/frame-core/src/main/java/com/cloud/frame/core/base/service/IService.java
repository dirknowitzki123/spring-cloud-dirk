package com.cloud.frame.core.base.service;

import java.util.List;
import java.util.Map;


/**
 * 需要实体类实现的方法的接口
 * @author HeJian
 *
 * @param <T>
 */
public interface IService<T>  extends IBaseService<T>{

	/**
	 * 通过map参数获取查询的一个结果
	 * @param params
	 * @return
	 */
	public T getOne(Map<String,Object> params);
	
	/**
	 * 根据map条件参数获取查询结果列表
	 * @param params
	 * @return
	 */
	public List<T> getList(Map<String,Object> params);
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public void save(T entity);
	
}
